#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, x;
    cin >> n >> x;
    int a[n + 2];
    a[0] = 0;
    a[n + 1] = x;
    int max_gap = 0;
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
        max_gap = max(max_gap, a[i] - a[i - 1]);
    }
    max_gap = max(max_gap, 2 * (a[n + 1] - a[n]));
    cout << max_gap << endl;
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