#include <algorithm>
#include <iostream>
#include <map>
#include <vector>

struct ChunkMatcher {
  int target;
  bool operator()(const std::pair<const int, int>& p) const {
    return p.second == target;
  }
};

class MemoryHandler {
  std::map<int, int> memory_chunks;
  std::multimap<int, int> size_map;
  std::vector<std::pair<int, int>> operation_history;

  void remove_chunk(std::map<int, int>::iterator chunk) {
    auto [begin, end] = size_map.equal_range(chunk->second - chunk->first);
    auto found = std::find_if(begin, end, ChunkMatcher{chunk->first});
    if (found != end)
      size_map.erase(found);
    memory_chunks.erase(chunk);
  }

  void remove_by_size(std::multimap<int, int>::iterator size_entry) {
    memory_chunks.erase(size_entry->second);
    size_map.erase(size_entry);
  }

  void register_chunk(int start, int end) {
    memory_chunks[start] = end;
    size_map.insert({end - start, start});
  }

public:
  MemoryHandler(int capacity, int max_ops) : operation_history(max_ops) {
    register_chunk(1, capacity + 1);
  }

  void handle_request(int op_index, int req_value) {
    if (req_value < 0) {
      int alloc_index = -req_value - 1;
      if (operation_history[alloc_index].first < 0)
        return;

      auto [base, length] = operation_history[alloc_index];
      int limit = base + length;

      auto next = memory_chunks.lower_bound(limit);
      auto prev = next != memory_chunks.begin() ? std::prev(next) : memory_chunks.end();

      if (next != memory_chunks.end() && next->first == limit) {
        if (prev != memory_chunks.end() && prev->second == base) {
          int new_base = prev->first;
          int new_limit = next->second;
          remove_chunk(prev);
          remove_chunk(next);
          register_chunk(new_base, new_limit);
        } else {
          int new_limit = next->second;
          remove_chunk(next);
          register_chunk(base, new_limit);
        }
      } else {
        if (prev != memory_chunks.end() && prev->second == base) {
          int new_base = prev->first;
          remove_chunk(prev);
          register_chunk(new_base, limit);
        } else {
          register_chunk(base, limit);
        }
      }
    } else {
      auto found = size_map.lower_bound(req_value);
      if (found == size_map.end()) {
        operation_history[op_index] = {-1, req_value};
        std::cout << -1 << '\n';
      } else {
        std::cout << found->second << '\n';
        int new_base = found->second + req_value;
        int new_limit = found->first + found->second;
        operation_history[op_index] = {found->second, req_value};
        remove_by_size(found);
        register_chunk(new_base, new_limit);
      }
    }
  }
};

int main() {
  int N, M;
  std::cin >> N >> M;

  MemoryHandler alloc_system(N, M);

  for (int i = 0; i < M; ++i) {
    int cmd;
    std::cin >> cmd;
    alloc_system.handle_request(i, cmd);
  }

  return 0;
}