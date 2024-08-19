#include <bits/stdc++.h>
using namespace std;

const int MAXB = 31;

void solve() {
    int n;
    cin >> n;
    bool bits[n][MAXB];
    int first_set[n];
    for (int i = 0; i < n; i++) {
        int a;
        cin >> a;
        first_set[i] = -1;
        for (int j = 0; j < MAXB; j++) {
            bits[i][MAXB - j - 1] = a & 1;
            if (bits[i][MAXB - j - 1]) {
                first_set[i] = MAXB - j - 1;
            }
            a >>= 1;
        }
    }

    int evensf[MAXB][n];
    int oddsf[MAXB][n];
    int evensb[MAXB][n];
    int oddsb[MAXB][n];
    for (int i = 0; i < MAXB; i++) {
        for (int j = 0; j < n; j++) {
            if (bits[j][i]) {
                evensf[i][j] = (j > 0 ? oddsf[i][j - 1] : 0);
                oddsf[i][j] = (j > 0 ? evensf[i][j - 1] : 0) + 1;
            } else {
                evensf[i][j] = (j > 0 ? evensf[i][j - 1] : 0) + 1;
                oddsf[i][j] = (j > 0 ? oddsf[i][j - 1] : 0);
            }
        }
        for (int j = n - 1; j >= 0; j--) {
            if (bits[j][i]) {
                evensb[i][j] = (j < n - 1 ? oddsb[i][j + 1] : 0);
                oddsb[i][j] = (j < n - 1 ? evensb[i][j + 1] : 0) + 1;
            } else {
                evensb[i][j] = (j < n - 1 ? evensb[i][j + 1] : 0) + 1;
                oddsb[i][j] = (j < n - 1 ? oddsb[i][j + 1] : 0);
            }     
        }
    }

    long long res = 0;
    for (int i = 0; i < MAXB; i++) {
        for (int j = 0; j < n; j++) {
            if (bits[j][i] && first_set[j] == i) {
                res += (long long) evensf[i][j] * oddsb[i][j];
                res += (long long) oddsf[i][j] * evensb[i][j];
            }
        }
    }
    cout << res << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}