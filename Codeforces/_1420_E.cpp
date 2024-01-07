#include <bits/stdc++.h>
using namespace std;
 
const int inf = 100000;
const int MAXN = 81;
int dp[MAXN][MAXN][MAXN * (MAXN - 1) / 2];
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    int a[n];
    int ones = 0;
    vector<int> one_inds;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        if (a[i] == 1) {
            one_inds.push_back(i);
            ones++;
        }
    }
    int zeros = n - ones;

    int max_moves = n * (n - 1) / 2;
    // dp[i][j][k] = min complement cost function, filling in 1...i with j 1's, k distance
    // from original, i-th value is a 1.
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
            for (int k = 0; k <= max_moves; k++) {
                dp[i][j][k] = inf;
            }
        }
    }
    dp[0][0][0] = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= min(ones - 1, i); j++) {
            for (int k = 0; k <= min(max_moves, i * (n - 1)); k++) {
                if (dp[i][j][k] != inf) {
                    for (int l = i + 1; l <= n; l++) {
                        dp[l][j + 1][k + abs(l - 1 - one_inds[j])] = min(dp[l][j + 1][k + abs(l - 1 - one_inds[j])], dp[i][j][k] + (l - i - 1) * (l - i - 2) / 2);
                    }
                }
            }
        }
    }
    
    int min_zero_sq = inf;
    for (int k = 0; k <= max_moves; k++) {
        for (int i = 0; i <= n; i++) {
            if (dp[i][ones][k] != inf) {
                int extra_cost = (n - i) * (n - i - 1) / 2;
                min_zero_sq = min(min_zero_sq, dp[i][ones][k] + extra_cost);
            }
        }
        cout << zeros * (zeros - 1) / 2 - min_zero_sq << " ";
    }
    cout << endl;
}