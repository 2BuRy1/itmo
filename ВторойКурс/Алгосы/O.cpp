#include <iostream>
#include <queue>
#include <vector>
using namespace std;

bool hasCycle(vector<vector<int>> graph) {
  size_t n = graph.size();
  vector<int> color(n, -1);
  for (size_t i = 0; i < n; i++) {
    if (color[i] == -1) {
      color[i] = 0;
      queue<int> q;
      q.push(i);
      while (!q.empty()) {
        int val = q.front();
        q.pop();
        for (int j : graph[val]) {
          if (color[j] == -1) {
            color[j] = color[val] ^ 1;
            q.push(j);
          } else {
            if (color[val] == color[j]) {
              return false;
            }
          }
        }
      }
    }
  }
  return true;
}

int main() {
  int n, m;
  cin >> n >> m;

  vector<vector<int>> graph(n);

  for (int i = 0; i < m; ++i) {
    int u, v;
    cin >> u >> v;
    u--;
    v--;

    graph[u].push_back(v);
    graph[v].push_back(u);
  }

  if (hasCycle(graph)) {
    cout << "YES" << endl;
  } else {
    cout << "NO" << endl;
  }

  return 0;
}