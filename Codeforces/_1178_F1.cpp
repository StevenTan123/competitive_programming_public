#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    int c[n];
    for (int i = 0; i < n; i++) {
        cin >> c[i];
        c[i]--;
    }

    vector<vector<long long>> dp(n + 1, vector<long long>(n + 1, 1));
    for (int i = n - 1; i >= 0; i--) {
        int min_ind = i;
        for (int j = i + 1; j < n; j++) {
            if (c[j] < c[min_ind]) {
                min_ind = j;
            }
            long long right_sum = 0;
            for (int k = min_ind; k <= j; k++) {
                right_sum += dp[min_ind + 1][k] * dp[k + 1][j] % MOD;
            }
            right_sum %= MOD;

            dp[i][j] = 0;
            for (int k = i; k <= min_ind; k++) {
                dp[i][j] += (k > 0 ? dp[i][k - 1] : 1) * (min_ind > 0 ? dp[k][min_ind - 1] : 1) % MOD * right_sum % MOD;
            }
            dp[i][j] %= MOD;
        }
    }
    cout << dp[0][n - 1] << endl; 
}