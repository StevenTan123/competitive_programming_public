#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k;
    cin >> n >> k;
    
    // dp[i][j] = # ways to split 1...i into j segments where no segment has 
    // length 2. For j == k, it also counts splitting into > j segments.
    vector<vector<long long>> dp(n + 1, vector<long long>(k + 1));
    // dp_sum[i][j] = sum of dp[0...i][j].
    vector<vector<long long>> dp_sum(n + 1, vector<long long>(k + 1));
    dp[0][0] = 1;
    for (int i = 0; i <= n; i++) {
        dp_sum[i][0] = 1;
    }
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= k; j++) {
            if (i == 2 || i == n) {
                dp[i][j] += dp_sum[i - 1][j - 1];
                if (j == k) {
                    dp[i][j] += dp_sum[i - 1][j];
                }
            } else {
                if (i >= 3) {
                    dp[i][j] += dp_sum[i - 3][j - 1];
                    if (j == k) {
                        dp[i][j] += dp_sum[i - 3][j];
                    }
                    dp[i][j] %= MOD;
                }
                dp[i][j] += dp[i - 1][j - 1];
                if (j == k) {
                    dp[i][j] += dp[i - 1][j];
                }
            }
            dp[i][j] %= MOD;
            dp_sum[i][j] = (dp_sum[i - 1][j] + dp[i][j]) % MOD;
        }
    }
    cout << dp[n][k] << "\n";
}