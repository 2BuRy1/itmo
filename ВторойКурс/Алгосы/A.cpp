#include <iostream>
#include <vector>

using namespace std;

int main() {
    int n, startIndex = 0, currentLength = 0, maxLength = 1, bestIndex = 0;
    cin >> n;

    vector<int> flowers(n);

    for (int i = 0; i < n; i++) {
        cin >> flowers[i];
    }

    for (size_t i = 0; i < flowers.size(); i++) {
        if (i >= 2 && flowers[i - 2] == flowers[i] && flowers[i - 1] == flowers[i]) {
            startIndex = i - 1;
            currentLength = 2;
        } else {
            currentLength++;
            if (currentLength > maxLength) {
                maxLength = currentLength;
                bestIndex = startIndex;
            }
        }
    }
    cout << bestIndex + 1 << " " << bestIndex + maxLength << endl;

    return 0;
}