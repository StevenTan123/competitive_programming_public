#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;

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
    int n, k;
    cin >> n >> k;
    int v[n];
    long long special_avg = 0;
    long long normal_avg = 0;
    long long sum = 0;
    for (int i = 0; i < n; i++) {
        cin >> v[i];
        sum += v[i];
        if (i < k) {
            special_avg += v[i];
        } else {
            normal_avg += v[i];
        }
    }
    special_avg = special_avg % MOD * modinv(k) % MOD;
    normal_avg = normal_avg % MOD * modinv(n - k) % MOD;

    long long alice_special_exp = k * modinv(n - k + 1) % MOD * ((n - k + 2) / 2) % MOD;
    long long alice = (n - k + 1) / 2 * normal_avg + alice_special_exp * special_avg % MOD;
    alice %= MOD;
    long long bob = (sum - alice) % MOD + MOD;
    bob %= MOD;
    cout << alice << " " << bob << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}