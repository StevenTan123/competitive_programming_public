#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k;
    cin >> n >> k;
    int a[n];
    int b[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    sort(a, a + n);
    for (int i = 0; i < n; i++) {
        cin >> b[i];
    }
    sort(b, b + n);

    // dp[i][j][k] = max/min of additional sa - sb given we have finished i moves and
    // P1 has picked j players and P2 has picked k players.
    vector<vector<vector<long long>>> dp(2 * n + 1, vector<vector<long long>>(k + 1, vector<long long>(k + 1)));
    for (int i = 2 * n - 1; i >= 0; i--) {
        int one_moves = (i + 1) / 2;
        int two_moves = i - one_moves;
        for (int j = min(k, one_moves); j >= 0; j--) {
            for (int x = min(k, two_moves); x >= 0; x--) {
                int one_gone = two_moves - x + j;
                int two_gone = one_moves - j + x;
                dp[i][j][x] = dp[i + 1][j][x];
                if (i % 2 == 0) {
                    // P1 turn.
                    if (j < k && one_gone < n) dp[i][j][x] = max(dp[i][j][x], dp[i + 1][j + 1][x] + a[n - 1 - one_gone]);
                } else {
                    // P2 turn.
                    if (x < k && two_gone < n) dp[i][j][x] = min(dp[i][j][x], dp[i + 1][j][x + 1] - b[n - 1 - two_gone]); 
                }
            }
        }
    }
    cout << dp[0][0][0] << "\n";
}