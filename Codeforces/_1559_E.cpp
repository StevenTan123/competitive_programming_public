#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    int l[n];
    int r[n];
    for (int i = 0; i < n; i++) {
        cin >> l[i] >> r[i];
    }

    // cnt[i] = # arrays satisfying bounds with sum <= m and gcd of all elements a multiple of i
    vector<int> cnt(m / n + 2);
    for (int i = 1; i <= m / n + 1; i++) {
        // dp[j][k] = # arrays 0...j satisfying the conditions with sum = k 
        vector<vector<long long>> dp(n, vector<long long>(m / i + 1));
        vector<vector<long long>> sum(n, vector<long long>(m / i + 1));
        for (int j = 0; j < n; j++) {
            int low = l[j] / i + (l[j] % i == 0 ? 0 : 1);
            int high = r[j] / i;
            if (low > high) {
                continue;
            }
            for (int k = 0; k <= m / i; k++) {
                if (j == 0) {
                    if (k >= low && k <= high) {
                        dp[j][k] = 1;
                    } 
                } else {
                    dp[j][k] += (k - low >= 0 ? sum[j - 1][k - low] : 0) - (k - high > 0 ? sum[j - 1][k - high - 1] : 0);
                    dp[j][k] %= MOD;
                }
                sum[j][k] = (k > 0 ? sum[j][k - 1] : 0) + dp[j][k];
                sum[j][k] %= MOD;
            }
        }
        for (int j = 0; j <= m / i; j++) {
            cnt[i] += dp[n - 1][j];
            cnt[i] %= MOD;
        }
    }

    // res[i] = # arrays satisfying bounds with sum <= m and gcd of all elements = i
    vector<int> res(m / n + 2);
    for (int i = m / n + 1; i >= 1; i--) {
        res[i] = cnt[i];
        for (int j = i + i; j <= m / n + 1; j += i) {
            res[i] -= res[j];
            res[i] %= MOD;
        }
    }
    cout << (res[1] + MOD) % MOD << endl;
}