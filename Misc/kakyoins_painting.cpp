#include <bits/stdc++.h>
using namespace std;

const int MOD = 1e9 + 7;

using ll = long long;

ll fact [2000005][2];

ll modadd(ll a, ll b) {
    return (a + b + MOD) % MOD;
}

ll modmult(ll a, ll b) {
    return a * b % MOD;
}

ll binpow(ll a, ll b) {
    if (b == 0) {
        return 1;
    }
    ll small = binpow(a, b / 2);
    if (b % 2 == 0) {
        return modmult(small, small);
    }
    else {
        return modmult(modmult(small, small), a);
    }
}

ll modinv(ll a) {
    return binpow(a, MOD - 2);
}

ll binom(int n, int k) {
    return modmult(modmult(fact[n][0], fact[n - k][1]), fact[k][1]);
}

void solve() {
    int n;
    cin >> n;
    ll sum = 0;
    for(int i = 1; i <= n; i++) {
        ll one = binom(n + i, 2 * i);
        ll add = modmult(modmult(modmult(one, one), fact[i][0]), i);
        sum = modadd(sum, add);
    }
    cout << sum << "\n";
}

int main() {
    fact[0][0] = 1;
    fact[0][1] = 1;
    for(int i = 1; i < 2000005; i++) {
        fact[i][0] = modmult(fact[i - 1][0], i);
        fact[i][1] = modinv(fact[i][0]);
    }
    int t;
    cin >> t;
    while(t--) {
        solve();
    }
}