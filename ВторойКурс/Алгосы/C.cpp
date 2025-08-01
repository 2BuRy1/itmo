#include <iostream>
#include <map>
#include <stack>

using namespace std;

bool isNumber(string input) {
    if (input.empty())
        return false;
    if (input == "-0" || input == "0")
        return true;
    size_t start = (input[0] == '-') ? 1 : 0;
    return start < input.size() && input.find_first_not_of("0123456789", start) == string::npos;
}

int main() {
    string input;
    map<string, stack<string>> inputs;
    stack<stack<string>> currentChars;
    currentChars.push(stack<string>());

    while (getline(cin, input)) {
        if (input == "{") {
            currentChars.push(stack<string>());
        } else if (input == "}") {
            if (!currentChars.empty()) {
                while (!currentChars.top().empty()) {
                    inputs[currentChars.top().top()].pop();
                    currentChars.top().pop();
                }
                currentChars.pop();
            }
        } else {
            size_t equalPos = input.find("=");
            string varName = input.substr(0, equalPos);
            string value = input.substr(equalPos + 1);

            string result;

            if (isNumber(value)) {
                result = value;
            } else {
                if (!inputs.count(value) || inputs[value].empty()) {
                    result = "0";
                } else {
                    result = inputs[value].top();
                }
                cout << result << endl;
            }

            currentChars.top().push(varName);
            inputs[varName].push(result);
        }
    }

    return 0;
}