#include <algorithm>
#include <climits>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

using Node = pair<int, pair<int, int>>;

int N, M, startRow, startCol, endRow, endCol;
vector<pair<int, int>> dirs;
const vector<char> move_chars = {'N', 'S', 'W', 'E'};

void dijkstra(
    vector<vector<char>>& grid, vector<vector<int>>& dist, vector<vector<char>>& prev_dir
) {
  priority_queue<Node, vector<Node>, greater<Node>> pq;

  dist[startRow][startCol] = 0;
  pq.push({0, make_pair(startRow, startCol)});

  while (!pq.empty()) {
    auto [time, pos] = pq.top();
    auto [row, col] = pos;
    pq.pop();

    if (row == endRow && col == endCol)
      break;
    if (time > dist[row][col])
      continue;

    for (int i = 0; i < 4; ++i) {
      int new_row = row + dirs[i].first;
      int new_col = col + dirs[i].second;

      if (new_row < 0 || new_row >= N || new_col < 0 || new_col >= M)
        continue;
      if (grid[new_row][new_col] == '#')
        continue;

      int move_time = (grid[new_row][new_col] == 'W') ? 2 : 1;
      int new_time = time + move_time;

      if (new_time < dist[new_row][new_col]) {
        dist[new_row][new_col] = new_time;
        prev_dir[new_row][new_col] = i;
        pq.push({new_time, make_pair(new_row, new_col)});
      }
    }
  }
}

string reconstruct_path(const vector<vector<char>>& prev_dir) {
  string path;
  int row = endRow, col = endCol;

  while (row != startRow || col != startCol) {
    int dir_idx = prev_dir[row][col];
    auto el = move_chars[dir_idx];
    path.push_back(el);
    row -= dirs[dir_idx].first;
    col -= dirs[dir_idx].second;
  }
  reverse(path.begin(), path.end());
  return path;
}

int main() {
  cin >> N >> M >> startRow >> startCol >> endRow >> endCol;
  dirs.emplace_back(-1, 0);
  dirs.emplace_back(1, 0);
  dirs.emplace_back(0, -1);
  dirs.emplace_back(0, 1);
  startRow--;
  startCol--;
  endRow--;
  endCol--;

  vector<vector<char>> grid(N, vector<char>(M));
  for (auto& row : grid) {
    for (auto& cell : row) {
      cin >> cell;
    }
  }

  if (grid[startRow][startCol] == '#' || grid[endRow][endCol] == '#') {
    cout << -1 << endl;
    return 0;
  }

  vector<vector<int>> dist(N, vector<int>(M, INT_MAX));
  vector<vector<char>> prev_dir(N, vector<char>(M, -1));

  dijkstra(grid, dist, prev_dir);

  if (dist[endRow][endCol] == INT_MAX) {
    cout << -1 << endl;
  } else {
    cout << dist[endRow][endCol] << endl;
    cout << reconstruct_path(prev_dir) << endl;
  }

  return 0;
}