#include <iostream>
#include <stack>
#include <unordered_set>
#include <vector>

using namespace std;

bool dfs(const vector<int>& graph, unordered_set<int>& visited, int start) {
  stack<int> st;
  unordered_set<int> currentPath;

  st.push(start);
  currentPath.insert(start);
  bool cycleFound = false;

  while (!st.empty()) {
    int node = st.top();
    int next = graph[node] - 1;

    if (currentPath.count(next)) {
      while (!st.empty()) {
        int cycle_node = st.top();
        visited.insert(cycle_node);
        st.pop();
        if (cycle_node == next)
          break;
      }
      cycleFound = true;
      break;
    }

    if (visited.count(next)) {
      visited.insert(node);
      break;
    }

    currentPath.insert(next);
    st.push(next);
  }

  return cycleFound;
}

int main() {
  int n;
  cin >> n;
  vector<int> graph(n);
  unordered_set<int> visited;
  int cycles = 0;

  for (int i = 0; i < n; ++i) {
    cin >> graph[i];
  }

  for (int i = 0; i < n; ++i) {
    if (!visited.count(i)) {
      if (dfs(graph, visited, i)) {
        cycles++;
      }
    }
  }

  cout << cycles << endl;
  return 0;
}