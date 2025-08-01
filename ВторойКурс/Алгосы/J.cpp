#include <iostream>
#include <list>
#include <vector>
using namespace std;
void print_value(const string value) {
    cout << value << '\n';
}
int main() {
    list<string> myList;
    auto mid = myList.begin();

    int n;
    cin >> n;

    vector<string> goblins;

    for (int i = 0; i < n; i++) {
        string operation;
        string index;
        cin >> operation;
        if (operation == "+" || operation == "*") {
            cin >> index;
            goblins.push_back(operation + ' ' + index);
        } else {
            goblins.push_back(operation + ' ');
        }
    }

    for (size_t i = 0; i < goblins.size(); ++i) {
        string operation = goblins[i].substr(0, goblins[i].find(' '));
        string index = goblins[i].substr(goblins[i].find(' ') + 1);
        if (operation == "+" || operation == "*") {
            if (operation == "+") {
                myList.push_back(index);
                if (myList.size() == 1) {
                    mid = myList.begin();
                } else if (myList.size() % 2 != 0) {
                    mid++;
                }
            } else if (operation == "*") {
                if (myList.empty()) {
                    myList.push_back(index);
                    mid = myList.begin();
                } else {
                    if (myList.size() % 2 == 0) {
                        myList.insert(next(mid), index);
                        mid++;
                    } else {
                        myList.insert(next(mid), index);
                    }
                }
            }

        } else if (operation == "-") {
            print_value(myList.front());
            bool was_mid_at_begin = (mid == myList.begin());

            myList.pop_front();

            if (was_mid_at_begin) {
                mid = myList.begin();
            } else if (myList.size() % 2 != 0) {
                ++mid;
            }
        }
    }

    return 0;
}