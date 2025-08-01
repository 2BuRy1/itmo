#include <iostream>
#include <vector>

using namespace std;

int main() {
    vector<int> params(5);
    for (size_t i = 0; i < params.size(); ++i) {
        cin >> params[i];
    }

    int a = params[0], b = params[1], c = params[2], d = params[3], k = params[4];
    int buffer = 0;

    for (int i = 0; i < k; ++i) {
        buffer = a * b - c;
        if (buffer < 0) {
            a = 0;
            break;
        }
        if (buffer == a) {
            break;
        }

        if (buffer < d) {
            a = buffer;
        } else {
            a = d;
            break;
        }
    }
    cout << a;
    return 0;
}