#include <bits/stdc++.h>
using namespace std;

const int MAXN = 10000000;

int main() {
    vector<int> spf(MAXN, 0);
    for (int i = 2; i < MAXN; i++) {
        if (spf[i] == 0) {
            for (int j = i; j < MAXN; j += i) {
                if (spf[j] == 0) {
                    spf[j] = i;
                }
            }
        }
    }

    int counter = 0;
    for (int i = 2; i < MAXN; i++) {
        if (spf[i] == i) {
            counter++;
            if (counter % 100000 == 0) {
                cout << "# Primes Found: " << counter << ", Latest Prime: " << i << endl;
            }
        }
    }
    cout << "End Reached, # Primes Found: " << counter << endl;
}