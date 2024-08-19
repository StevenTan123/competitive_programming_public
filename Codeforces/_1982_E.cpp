#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;
const int MAXK = 62;
long long pow2[MAXK];

// dp[i][j] = # j-good subarays in 0...2^i - 1
long long dp[MAXK][MAXK];

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

long long nc2(long long n) {
    n %= MOD;
    return n * (n - 1) % MOD * modinv(2) % MOD;
}

// Longest suffix of 0...2^n - 1 that is all <= k ones.
long long suf_subarray(int n, int k) {
    if (k >= n) {
        return pow2[n];
    }
    return 0;
}

// Longest prefix of 0...2^n - 1 that is all <= k ones.
long long pref_subarray(int n, int k) {
    if (k >= n) {
        return pow2[n];
    }
    long long res = 0;
    for (int i = 0; i <= k; i++) {
        res = (res << 1) | 1;
    }
    return res % MOD;
}

long long combine(int n1, int k1, int n2, int k2) {
    long long suf = suf_subarray(n1, k1);
    long long pref = pref_subarray(n2, k2);
    long long one = dp[n1][k1] - nc2(suf);
    long long two = dp[n2][k2] - nc2(pref);
    long long res = one + two + nc2(pref + suf);
    res %= MOD;
    return res;
}

void solve() {
    long long n;
    int k;
    cin >> n >> k;
    long long res = 0;
    long long suf = 0;
    int cnt = 0;
    for (int i = MAXK - 1; i >= 0; i--) {
        if (n & ((long long) 1 << i)) {
            if (cnt > k) {
                break;
            }
            long long left = res - nc2(suf);
            long long pref = pref_subarray(i, k - cnt);
            long long right = dp[i][k - cnt] - nc2(pref);
            res = left + right + nc2(suf + pref);
            res %= MOD;
            if (pref == pow2[i]) {
                suf += pref;
                suf %= MOD;
            } else {
                suf = suf_subarray(i, k - cnt);
            }
            cnt++;
        }
    }
    res = (res + MOD) % MOD;
    cout << res << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    pow2[0] = 1;
    for (int i = 0; i < MAXK; i++) {
        dp[i][0] = 1;
        for (int j = 1; j < MAXK; j++) {
            dp[i][j] = 0;
        }
        if (i > 0) {
            pow2[i] = pow2[i - 1] * 2 % MOD;
        }
    }
    for (int i = 0; i < MAXK; i++) {
        for (int j = 1; j < MAXK; j++) {
            if (j >= i) {
                dp[i][j] = (nc2(pow2[i]) + pow2[i]) % MOD;
            } else {
                dp[i][j] = combine(i - 1, j, i - 1, j - 1);
            }
        }
    }
    
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}