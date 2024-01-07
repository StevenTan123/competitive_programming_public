#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k, m;
    cin >> n >> k >> m;
    int b[m];
    for (int i = 0; i < m; i++) {
        cin >> b[i];
    }

    if ((n - m) % (k - 1) != 0) {
        cout << "NO\n";
    } else {
        bool possible = false;
        for (int i = 0; i < m; i++) {
            int l_del = b[i] - 1 - i;
            int r_del = n - b[i] - (m - i - 1);
            if (l_del >= (k - 1) / 2 && r_del >= (k - 1) / 2) {
                possible = true;
                break;
            }
        }
        cout << (possible ? "YES\n" : "NO\n");
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