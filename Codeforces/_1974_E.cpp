#include <bits/stdc++.h>
using namespace std;

void solve() {
    int m, x;
    cin >> m >> x;

    int c[m];
    int h[m];
    int MAXH = 0;
    for (int i = 0; i < m; i++) {
        cin >> c[i] >> h[i];
        MAXH += h[i];
    }
    MAXH++;
    
    vector<vector<long long>> dp(m, vector<long long>(MAXH, -1));
    dp[0][0] = 0;
    if (c[0] == 0) {
        dp[0][h[0]] = 0;
    }
    int res = 0;
    for (int i = 1; i < m; i++) {
        for (int j = 0; j < MAXH; j++) {
            if (dp[i - 1][j] != -1) {
                dp[i][j] = dp[i - 1][j] + x;
            }
            if (j >= h[i] && dp[i - 1][j - h[i]] != -1) {
                dp[i][j] = max(dp[i][j], dp[i - 1][j - h[i]] + x - c[i]);
            }
            if (i == m - 1 && dp[i][j] != -1) {
                res = max(res, j);
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