#include <bits/stdc++.h>
using namespace std;

void cost(int n, int m, vector<vector<int>> &mat, vector<int> &costs) {
    vector<vector<int>> diffs(m, vector<int>(m));
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < m; j++) {
            for (int k = 0; k < n; k++) {
                diffs[i][j] += abs(mat[k][i] - mat[k][j]);
            }
        }
    }

    // dp[mask][i] = min cost permutation of cols in mask ending in col i
    vector<vector<int>> dp(1 << m, vector<int>(m, INT_MAX));
    for (int i = 0; i < m; i++) {
        dp[1 << i][i] = 0;
    }
    for (int mask = 0; mask < (1 << m); mask++) {
        for (int i = 0; i < m; i++) {
            if (dp[mask][i] != INT_MAX) {
                for (int j = 0; j < m; j++) {
                    int new_mask = mask | (1 << j);
                    if (new_mask != mask) {
                        dp[new_mask][j] = min(dp[new_mask][j], dp[mask][i] + diffs[i][j]);
                    }
                }
            }
        }
    }

    for (int i = 0; i < m; i++) {
        int mask = ((1 << m) - 1) ^ (1 << i);
        for (int j = 0; j < m; j++) {
            if (j != i) {
                costs[i] = min(costs[i], dp[mask][j]);
            }
        }
    }
}

void solve_mat(int n, int m, vector<vector<int>> &mat, vector<vector<int>> &costs) {
    for (int i = 0; i < n; i++) {
        vector<vector<int>> mat_drop(n - 1, vector<int>(m));
        int p = 0;
        for (int j = 0; j < n; j++) {
            if (j != i) {
                mat_drop[p++] = mat[j];
            }
        }
        cost(n - 1, m, mat_drop, costs[i]);
    }
}

void solve() {
    int n, m;
    cin >> n >> m;
    vector<vector<int>> rows(n + 1, vector<int>(m + 1));
    vector<vector<int>> cols(m + 1, vector<int>(n + 1));
    for (int i = 0; i < n; i++) {
        int xor_row = 0;
        for (int j = 0; j < m; j++) {
            cin >> rows[i][j];
            xor_row ^= rows[i][j];
        }
        rows[i][m] = xor_row; 
    }
    for (int j = 0; j <= m; j++) {
        int xor_col = 0;
        for (int i = 0; i < n; i++) {
            xor_col ^= rows[i][j];
        }
        rows[n][j] = xor_col;
    }
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= m; j++) {
            cols[j][i] = rows[i][j];
        }
    }

    vector<vector<int>> cost_rows(n + 1, vector<int>(m + 1, INT_MAX));
    vector<vector<int>> cost_cols(m + 1, vector<int>(n + 1, INT_MAX));
    solve_mat(n + 1, m + 1, rows, cost_rows);
    solve_mat(m + 1, n + 1, cols, cost_cols);
    int res = INT_MAX;
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= m; j++) {
            res = min(res, cost_rows[i][j] + cost_cols[j][i]);
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