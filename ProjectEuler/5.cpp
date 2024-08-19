#include <bits/stdc++.h>
using namespace std;

int main() {
    int val = 1;
    while (true) {
        bool evenly = true;
        for (int i = 2; i <= 20; i++) {
            if (val % i != 0) {
                evenly = false;
                break;
            }
        }
        if (evenly) {
            cout << val << endl;
            break;
        }
        val++;
    }
}