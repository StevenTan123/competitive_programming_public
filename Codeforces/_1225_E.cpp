#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    bool grid[n][m];
    for (int i = 0; i < n; i++) {
        string line;
        cin >> line;
        for (int j = 0; j < m; j++) {
            grid[i][j] = (line[j] == 'R');
        }
    }

    // rth[i][j] = the j-th rock from the right of row i
    vector<int> rth[n];
    // cth[i][j] = the j-th rock from the bottom of column i
    vector<int> cth[m];
    for (int i = 0; i < n; i++) {
        for (int j = m - 1; j >= 0; j--) {
            if (grid[i][j]) {
                rth[i].push_back(j);
            }
        }
    }
    for (int j = 0; j < m; j++) {
        for (int i = n - 1; i >= 0; i--) {
            if (grid[i][j]) {
                cth[j].push_back(i);
            }
        }
    }
    
    // dp[i][j][0/1] = # ways to reach row i, col j from the left/top (0/1).
    vector<vector<vector<long long>>> dp(n, vector<vector<long long>>(m, vector<long long>(2)));
    vector<vector<vector<long long>>> rsum(n, vector<vector<long long>>(m, vector<long long>(2)));
    vector<vector<vector<long long>>> csum(n, vector<vector<long long>>(m, vector<long long>(2)));
    dp[0][0][0] = 1;
    dp[0][0][1] = 1;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (j > 0) {
                // enter from the left
                int max_rocks = m - j - 1;
                int left = 0;
                if (max_rocks < rth[i].size()) {
                    left = min(j, rth[i][max_rocks]);
                }
                dp[i][j][0] = rsum[i][j - 1][1] - (left > 0 ? rsum[i][left - 1][1] : 0);
                dp[i][j][0] %= MOD;
            }
            if (i > 0) {
                // enter from the top
                int max_rocks = n - i - 1;
                int top = 0;
                if (max_rocks < cth[j].size()) {
                    top = min(i, cth[j][max_rocks]);
                }
                dp[i][j][1] = csum[i - 1][j][0] - (top > 0 ? csum[top - 1][j][0] : 0);
                dp[i][j][1] %= MOD;
            }
            rsum[i][j][0] = (j > 0 ? rsum[i][j - 1][0] : 0) + dp[i][j][0];
            rsum[i][j][0] %= MOD;
            rsum[i][j][1] = (j > 0 ? rsum[i][j - 1][1] : 0) + dp[i][j][1];
            rsum[i][j][1] %= MOD;
            csum[i][j][0] = (i > 0 ? csum[i - 1][j][0] : 0) + dp[i][j][0];
            csum[i][j][0] %= MOD;
            csum[i][j][1] = (i > 0 ? csum[i - 1][j][1] : 0) + dp[i][j][1];
            csum[i][j][1] %= MOD;
        }
    }
    long long res = (dp[n - 1][m - 1][0] + dp[n - 1][m - 1][1]) % MOD;
    if (n == 1 && m == 1) {
        res /= 2;
    }
    cout << (res + MOD) % MOD << endl;
}