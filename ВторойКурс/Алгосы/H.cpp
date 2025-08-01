#include <algorithm>
#include <iostream>
#include <map>
#include <vector>
using namespace std;

int main() {
    int n, k;
    cin >> n >> k;

    vector<int> nums(n);

    for (int i = 0; i < n; i++) {
        cin >> nums[i];
    }

    sort(nums.begin(), nums.end(), greater<int>());

    for (size_t i = 0; i <= nums.size(); i += k) {
        if (i != 0) {
            nums[i - 1] = 0;
        }
    }

    int result = 0;

    for (auto num : nums) {
        result += num;
    }

    cout << result << endl;

    return 0;
}