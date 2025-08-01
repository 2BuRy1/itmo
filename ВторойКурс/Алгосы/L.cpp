#include <deque>
#include <iostream>
#include <vector>

using namespace std;

int main() {
    int n, window;
    cin >> n >> window;
    vector<int> nums(n);
    for (size_t i = 0; i < nums.size(); ++i) {
        cin >> nums[i];
    }

    deque<int> deque;
    vector<int> result;

    for (int i = 0; i < window; ++i) {
        while (!deque.empty() && nums[i] <= nums[deque.back()]) {
            deque.pop_back();
        }
        deque.push_back(i);
    }
    result.push_back(nums[deque.front()]);

    for (int i = window; i < n; ++i) {
        if (!deque.empty() && deque.front() <= i - window) {
            deque.pop_front();
        }

        while (!deque.empty() && nums[i] <= nums[deque.back()]) {
            deque.pop_back();
        }

        deque.push_back(i);
        result.push_back(nums[deque.front()]);
    }

    for (int val : result) {
        cout << val << " ";
    }
    return 0;
}