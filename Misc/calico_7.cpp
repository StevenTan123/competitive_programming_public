#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;
    int grid[n][m];
    for (int i = 0; i < n; i++) {
        string line;
        cin >> line;
        for (int j = 0; j < m; j++) {
            if (line[j] == 'O') {
                grid[i][j] = 0;
            } else if (line[j] == 'D') {
                grid[i][j] = 1;
            } else {
                grid[i][j] = 2;
            }
        }
    }

    int rsum[n][m];
    int csum[n][m];
    for (int i = n - 1; i >= 0; i--) {
        for (int j = m - 1; j >= 0; j--) {
            rsum[i][j] = (j + 1 < m ? rsum[i][j + 1] : 0) + (grid[i][j] == 1);
            csum[i][j] = (i + 1 < n ? csum[i + 1][j] : 0) + (grid[i][j] == 1);
        }
    }

    int dp[n][m][2];
    int rows[n][m];
    int cols[n][m];
    for (int i = n - 1; i >= 0; i--) {
        for (int j = m - 1; j >= 0; j--) {
            dp[i][j][0] = INT_MIN;
            dp[i][j][1] = INT_MIN;
            if (grid[i][j] == 2) {
                if (j < m - 1) {
                    if (cols[i][j + 1] != INT_MIN) {
                        dp[i][j][1] = max(dp[i][j][1], cols[i][j + 1] + csum[i][j]);
                    }
                } else {
                    dp[i][j][1] = max(dp[i][j][1], csum[i][j]);
                }

                if (i < n - 1) {
                    if (rows[i + 1][j] != INT_MIN) {
                        dp[i][j][1] = max(dp[i][j][1], rows[i + 1][j] + rsum[i][j]);
                    }
                } else {
                    dp[i][j][1] = max(dp[i][j][1], rsum[i][j]);
                }
            } else {
                if (i == n - 1 && j == m - 1) {
                    dp[i][j][0] = (grid[i][j] == 1);
                }
                if (j < m - 1) {
                    dp[i][j][1] = max(dp[i][j][1], dp[i][j + 1][1] + (grid[i][j] == 1));
                    dp[i][j][0] = max(dp[i][j][0], dp[i][j + 1][0] + (grid[i][j] == 1));
                }
                if (i < n - 1) {
                    dp[i][j][1] = max(dp[i][j][1], dp[i + 1][j][1] + (grid[i][j] == 1));
                    dp[i][j][0] = max(dp[i][j][0], dp[i + 1][j][0] + (grid[i][j] == 1));
                }
            }

            if (i > 0) {
                rows[i][j] = (j < m - 1 ? rows[i][j + 1] : INT_MIN);
                if (dp[i][j][0] >= 0) {
                    rows[i][j] = max(rows[i][j], dp[i][j][0] - (j < m - 1 ? rsum[i - 1][j + 1] : 0));
                }
            }
            if (j > 0) {
                cols[i][j] = (i < n - 1 ? cols[i + 1][j] : INT_MIN);
                if (dp[i][j][0] >= 0) {
                    cols[i][j] = max(cols[i][j], dp[i][j][0] - (i < n - 1 ? csum[i + 1][j - 1] : 0));
                }
            }
        }
    }

    int res = max(dp[0][0][0], dp[0][0][1]);
    if (res < 0) {
        cout << "IMPOSSIBLE\n";
    } else {
        cout << res << "\n";
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