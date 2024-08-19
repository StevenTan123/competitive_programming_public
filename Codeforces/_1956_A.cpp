#include <bits/stdc++.h>
using namespace std;

void solve() {
    int k, q;
    cin >> k >> q;
    int a[k];
    for (int i = 0; i < k; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < q; i++) {
        int n;
        cin >> n;
        cout << min(n, a[0] - 1) << " ";
    }
    cout << "\n";
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