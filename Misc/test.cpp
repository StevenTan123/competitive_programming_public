#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, k;
    cin >> n >> m >> k;

    vector<vector<bool>> grid(n, vector<bool>(m, 0));
    for (int i = 0; i < k; i++) {
        int r, c;
        cin >> r >> c;
        r--; c--;
        grid[r][c] = 1;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cout << grid[i][j];
        }
        cout << endl;
    }
}