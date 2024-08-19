#pragma GCC optimize("O3,unroll-loops")
#include <bits/stdc++.h>
using namespace std;

const double epsilon = 1e-10;
const int MAXT = 101;
const int MAXN = 17;
const int MAXPOW = 1 << MAXN;

// probs[i] = probability a team solves problem i at time ti.
double probs[MAXN];
// dp[ti][mask] = probability a team having read problems mask picks something to read at time ti.
double dp[MAXT][MAXPOW];
// dp2[ti][mask][i] = probability a team having read problems mask picks problem i to read at time ti.
double dp2[MAXT][MAXPOW][MAXN];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, t;
    cin >> n >> t;
    int r[n];
    int c[n];
    double p[n];
    for (int i = 0; i < n; i++) {
        cin >> r[i] >> c[i] >> p[i];
    }

    int pow2 = 1 << n;
    for (int i = 0; i < n; i++) {
        probs[i] = 0;
    }
    for (int ti = 0; ti <= t; ti++) {
        for (int mask = 0; mask < pow2; mask++) {
            dp[ti][mask] = 0;
            for (int i = 0; i < n; i++) {
                if (ti - r[i] - c[i] >= 0) {
                    double add = dp2[ti - r[i] - c[i]][mask][i] * p[i];
                    probs[i] += add;
                    dp[ti][mask] += add;
                }
                if (ti - r[i] >= 0) {
                    dp[ti][mask] += dp2[ti - r[i]][mask][i] * (1 - p[i]);
                }
                dp2[ti][mask][i] = 0;
            }
        }
        if (ti == 0) {
            dp[0][0] = 1;
        }
        for (int mask = 0; mask < pow2 - 1; mask++) {
            double total = 0;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (!(mask & (1 << i))) {
                    total += probs[i];
                    cnt++;
                }
            }
            for (int i = 0; i < n; i++) {
                int nmask = mask | (1 << i);
                if (nmask != mask) {
                    double p_pick = total < epsilon ? ((double) 1 / cnt) : (probs[i] / total);
                    dp2[ti][nmask][i] += dp[ti][mask] * p_pick;
                }
            }
        }
    }

    for (int i = 0; i < n; i++) {
        cout << fixed << setprecision(10) << probs[i] << "\n";
    }
}