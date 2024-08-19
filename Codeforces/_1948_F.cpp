#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
const int MAXN = 1000005;

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

long long nck(int n, int k) {
    if (k > n) {
        return 0;
    }
    return fact[n] * inv_fact[n - k] % MOD * inv_fact[k] % MOD;
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

    int n, q;
    cin >> n >> q;
    int a[n];
    int b[n];
    int asum[n];
    int bsum[n];
    int m = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        asum[i] = (i > 0 ? asum[i - 1] : 0) + a[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> b[i];
        bsum[i] = (i > 0 ? bsum[i - 1] : 0) + b[i];
        m += b[i];
    }

    long long inv_pow2 = 1;
    for (int i = 0; i < m; i++) {
        inv_pow2 = inv_pow2 * 2 % MOD;
    }
    inv_pow2 = modinv(inv_pow2);

    long long mchoose[m + 1];
    for (int i = 0; i <= m; i++) {
        mchoose[i] = nck(m, i);
    }
    long long suf_sum[m + 1];
    for (int i = m; i >= 0; i--) {
        suf_sum[i] = (i < m ? suf_sum[i + 1] : 0) + mchoose[i];
        suf_sum[i] %= MOD;
    }

    for (int i = 0; i < q; i++) {
        int l, r;
        cin >> l >> r;
        l--; r--;

        int gold_in = asum[r] - (l > 0 ? asum[l - 1] : 0);
        int gold_out = asum[n - 1] - gold_in;
        int gold_diff = gold_out - gold_in;
        int silver_in = bsum[r] - (l > 0 ? bsum[l - 1] : 0);
        int silver_out = bsum[n - 1] - silver_in;
        int min_heads = gold_diff + silver_out + 1;
        long long res = 0;
        if (min_heads <= m) {
            res = suf_sum[max(min_heads, 0)] * inv_pow2 % MOD;
        }
        cout << res << " ";
    }
    cout << endl;
}
