#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;

    if (k % 2 == 1) {
        cout << "No\n";
    } else {
        bool possible = true;
        int res[n][n];
        if (k == 2) {
            if (n == 2) {
                res[0][0] = 1;
                res[1][1] = 1;
                res[0][1] = 0;
                res[1][0] = 0;
            } else {
                possible = false;
            }
        } else if (k % 4 == 0) {
            for (int i = 0; i < n; i += 2) {
                for (int j = 0; j < n; j += 2) {
                    int val = (k == 0 ? 0 : 1);
                    res[i][j] = val;
                    res[i + 1][j] = val;
                    res[i][j + 1] = val;
                    res[i + 1][j + 1] = val;
                    if (k > 0) k -= 4;
                }
            }
        } else if (n * n - k >= 10) {
            k -= 6;
            for (int i = 0; i < n - 4; i += 2) {
                for (int j = 0; j < n; j += 2) {
                    int val = (k == 0 ? 0 : 1);
                    res[i][j] = val;
                    res[i + 1][j] = val;
                    res[i][j + 1] = val;
                    res[i + 1][j + 1] = val;
                    if (k > 0) k -= 4;
                }
            }
            for (int i = n - 4; i < n; i += 2) {
                for (int j = 0; j < n - 4; j += 2) {
                    int val = (k == 0 ? 0 : 1);
                    res[i][j] = val;
                    res[i + 1][j] = val;
                    res[i][j + 1] = val;
                    res[i + 1][j + 1] = val;
                    if (k > 0) k -= 4;
                }
            }
            for (int i = n - 4; i < n; i++) {
                for (int j = n - 4; j < n; j++) {
                    if (i == n - 4 || j == n - 4 || i == j) {
                        res[i][j] = 0;
                    } else {
                        res[i][j] = 1;
                    }
                }
            }
        } else if (n * n - k == 6) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i <= n - 4 || j <= n - 4) {
                        res[i][j] = 1;
                    } else if (i == j) {
                        res[i][j] = 1;
                    } else {
                        res[i][j] = 0;
                    }
                }
            }
        } else {
            possible = false;
        }

        if (possible) {
            cout << "Yes\n";
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cout << res[i][j] << " ";
                }
                cout << "\n";
            }
        } else {
            cout << "No\n";
        }
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