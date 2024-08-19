#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i]--;
    }

    // dp[i][j][k] = max remove from range a[i...j] given k has been removed from the left of the range.
    vector<vector<vector<int>>> dp(n, vector<vector<int>>(n, vector<int>(n)));
    for (int i = n - 1; i >= 0; i--) {
        for (int j = i; j < n; j++) {
            for (int k = 0; k <= i; k += 2) {
                if (k > 0) {
                    dp[i][j][k] = max(dp[i][j][k], dp[i][j][k - 2]);
                }
                if (i - a[i] == k && j > i) {
                    dp[i][j][k] = max(dp[i][j][k], dp[i + 1][j - 1][k] + 2);
                }
                for (int x = i; x < j; x++) {
                    dp[i][j][k] = max(dp[i][j][k], dp[i][x][k] + dp[x + 1][j][k + dp[i][x][k]]);
                }
            }
        }    
    }

    int res = dp[0][n - 1][0] / 2;
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