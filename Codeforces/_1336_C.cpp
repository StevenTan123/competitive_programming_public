#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string S, T;
    cin >> S >> T;
    // dp[i][j] = # ways to use 0...i of S to match j...j + i of T.
    vector<vector<long long>> dp(S.length(), vector<long long>(T.length() + 1));
    dp[0][T.length()] = 2;
    for (int i = 0; i < T.length(); i++) {
        if (S[0] == T[i]) {
            dp[0][i] = 2;
        }
    }
    for (int i = 0; i < S.length() - 1; i++) {
        for (int j = 0; j < T.length(); j++) {
            if (j + i + 1 >= T.length() || S[i + 1] == T[j + i + 1]) {
                dp[i + 1][j] += dp[i][j];
                dp[i + 1][j] %= MOD;
            }
            if (j > 0 && S[i + 1] == T[j - 1]) {
                dp[i + 1][j - 1] += dp[i][j];
                dp[i + 1][j - 1] %= MOD;
            }
        }
        dp[i + 1][T.length()] += 2 * dp[i][T.length()];
        dp[i + 1][T.length()] %= MOD;
        if (S[i + 1] == T[T.length() - 1]) {
            dp[i + 1][T.length() - 1] += dp[i][T.length()];
            dp[i + 1][T.length() - 1] %= MOD;
        } 
    }
    long long res = 0;
    for (int i = T.length() - 1; i < S.length(); i++) {
        res += dp[i][0];
        res %= MOD;
    }
    cout << res << endl;
}