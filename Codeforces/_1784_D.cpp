#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
const int MAXN = 1100005;

long long fact[MAXN];
long long inv_fact[MAXN];

long long binpow(long long a, long long b) {
    if (b == 0) {
        return 1;
    }
    long long c = binpow(a, b / 2);
    if (b % 2 == 0) {
        return c * c % MOD;
    } else {
        return c * c % MOD * a % MOD;
    }
}

long long modinv(long long a) {
    return binpow(a, MOD - 2);
}

long long npk(int n, int k) {
    if (k > n || k < 0) {
        return 0;
    }
    if (n == 0 && k == 0) {
        return 1;
    }
    return fact[n] * inv_fact[n - k] % MOD;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    fact[0] = 1;
    for (int i = 1; i < MAXN; i++) {
        fact[i] = fact[i - 1] * i % MOD;
    }
    inv_fact[MAXN - 1] = modinv(fact[MAXN - 1]);
    for (int i = MAXN - 2; i >= 0; i--) {
        inv_fact[i] = inv_fact[i + 1] * (i + 1) % MOD;
    }

    int n;
    cin >> n;
    int m = 1 << n;

    // dp[i][j] = given that player j is the winner of their sub-bracket of 2^i players,
    // how many ways are there to assign the rest of the bracket so that player j continues
    // the wooden spoon.
    vector<vector<long long>> dp(n + 1, vector<long long>(m));
    // dp_sum[i][j] = sum of dp[i][0...j] * _
    vector<vector<long long>> dp_sum(n + 1, vector<long long>(m));
    dp[n][0] = 1;
    dp_sum[n][0] = (1 << (n - 1)) * npk(m - 1 - (1 << (n - 1)), (1 << (n - 1)) - 1);
    for (int i = 1; i < m; i++) {
        dp_sum[n][i] = dp_sum[n][i - 1];
    }

    for (int i = n - 1; i >= 0; i--) {
        for (int j = 1; j < m; j++) {
            dp[i][j] += dp_sum[i + 1][j - 1] * 2;
            dp[i][j] %= MOD;
        }
        if (i > 0) {
            for (int j = 1; j < m; j++) {
                int half = 1 << (i - 1);    
                long long add = dp[i][j] * half % MOD * npk(m - j - 1 - half, half - 1) % MOD;
                dp_sum[i][j] = dp_sum[i][j - 1] + add;
                dp_sum[i][j] %= MOD;
            }
        }
    }

    for (int i = 0; i < m; i++) {
        cout << dp[0][i] << "\n";
    }
}