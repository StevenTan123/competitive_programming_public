#include <bits/stdc++.h>
using namespace std;

const int BITS = 30;

void solve() {
    int n;
    cin >> n;

    vector<vector<bool>> a(n - 1, vector<bool>(BITS));
    for (int i = 0; i < n - 1; i++) {
        int ai;
        cin >> ai;
        for (int j = 0; j < BITS; j++) {
            a[i][j] = ai & 1;
            ai >>= 1;
        }
    }

    vector<vector<bool>> res(n, vector<bool>(BITS));
    bool possible = true;
    for (int i = 0; i < BITS; i++) {
        int state = 2;
        for (int j = 1; j < n; j++) {
            if (a[j - 1][i]) {
                if (state == 0) {
                    possible = false;
                    break;
                } else {
                    res[j - 1][i] = 1;
                    res[j][i] = 1;
                    state = 1;
                }
            } else {
                if (state == 1) {
                    res[j][i] = 0;
                    state = 0;
                } else {
                    if (state == 2) {
                        res[j - 1][i] = 0;
                    }
                    state = 2;
                }
            }
        }
    }

    if (possible) {
        for (int i = 0; i < n; i++) {
            int val = 0;
            for (int j = BITS - 1; j >= 0; j--) {
                val = (val << 1) | res[i][j];
            }
            cout << val << " ";
        }
        cout << "\n";
    } else {
        cout << "-1\n";
    }
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