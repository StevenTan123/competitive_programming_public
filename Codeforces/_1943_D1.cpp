#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    long long p;
    cin >> n >> k >> p;

    // dp[i][j][x] = # arrays 0...i with a[i] = j, a[i - 1] = x.
    vector<vector<vector<long long>>> dp(n, vector<vector<long long>>(k + 1, vector<long long>(k + 1)));
    for (int i = 0; i <= k; i++) {
        dp[0][i][0] = 1;
    }
    for (int i = 1; i < n; i++) {
        for (int j = 0; j <= k; j++) {
            for (int x = 1; x <= k; x++) {
                dp[i - 1][j][x] += dp[i - 1][j][x - 1];
                dp[i - 1][j][x] %= p;
            }
        }
        for (int j = 0; j <= k; j++) {
            for (int x = 0; x <= k; x++) {
                int prev = max(0, x - j);
                dp[i][j][x] += dp[i - 1][x][k] - (prev > 0 ? dp[i - 1][x][prev - 1] : 0);
                dp[i][j][x] %= p; 
            }
        }
    }
    
    long long res = 0;
    for (int i = 0; i <= k; i++) {
        for (int j = i; j <= k; j++) {
            res += dp[n - 1][i][j];
            res %= p;
        }
    }
    cout << (res + p) % p << "\n";
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