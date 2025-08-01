#include <iostream>
#include <map>
#include <stack>
#include <vector>
using namespace std;

bool is_upper_case(char s) {
    if (s >= 'A' && s <= 'Z')
        return true;
    return false;
}

bool is_lower_case(char s) {
    if (s >= 'a' && s <= 'z')
        return true;
    return false;ч
  }

int main() {
    string input;
    cin >> input;
    stack<char> letterStack;
    map<char, deque<int>> animalPosition;
    vector<char> traps;
    int lettersCounter = 0;
    for (size_t i = 0; i < input.length(); i++) {
        if (letterStack.empty()) {
            letterStack.push(input[i]);
            if (is_lower_case(input[i])) {
                lettersCounter++;
                animalPosition[input[i]].push_back(lettersCounter);
            } else {
                traps.push_back(input[i]);
            }
        } else {
            if (is_lower_case(input[i])) {
                lettersCounter++;
                animalPosition[input[i]].push_back(lettersCounter);
                if (isupper(letterStack.top()) && tolower(letterStack.top()) == input[i]) {
                    letterStack.pop();
                } else {
                    letterStack.push(input[i]);
                }
            } else {
                traps.push_back(input[i]);
                if (islower(letterStack.top()) && toupper(letterStack.top()) == input[i]) {
                    letterStack.pop();
                } else {
                    letterStack.push(input[i]);
                }
            }
        }
    }
    if (letterStack.empty()) {
        cout << "Possible" << endl;
        for (char e : traps) {
            int index = animalPosition[tolower(e)].back();

            animalPosition[tolower(e)].pop_back();
            cout << index << " ";
        }
    } else {
        cout << "Impossible" << endl;
    }

    return 0;
}
