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
    int n, l, r;
    cin >> n >> l >> r;
    
    int free = min(1 - l, r - n);
    long long res = free * nck(n, n / 2) % MOD;
    if (n % 2 == 1) {
        res = (res * 2) % MOD;
    }
    int cur = free + 1;
    while (true) {
        int plus_rb = min(r - cur, n);
        int sub_lb = max(l + cur, 1);
        if (n % 2 == 0) {
            if (plus_rb < n / 2 || sub_lb > n / 2 + 1) {
                break;
            }
            int overlap = plus_rb - sub_lb + 1;
            int left = n / 2 - (sub_lb - 1);
            res += nck(overlap, left);
        } else {
            if (plus_rb < n / 2 || sub_lb > n / 2 + 2 || plus_rb + 1 < sub_lb) {
                break;
            }
            int overlap = plus_rb - sub_lb + 1;
            int left = n / 2 - (sub_lb - 1);
            res += nck(overlap, left);
            if (plus_rb >= n / 2 + 1) {
                res += nck(overlap, left + 1);
            }
        }
        res %= MOD;
        cur++;
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
    while (t-- > 0) {
        solve();
    }
}