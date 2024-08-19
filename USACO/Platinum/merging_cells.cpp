#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;
const int MAXN = 5001;
long long inv[MAXN];

int L[MAXN][MAXN];
int R[MAXN][MAXN];
int dp[MAXN][MAXN] = {{0}};
int row_sum[MAXN][MAXN] = {{0}};
int col_sum[MAXN][MAXN] = {{0}};

long long binpow(long long a, long long b) {
    if (b == 0) {
        return 1;
    }
    long long c = binpow(a, b / 2);
    if (b % 2 == 0) {
        return c * c % MOD;
    } else {
        return c * c % MOD * a % MOD;
    }
}

long long modinv(long long a) {
    return binpow(a, MOD - 2);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 1; i < MAXN; i++) {
        inv[i] = modinv(i);
    }

    int N;
    cin >> N;
    int s[N];
    int psum[N];
    for (int i = 0; i < N; i++) {
        cin >> s[i];
        psum[i] = (i > 0 ? psum[i - 1] : 0) + s[i];
    }

    for (int i = N - 1; i >= 0; i--) {
        int l = i - 1;
        for (int j = i; j >= 0; j--) {
            l = min(l, j - 1);
            long long lrsum = psum[i] - (j > 0 ? psum[j - 1] : 0);
            long long esum = (j > 0 ? psum[j - 1] : 0) - (l > 0 ? psum[l - 1] : 0);
            while (l >= 0 && esum <= lrsum) {
                l--;
                esum = (j > 0 ? psum[j - 1] : 0) - (l > 0 ? psum[l - 1] : 0);
            }
            L[j][i] = l + 1;
        }
    }
    for (int i = 0; i < N; i++) {
        int r = i + 1;
        for (int j = i; j < N; j++) {
            r = max(r, j + 1);
            long long lrsum = psum[j] - (i > 0 ? psum[i - 1] : 0);
            long long esum =  psum[r] - psum[j];
            while (r < N && esum < lrsum) {
                r++;
                esum = psum[r] - psum[j];
            }
            R[i][j] = r - 1;
        }
    }

    dp[0][N - 1] = 1;
    for (int i = 0; i < N; i++) {
        for (int j = N - 1; j >= 0; j--) {
            if (i > 0) {
                // sum dp[L[i][j]][j] to dp[i - 1][j]
                if (L[i][j] <= i - 1) {
                    dp[i][j] += (col_sum[i - 1][j] - (L[i][j] > 0 ? col_sum[L[i][j] - 1][j] : 0)) % MOD;
                    dp[i][j] %= MOD;
                }
            }
            if (j < N - 1) {
                // sum dp[i][j + 1] to dp[i][R[i][j]]
                if (R[i][j] >= j + 1) {
                    dp[i][j] += (row_sum[i][j + 1] - (R[i][j] < N - 1 ? row_sum[i][R[i][j] + 1] : 0)) % MOD;
                    dp[i][j] %= MOD;
                }
            }
            col_sum[i][j] = ((i > 0 ? col_sum[i - 1][j] : 0) + dp[i][j] * inv[j - i]) % MOD;
            row_sum[i][j] = ((j < N - 1 ? row_sum[i][j + 1] : 0) + dp[i][j] * inv[j - i]) % MOD;
        }
    }
    for (int i = 0; i < N; i++) {
        long long cur = (dp[i][i] + MOD) % MOD;
        cout << cur << "\n";
    }    
}