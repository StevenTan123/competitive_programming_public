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

void solve() {
    int l, n;
    cin >> l >> n;

    long long cnt = 0;
    for (int i = 0; i <= l; i++) {
        int sum = l - i - 2 * n;
        if (sum < 0 || sum % 2 != 0) {
            continue;
        }
        sum /= 2;

        long long cur_ways = nck(sum + n - 1, n - 1);
        // distribute i blanks into n + 1 buckets
        cur_ways *= nck(i + n, n);
        cur_ways %= MOD;
        cnt += cur_ways;
        cnt %= MOD;
    }
    // cnt stores number of ways to make all gaps even
    long long res = nck(l, 2 * n) - cnt;
    res = (res % MOD + MOD) % MOD;
    res = (res * 2) % MOD;
    cout << res << "\n";
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

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}