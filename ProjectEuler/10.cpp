#include <bits/stdc++.h>
using namespace std;

const int MAXN = 2000000;

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

    long long sum = 0;
    for (int i = 2; i < MAXN; i++) {
        if (spf[i] == i) {
            sum += i;
        }
    }
    cout << sum << endl;
}