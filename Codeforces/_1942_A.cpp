#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;
    if (k == 1) {
        for (int i = 1; i <= n; i++) {
            cout << i << " ";
        }
        cout << "\n";
    } else if (k == n) {
        for (int i = 1; i <= n; i++) {
            cout << 1 << " ";
        }
        cout << "\n";
    } else {
        cout << "-1\n";
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