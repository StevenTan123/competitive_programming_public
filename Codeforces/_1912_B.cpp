#include <bits/stdc++.h>
using namespace std;

const int MAXK = 1000005;
const long long MOD = 998244353;

long long fact[MAXK];
long long inv_fact[MAXK];

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

long long binom(int n, int k) {
    return fact[n] * inv_fact[k] % MOD * inv_fact[n - k] % MOD;
}

// Inconvenience of a block of x seats
long long inc(int x) {
    if (x <= 2) {
        return 0;
    }
    if (x % 2 == 0) {
        return (long long) (x / 2) * (x / 2 - 1);
    } else {
        return (long long) (x / 2) * (x / 2 - 1) + x / 2;
    }
}

void solve() {
    int n, k;
    cin >> n >> k;

    int ends = n / (2 * k);
    long long min_inc = LONG_LONG_MAX;
    for (int i = ends - 10; i <= ends + 10; i++) {
        if (i < 1 || n - i * 2 < k - 1) {
            continue;
        }
        
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    fact[0] = 1;
    for (int i = 1; i < MAXK; i++) {
        fact[i] = fact[i - 1] * i % MOD;
    }
    inv_fact[MAXK - 1] = modinv(fact[MAXK - 1]);
    for (int i = MAXK - 2; i >= 0; i--) {
        inv_fact[i] = inv_fact[i + 1] * i;
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}