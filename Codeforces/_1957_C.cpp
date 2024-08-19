#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;
const int MAXN = 300005;
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
    int n, k;
    cin >> n >> k;
    int left = n;
    for (int i = 0; i < k; i++) {
        int r, c;
        cin >> r >> c;
        if (r != c) {
            left -= 2;
        } else {
            left--;
        }
    }
    
    long long res = 0;
    for (int i = 0; i <= left; i++) {
        long long cur = nck(left, i);
        int left2 = left - i;
        if (left2 % 2 == 0) {
            cur *= fact[left2] * inv_fact[left2 / 2] % MOD;
            cur %= MOD;
            res += cur;
            res %= MOD;
        }
    }
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
