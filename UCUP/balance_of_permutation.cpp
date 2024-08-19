#include <bits/stdc++.h>
using namespace std;

const int MAXN = 31;
// dp[i][j][k] = # partial permutations of 1...i with j unpaired elements in 1...i 
// and sum of right indices k.    
__uint128_t dp[2 * MAXN][MAXN][MAXN * MAXN];

__uint128_t string_to_uint128(string x) {
    __uint128_t res = 0;
    for (int i = 0; i < x.size(); i++) {
        res *= 10;
        res += (x[i] - '0');
    }
    return res;
}

// Ways to finish the partial permutation described by filled with balance b.
__uint128_t cnt_ways_finish(int n, int b, vector<int> filled) {
    vector<int> paired(2 * n);
    for (int i = 0; i < filled.size(); i++) {
        paired[i * 2] = 1;
        paired[filled[i] * 2 + 1] = 2;
        if (filled[i] < i) {
            paired[i * 2] = 2;
            paired[filled[i] * 2 + 1] = 1;
        }
    }

    for (int i = 0; i <= 2 * n; i++) {
        for (int j = 0; j <= n; j++) {
            for (int k = 0; k < n * n; k++) {
                dp[i][j][k] = 0;
            }
        }
    }
    dp[0][0][0] = 1;

    int cnt_ind = 0;
    int cnt_val = 0;
    for (int i = 0; i < 2 * n; i++) {
        for (int j = 0; j <= n; j++) {
            for (int k = 0; k < n * n; k++) {
                if (dp[i][j][k] == 0) {
                    continue;
                }
                if (!paired[i]) {
                    if (j > 0) {
                        // ind_ops + val_ops = j
                        // ind_ops + cnt_ind = val_ops + cnt_val + (i % 2)
                        // ind_ops + cnt_ind = j - ind_ops + cnt_val + (i % 2)
                        int ind_ops = (j + cnt_val - cnt_ind + (i % 2)) / 2;
                        int val_ops = j - ind_ops;
                        if (i % 2 == 0) {
                            // considering an index
                            dp[i + 1][j - 1][k + i / 2] += dp[i][j][k] * val_ops;
                        } else {
                            // condidering a value
                            dp[i + 1][j - 1][k + i / 2] += dp[i][j][k] * ind_ops;
                        }
                    }
                    if (j < n) {
                        dp[i + 1][j + 1][k] += dp[i][j][k];
                    }
                } else if (paired[i] == 1) {
                    dp[i + 1][j][k] += dp[i][j][k];
                } else {
                    dp[i + 1][j][k + i / 2] += dp[i][j][k];
                }
            }
        }
        if (paired[i] && i % 2 == 0) {
            cnt_ind++;
        } else if (paired[i]) {
            cnt_val++;
        }
    }
    for (int i = 0; i < n * n; i++) {
        int balance = i - (n * (n - 1) - i);
        if (balance == b) {
            return dp[2 * n][0][i];
        }
    }
    assert(0);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, b;
    string strk;
    cin >> n >> b >> strk;
    __uint128_t k = string_to_uint128(strk);
    vector<int> p;
    vector<bool> left(n, 1);   
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (left[j]) {
                p.push_back(j);
                __uint128_t cnt = cnt_ways_finish(n, b, p);
                if (cnt >= k) {
                    left[j] = 0;
                    break;
                }
                k -= cnt;
                p.pop_back();   
            }
        }
    }

    for (int pi : p) {
        cout << pi + 1 << " ";
    }
    cout << "\n";
}