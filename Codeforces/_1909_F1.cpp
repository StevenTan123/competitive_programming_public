#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

void solve() {
    int n;
    cin >> n;
    int a[n];
    bool possible = true;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        if (a[i] > i + 1 || (i > 0 && (a[i] > a[i - 1] + 2 || a[i] < a[i - 1]))) {
            possible = false;
        }
    }
    if (a[n - 1] != n) {
        possible = false;
    }

    if (possible) {
        long long res = 1;
        for (int i = 1; i < n; i++) {
            if (a[i] == a[i - 1] + 1) {
                res *= 2 * i + 1 - 2 * a[i - 1];
                res %= MOD;
            } else if (a[i] == a[i - 1] + 2) {
                res *= (long long) (i - a[i - 1]) * (i - a[i - 1]) % MOD;
                res %= MOD;
            }
        }
        cout << res << "\n";
    } else {
        cout << "0\n";
    }
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