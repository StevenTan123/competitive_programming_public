#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N;
    double p[N];
    for (int i = 0; i < N; i++) {
        cin >> p[i];
    }

    // dp[i][j] = probability that after placing i blocks while removing illegal ones, current height is j.
    vector<vector<double>> dp(N + 1, vector<double>(N + 1));
    dp[0][0] = 1;
    double exp_remove = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j <= i; j++) {
            dp[i + 1][j + 1] += dp[i][j] * p[i];
            double p_drop = dp[i][j] * (1 - p[i]);
            if (j > 0) {
                dp[i + 1][j - 1] += p_drop;
            } else {
                exp_remove += p_drop;
                dp[i + 1][j] += p_drop;
            }
        }
    }
    for (int j = 1; j <= N; j++) {
        exp_remove += dp[N][j] * j;
    }
    cout << fixed << setprecision(10) << N - exp_remove << "\n";
}