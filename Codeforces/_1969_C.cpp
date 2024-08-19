#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    // dp[i][j] = min sum of 0...i having used j operations.
    vector<vector<long long>> dp(n, vector<long long>(k + 1, LONG_LONG_MAX));
    dp[0][0] = a[0];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= min(i, k); j++) {
            if (i > 0 && dp[i - 1][j] != LONG_LONG_MAX) {
                dp[i][j] = dp[i - 1][j] + a[i];
            }
            int min_range = a[i];
            int prev_j = j - 1;
            for (int x = i - 1; x >= max(0, i - j); x--) {
                min_range = min(min_range, a[x]);
                if (x > 0 && dp[x - 1][prev_j] != LONG_LONG_MAX) {
                    dp[i][j] = min(dp[i][j], (x > 0 ? dp[x - 1][prev_j] : 0) + (long long) min_range * (i - x + 1));
                } else if (x == 0 && prev_j == 0) {
                    dp[i][j] = min(dp[i][j], (long long) min_range * (i - x + 1));
                }
                prev_j--;
            }
        }
    }
    long long res = LONG_LONG_MAX;
    for (int i = 0; i <= k; i++) {
        res = min(res, dp[n - 1][i]);
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