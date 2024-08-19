#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    
    int a[n];
    int res = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        if (i % 2 == 0) {
            res = max(res, a[i]);
        }
    }
    cout << res << "\n";
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