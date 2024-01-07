#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    int GCD_gap = 0;
    int max_val = INT_MIN;
    set<long long> unique;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        if (i > 0) {
            GCD_gap = __gcd(GCD_gap, abs(a[i] - a[i - 1]));
        }
        max_val = max(max_val, a[i]);
        unique.insert(a[i]);
    }
    if (GCD_gap == 0) {
        cout << 1 << endl;
        return;
    }
    sort(a, a + n);

    long long ops = 0;
    for (int i = 0; i < n; i++) {
        ops += (max_val - a[i]) / GCD_gap;
    }
    long long res = ops + n;
    int p = n - 1;
    for (int i = 1; i < n; i++) {
        long long val = max_val - (long long) i * GCD_gap;
        if (unique.find(val) == unique.end()) {
            res = ops + i;
            break;
        }
    }
    cout << res << endl;
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