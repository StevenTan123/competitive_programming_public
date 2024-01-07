#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
const int MAXN = 5005;
long long pow2[MAXN];

long long dp[MAXN][MAXN][2];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    pow2[0] = 1;
    for (int i = 1; i < MAXN; i++) {
        pow2[i] = pow2[i - 1] * 2 % MOD;
    }

    int N;
    cin >> N;
    int A[N];
    for (int i = 0; i < N; i++) {
        cin >> A[i];
    }

    // dp[i][j][k] = # graphs satisfying BFS up to ordering of A[0...i] and A[j] being the front of the queue,
    // k = 0/1 determines if front of queue has children.
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            dp[i][j][0] = 0;
            dp[i][j][1] = 0;
        }
    }
    long long res = 0;
    dp[0][0][0] = 1;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j <= i; j++) {
            if (j < i) {
                dp[i][j + 1][0] += (dp[i][j][0] + dp[i][j][1]) % MOD;
                dp[i][j + 1][0] %= MOD;
            } else if (i == N - 1 && j == N - 1) {
                res = (dp[i][j][0] + dp[i][j][1]) % MOD;
            }

            if (i < N - 1) {
                dp[i + 1][j][1] += dp[i][j][0] * pow2[i - j] % MOD;
                if (A[i + 1] > A[i]) {
                    dp[i + 1][j][1] += dp[i][j][1] * pow2[i - j] % MOD;
                }
                dp[i + 1][j][1] %= MOD;
            }
        }
    }
    cout << res << endl;
}