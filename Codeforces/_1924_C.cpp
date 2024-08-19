#include <bits/stdc++.h>
using namespace std;

const long long MOD = 999999893;

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

void solve() {
    int N;
    cin >> N;

    long long a, b, c, d;
    if (N % 2 == 1) {
        long long pow2 = binpow(2, (N + 1) / 2);
        a = pow2 - 2;
        b = 0;
        c = pow2 + 2;
        d = -2;
    } else {
        long long pow2 = binpow(2, N / 2);
        a = -2;
        b = pow2;
        c = 2;
        d = pow2 - 2;
    }

    long long num = b * c % MOD - a * d % MOD;
    long long den = c * c % MOD - 2 * d * d % MOD;
    long long res = num * modinv(den) % MOD;
    res = (res + MOD) % MOD;
    cout << res << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t-- > 0) {
        solve();
    }
}