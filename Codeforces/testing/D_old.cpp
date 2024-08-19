#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;

    int panda = 1;
    int has = 0;
    cout << n << "\n";
    for (int i = 0; i < m; i++) {
        if (m - has > n) {
            has += n;
            cout << n << " " << panda << " 0 1\n";
        } else {
            cout << m - has << " " << panda << " " << n - (m - has) << " " << (panda < n ? panda + 1 : 1) << "\n";
            panda++;
            has = n - (m - has);
        }
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