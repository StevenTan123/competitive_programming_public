#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;
const int MAXN = 200005;

long long pow2[MAXN];

void solve() {
    int n, k;
    cin >> n >> k;
    int a[n];
    long long tot = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        tot += a[i];
    }
    tot %= MOD;

    long long max_sum = 0;
    long long cur_sum = 0;
    for (int i = 0; i < n; i++) {
        cur_sum += a[i];
        max_sum = max(max_sum, cur_sum);
        if (cur_sum < 0) {
            cur_sum = 0;
        }
    }
    max_sum %= MOD;
    cur_sum %= MOD;

    long long left = tot - max_sum;
    long long res = (max_sum * pow2[k] + left) % MOD;
    res = (res + MOD) % MOD;
    cout << res << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    pow2[0] = 1;
    for (int i = 1; i < MAXN; i++) {
        pow2[i] = pow2[i - 1] * 2 % MOD;
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}