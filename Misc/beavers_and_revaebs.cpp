#include <bits/stdc++.h>
using namespace std;
 
const long long MOD = 1000000007;
const int MAXN = 55;
const int MAXV = 2005;
 
long long dp[MAXN][MAXN][2][MAXV];
long long dp_sum[MAXN][MAXN][2][MAXV];
 
long long sum_range(int i, int j, int k, int l, int r) {
    if (r < 0 || l >= MAXV) {
        return 0;
    }
    long long sub = dp_sum[i][j][k][min(r, MAXV - 1)] - (l > 0 ? dp_sum[i][j][k][l - 1] : 0);
    sub = (sub + MOD) % MOD;
    return sub;
}
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
 
    for (int i = 0; i < MAXN; i++) {
        for (int j = 0; j < MAXN; j++) {
            for (int k = 0; k < 2; k++) {
                for (int l = 0; l < MAXV; l++) {
                    dp[i][j][k][l] = 0;
                    dp_sum[i][j][k][l] = 0;
                }
            }
        }
    }
 
    int N;
    cin >> N;
    int l[N];
    int r[N];
    for (int i = 0; i < N; i++) {
        cin >> l[i] >> r[i];
    }
 
    if (N == 1) {
        cout << (r[0] - l[0] + 1) << endl;
        return 0;
    }
 
    dp[0][N + 1][0][0] = 1;
    for (int i = 0; i < N; i++) {
        for (int j = N + 1; j > i; j--) {
            for (int a = 0; a < MAXV; a++) {
 
                if (i > 0 && a > 0) {
                    if (j > i + 1) {
                        dp[i][j][0][a] += sum_range(i - 1, j, 0, a - r[i - 1], a - l[i - 1]);
                    }
                    dp[i][j][0][a] += sum_range(i - 1, j, 1, l[i - 1] - a, r[i - 1] - a);
                    dp[i][j][0][a] %= MOD;
                }
 
                if (j < N + 1 && a > 0) {
                    if (j > i + 1) {
                        dp[i][j][1][a] += sum_range(i, j + 1, 1, a - r[j - 1], a - l[j - 1]);
                    }
                    dp[i][j][1][a] += sum_range(i, j + 1, 0, l[j - 1] - a, r[j - 1] - a);
                    dp[i][j][1][a] %= MOD;
                }
 
                dp_sum[i][j][0][a] = (a > 0 ? dp_sum[i][j][0][a - 1] : 0) + dp[i][j][0][a];
                dp_sum[i][j][0][a] %= MOD;
                dp_sum[i][j][1][a] = (a > 0 ? dp_sum[i][j][1][a - 1] : 0) + dp[i][j][1][a];
                dp_sum[i][j][1][a] %= MOD;
            }
        }
    }
 
    long long res = 0;
    for (int i = 1; i < N; i++) {
        res += dp_sum[i][i + 1][0][MAXV - 1];
        res += dp_sum[i][i + 1][1][MAXV - 1];
        res %= MOD;
    }
    cout << res << endl;
}