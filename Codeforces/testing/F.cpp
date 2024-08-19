#include <bits/stdc++.h>
using namespace std;
 
const long long MOD = 1000000007;
const long long MAXV = 1000000000;
 
long long solve_sum(int n, string s, long long b[], long long sum) {
    // dp[i][0/1] = # consistent strings 0...i such that s[i] == P/S
    vector<vector<long long>> dp(n, vector<long long>(2));
    for (int i = 0; i < n; i++) {
        if (s[i] == 'P' || s[i] == '?') {
            if (i > 0) {
                if (i < n - 1 || b[i] == sum) {
                    if (abs(b[i] - b[i - 1]) <= MAXV) {
                        dp[i][0] += dp[i - 1][0];
                    }
                    if (abs(b[i] - (sum - b[i - 1])) <= 2 * MAXV) {
                        dp[i][0] += dp[i - 1][1];
                    }
                }
            } else {
                if (abs(b[0]) <= MAXV) {
                    dp[i][0] = 1;
                }
            }
        }
        if (s[i] == 'S' || s[i] == '?') {
            if (i > 0) {
                if (abs(b[i - 1] - b[i]) <= MAXV && (i < n - 1 || abs(b[i]) <= MAXV)) {
                    dp[i][1] += dp[i - 1][1];
                }
                if (b[i - 1] == sum - b[i] && (i < n - 1 || abs(b[i]) <= MAXV)) {
                    dp[i][1] += dp[i - 1][0];
                }
            } else {
                if (b[0] == sum) {
                    dp[i][1] = 1;
                }
            }
        }
        dp[i][0] %= MOD;
        dp[i][1] %= MOD;
    }
    return (dp[n - 1][0] + dp[n - 1][1]) % MOD;
}
 
void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    long long b[n];
    for (int i = 0; i < n; i++) {
        cin >> b[i];
    }

    set<long long> sums;
    if (s[0] != 'P') {
        sums.insert(b[0]);
    }
    for (int i = 1; i < n; i++) {
        if (s[i - 1] != 'S' && s[i] != 'P') {
            sums.insert(b[i - 1] + b[i]);
        }
    }
    if (s[n - 1] != 'S') {
        sums.insert(b[n - 1]);
    }

    long long res = 0;
    for (long long sum : sums) {
        res += solve_sum(n, s, b, sum);
        res %= MOD;
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