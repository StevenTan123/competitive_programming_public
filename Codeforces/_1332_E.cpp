#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

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

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    long long n, m, L, R;
    cin >> n >> m >> L >> R;

    long long k = R - L + 1;
    long long p = n * m;
    if (p % 2 == 1) {
        cout << binpow(k, p) << "\n";
    } else {
        if (k % 2 == 0) {
            cout << binpow(k, p) * modinv(2) % MOD << "\n";
        } else {
            cout << ((binpow(k, p) - 1) * modinv(2) % MOD + MOD + 1) % MOD << "\n";
        }
    }
}