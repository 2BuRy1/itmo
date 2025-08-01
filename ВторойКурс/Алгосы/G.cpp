#include <algorithm>
#include <deque>
#include <iostream>
#include <unordered_map>
#include <vector>

using namespace std;

int main() {
  vector<pair<char, int>> weights(26);
  unordered_map<char, int> freq_map;
  string input;

  cin >> input;

  for (int i = 0; i < 26; i++) {
    int weighty;
    cin >> weighty;
    weights[i] = {'a' + i, weighty};
  }

  for (char c : input) {
    freq_map[c]++;
  }

  vector<pair<char, int>> frequency(freq_map.begin(), freq_map.end());

  auto cmp = [&](const pair<char, int> a, const pair<char, int> b) {
    return weights[a.first - 'a'].second > weights[b.first - 'a'].second;
  };

  sort(frequency.begin(), frequency.end(), cmp);

  deque<char> leftSide, rightSide;
  vector<char> middle;

  for (const auto& p : frequency) {
    if (p.second >= 2) {
      leftSide.push_back(p.first);
      rightSide.push_front(p.first);
      for (int j = 0; j < p.second - 2; ++j) {
        middle.push_back(p.first);
      }
    } else {
      middle.push_back(p.first);
    }
  }

  string result;
  for (char e : leftSide)
    result += e;
  for (char e : middle)
    result += e;
  for (char e : rightSide)
    result += e;

  cout << result << endl;

  return 0;
}