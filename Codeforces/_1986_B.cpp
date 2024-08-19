#include <bits/stdc++.h>
using namespace std;

int dirs[4][2] = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };

void solve() {
    int n, m;
    cin >> n >> m;
    int a[n][m];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];   
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int max_nei = 0;
            for (int k = 0; k < 4; k++) {
                int newi = i + dirs[k][0];
                int newj = j + dirs[k][1];
                if (newi >= 0 && newi < n && newj >= 0 && newj < m) {
                    max_nei = max(max_nei, a[newi][newj]);
                }
            }
            a[i][j] = min(a[i][j], max_nei);
            cout << a[i][j] << " ";
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