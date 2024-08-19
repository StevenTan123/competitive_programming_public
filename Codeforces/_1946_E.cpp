#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;
const int MAXN = 200005;

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
    int n, m1, m2;
    cin >> n >> m1 >> m2;
    int p[m1];
    for (int i = 0; i < m1; i++) {
        cin >> p[i];
        p[i]--;
    }
    int s[m2];
    for (int i = 0; i < m2; i++) {
        cin >> s[i];
        s[i]--;
    }
    if (p[m1 - 1] != s[0] || p[0] > 0 || s[m2 - 1] != n - 1) {
        cout << "0\n";
        return;
    }

    long long ways = nck(n - 1, s[0]);
    for (int i = m1 - 2; i >= 0; i--) {
        ways *= nck(p[i + 1] - 1, p[i]) * fact[p[i + 1] - p[i] - 1] % MOD;
        ways %= MOD;
    }
    for (int i = 1; i < m2; i++) {
        ways *= nck(n - s[i - 1] - 2, n - s[i] - 1) * fact[s[i] - s[i - 1] - 1] % MOD;
        ways %= MOD;
    }
    cout << ways << "\n";
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