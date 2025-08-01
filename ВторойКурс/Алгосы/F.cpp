#include <iostream>
#include <vector>
using namespace std;

int main() {
    vector<string> numbers;
    string line;
    for (string input; cin >> input;) {
        numbers.push_back(input);
        if (numbers.size() >= 2) {
            for (size_t j = 0; j < numbers.size(); j++) {
                for (size_t i = 1; i < numbers.size(); i++) {
                    if (stoll(numbers[i - 1] + numbers[i]) < stoll(numbers[i] + numbers[i - 1])) {
                        string buffer = numbers[i - 1];
                        numbers[i - 1] = numbers[i];
                        numbers[i] = buffer;
                    }
                }
            }
        }
    }
    if (!numbers.empty()) {
        for (string e : numbers) {
            cout << e;
        }
    }
    return 0;
}
