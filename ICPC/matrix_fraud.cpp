#include <bits/stdc++.h>
using namespace std;

int rsum(int *psum, int l, int r) {
    if (l > r) {
        return 0;
    }
    return psum[r] - (l > 0 ? psum[l - 1] : 0);
}

int cost(int n, int *psum, int l, int r) {
    int res = rsum(psum, 0, l - 1) + (r - l + 1 - rsum(psum, l, r)) + rsum(psum, r + 1, n - 1);
    return res;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int r, c;
    cin >> r >> c;
    bool grid[max(r, c)][min(r, c)];
    for (int i = 0; i < r; i++) {
        string line;
        cin >> line;
        for (int j = 0; j < c; j++) {
            bool val = line[j] == '1';
            if (r < c) {
                grid[j][i] = val;
            } else {
                grid[i][j] = val;
            }
        }
    }
    if (r < c) {
        swap(r, c);
    }
    int sum[r][c];
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            sum[i][j] = (j > 0 ? sum[i][j - 1] : 0) + grid[i][j]; 
        }
    }

    // dp[i][j][k] = min cost to make rows 0...i banded, last row with 1's from j...k
    vector<vector<vector<int>>> dp(r, vector<vector<int>>(c, vector<int>(c, INT_MAX)));
    for (int i = 0; i < c; i++) {
        dp[0][0][i] = cost(c, sum[0], 0, i);
    }
    for (int i = 1; i < r; i++) {
        for (int cc = 0; cc < c; cc++) {
            for (int rr = 1; rr < c; rr++) {
                dp[i - 1][rr][cc] = min(dp[i - 1][rr][cc], dp[i - 1][rr - 1][cc]);
            }
        }
        for (int j = 0; j < c; j++) {
            int prev_min = INT_MAX;
            if (j > 0) {
                prev_min = dp[i - 1][j][j - 1];
            }
            for (int k = j; k < c; k++) {
                prev_min = min(prev_min, dp[i - 1][j][k]);
                if (prev_min != INT_MAX) {
                    dp[i][j][k] = prev_min + cost(c, sum[i], j, k);
                }
            }
        }
    }
    int res = INT_MAX;
    for (int i = 0; i < c; i++) {
        res = min(res, dp[r - 1][i][c - 1]);
    }
    cout << res << endl;
}