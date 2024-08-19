#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
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
    long long res = 1;
    for (int i = n; i > n - k; i--) {
        res *= i;
        res %= MOD;
    }
    res *= inv_fact[k];
    res %= MOD;
    return res;
}

void solve() {
    int n, k;
    cin >> n >> k;
    long long b[n + 1];
    for (int i = 1; i <= n; i++) {
        cin >> b[i];
    }
    vector<long long> a(n + 1);
    for (int i = 1; i <= n; i++) {
        a[i] = (b[i] - a[i] + MOD) % MOD;
        int d = 1;
        for (int j = i + (i & -i); j <= n; j += (j & -j)) {
            a[j] += a[i] * nck(d + k - 1, d);
            a[j] %= MOD;
            d++;
        }
    }
    for (int i = 1; i <= n; i++) {
        cout << a[i] << " ";
    }
    cout << "\n";
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