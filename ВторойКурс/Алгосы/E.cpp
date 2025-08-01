#include <cmath>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int main() {
    int places, cows;
    cin >> places >> cows;
    vector<int> v(places);

    for (int i = 0; i < places; i++) {
        cin >> v[i];
    }
    int best = 0;
    int minRange = 0, maxRange = v.back() - v.front(), average;

    while (minRange <= maxRange) {
        average = (maxRange + minRange) / 2;
        int remainingCows = cows - 1;
        int lastPlacesCow = v[0];
        for (size_t i = 0; i < v.size(); i++) {
            if (abs(v[i] - lastPlacesCow) >= average) {
                lastPlacesCow = v[i];
                remainingCows--;
            }
        }

        if (remainingCows <= 0) {
            minRange = average + 1;
            best = average;
        } else {
            maxRange = average - 1;
        }
    }

    cout << best << endl;
    return 0;
}