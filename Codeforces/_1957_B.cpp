#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;

    if (n == 1) {
        cout << k << "\n";
    } else {
        int cur = 0;
        while (true) {
            int next = (cur << 1) | 1;
            if (next <= k) {
                cur = next;
            } else {
                break;
            }
        }
        cout << cur << " " << k - cur;
        for (int i = 2; i < n; i++) {
            cout << " " << 0;
        }
        cout << "\n";
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