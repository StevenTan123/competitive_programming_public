#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
const int MAXN = 200005;
const int ALPH = 26;

long long fact[MAXN];
long long inv_fact[MAXN];
long long dp[ALPH][MAXN] = {{0}};
long long dp_sum[ALPH][MAXN] = {{0}};

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

long long nck(int n, int k) {
    return fact[n] * inv_fact[n - k] % MOD * inv_fact[k] % MOD;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 0; i < MAXN; i++) {
        if (i == 0) {
            fact[i] = 1;
        } else {
            fact[i] = fact[i - 1] * i % MOD;
        }
    }
    inv_fact[MAXN - 1] = modinv(fact[MAXN - 1]);
    for (int i = MAXN - 2; i >= 0; i--) {
        inv_fact[i] = inv_fact[i + 1] * (i + 1) % MOD;
    }
    
    int N, M;
    cin >> N >> M;
    string A, B;
    cin >> A >> B;

    int cntA[ALPH] = {0};
    int cntB[ALPH + 1] = {0};
    for (int i = 0; i < N; i++) {
        cntA[A[i] - 'A']++;
    }
    for (int i = 0; i < M; i++) {
        cntB[B[i] - 'A']++;
    }

    // dp[i][j] = # ways to subset and permute the first i letters of B to match
    // the first i letters of A in order, using exactly j of letter i + 1
    for (int i = 0; i <= min(cntA[0], cntB[1]); i++) {
        if (cntB[0] + i >= cntA[0]) {
            dp[0][i] = nck(cntA[0], i);
        }
        dp_sum[0][i] = ((i > 0 ? dp_sum[0][i - 1] : 0) + dp[0][i]) % MOD;
    }
    for (int i = 1; i < ALPH; i++) {
        for (int j = 0; j <= min(cntA[i], cntB[i + 1]); j++) {
            // dp[i][j] += dp[i - 1][k], where cntB[i] - k + j >= cntA[i]
            int maxk = min(cntB[i] - cntA[i] + j, min(cntA[i - 1], cntB[i]));
            if (maxk >= 0) {
                dp[i][j] += dp_sum[i - 1][maxk] * nck(cntA[i], j);
                dp[i][j] %= MOD;
            }
            dp_sum[i][j] = ((j > 0 ? dp_sum[i][j - 1] : 0) + dp[i][j]) % MOD;
        }
    }

    long long res = dp[ALPH - 1][0] * fact[N] % MOD;
    for (int i = 0; i < ALPH; i++) {
        res = res * inv_fact[cntA[i]] % MOD;
    }
    cout << res << endl;
}