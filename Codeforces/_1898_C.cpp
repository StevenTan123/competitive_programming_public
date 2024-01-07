#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m, k;
    cin >> n >> m >> k;

    int moves = n - 1 + m - 1;
    int mod = k - moves % 4;
    if (k < moves || mod % 2 == 1) {
        cout << "NO" << endl;
    } else {
        cout << "YES" << endl;

        int vert[n - 1][m];
        int horz[n][m - 1];
        for (int i = 0; i < m - 1; i++) {
            horz[0][i] = i % 2;
        }
        for (int i = 0; i < n - 1; i++) {
            vert[i][m - 1] = (i + m - 1) % 2;
        }
        horz[1][0] = 0;
        vert[0][0] = 1;
        vert[0][1] = 1;

        int col = moves % 2;
        horz[n - 1][m - 2] = 1 - col;
        horz[n - 2][m - 2] = 1 - col;
        vert[n - 2][m - 2] = col;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                char c = 'R';
                if (horz[i][j] == 1) {
                    c = 'B';
                }
                cout << c << " ";
            }
            cout << endl;
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                char c = 'R';
                if (vert[i][j] == 1) {
                    c = 'B';
                }
                cout << c << " ";
            }
            cout << endl;
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