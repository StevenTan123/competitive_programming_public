#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    vector<int> cnts(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        cnts[a[i] - 1]++;
    }
    vector<int> b;
    for (int i = 0; i < n; i++) {
        if (cnts[i] > 0) {
            b.push_back(cnts[i]);
        }
    }
    int m = b.size();

    // dp[i][j] = max steps Alice is behind Bob if we consider cakes 1...i and
    // Bob has eaten j cakes.
    // dp[i][j] = max(dp[i - 1][j] + 1, dp[i - 1][j - 1] - b[i])
    vector<vector<int>> dp(m + 1, vector<int>(m + 1, -1));
    dp[0][0] = 0;
    int res = m;
    for (int i = 1; i <= m; i++) {
        for (int j = 0; j <= m; j++) {
            if (dp[i - 1][j] >= 0) {
                dp[i][j] = dp[i - 1][j] + 1;
            }
            if (j > 0 && dp[i - 1][j - 1] >= 0) {
                dp[i][j] = max(dp[i][j], dp[i - 1][j - 1] - b[i - 1]);
            }
            if (i == m && dp[i][j] >= 0) {
                res = m - j;
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