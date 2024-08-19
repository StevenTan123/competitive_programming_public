#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    long long minv = 0;
    long long maxv = 0;
    long long cnt_min = 1;
    long long cnt_max = 1;
    for (int i = 0; i < n; i++) {
        long long pmin = minv;
        long long pmax = maxv;
        minv += a[i];
        maxv = max(abs(minv), abs(maxv + a[i]));
        long long n_cnt_max = 0;
        long long n_cnt_min = 0;
        if (minv >= 0) {
            n_cnt_min = cnt_min * 2 % MOD;
        } else {
            n_cnt_min = cnt_min;
        }
        if (abs(pmax + a[i]) == maxv) {
            if (pmax + a[i] >= 0) {
                n_cnt_max = 2 * cnt_max % MOD;
            } else {
                n_cnt_max = cnt_max;
            }
        }
        if (abs(minv) == maxv && !(abs(minv) == abs(pmax + a[i]) && pmin == pmax)) {
            if (minv >= 0) {
                n_cnt_max += 2 * cnt_min; 
                n_cnt_max %= MOD;
            } else {
                n_cnt_max += cnt_min;
                n_cnt_max %= MOD; 
            }
        } 
        cnt_min = n_cnt_min;
        cnt_max = n_cnt_max;
    }
    cout << cnt_max << "\n";
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