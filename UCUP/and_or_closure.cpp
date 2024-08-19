#include <bits/stdc++.h>
using namespace std;

const int MAXN = 200005;
const int BITS = 40;
const int SPLIT = 20;
bitset<MAXN> bits[BITS];
bitset<MAXN> zero;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        long long val;
        cin >> val;
        for (int j = 0; j < BITS; j++) {
            bits[j][i] = val & 1;
            val >>= 1;
        }
    }
    bitset<MAXN> one;
    for (int i = 0; i < n; i++) {
        one[i] = 1;
    }

    vector<bitset<MAXN>> unique;
    for (int i = 0; i < BITS; i++) {
        bool cur_new = true;
        for (int j = 0; j < i; j++) {
            if (bits[j] == bits[i]) {
                cur_new = false;
                break;
            }
        }
        if (bits[i] != zero && bits[i] != one && cur_new) {
            unique.push_back(bits[i]);
        }
    }
    
    int m = unique.size();
    if (m == 0) {
        cout << "1\n";
        return 0;
    }

    vector<uint64_t> DAG(m);
    for (int i = 0; i < m; i++) {
        for (int j = i + 1; j < m; j++) {
            bitset<MAXN> or_bs = (unique[i] | unique[j]);
            if (or_bs == unique[i] || or_bs == unique[j]) {
                DAG[i] = DAG[i] | ((uint64_t) 1 << j);
                DAG[j] = DAG[j] | ((uint64_t) 1 << i);
            }
        }
    }
    
    int half = min(SPLIT, m);
    // dp[i][mask] = # of subsets of mask differing only in the first i bits that are antichains 
    vector<vector<int>> sos_dp(half + 1, vector<int>(1 << half));
    for (uint64_t mask = 0; mask < (1 << half); mask++) {
        bool antichain = true;
        for (int j = 0; j < half; j++) {
            if (mask & (1 << j)) {
                if ((DAG[j] & mask) != 0) {
                    antichain = false;
                    break;
                }
            }
        }
        if (antichain) {
            sos_dp[0][mask] = 1;
        }
    }
    for (int i = 1; i <= half; i++) {
        for (uint64_t mask = 0; mask < (1 << half); mask++) {
            sos_dp[i][mask] = sos_dp[i - 1][mask];
            if (mask & (1 << (i - 1))) {
                sos_dp[i][mask] += sos_dp[i - 1][mask ^ (1 << (i - 1))];
            }
        }
    }

    if (m > SPLIT) {
        int ohalf = m - half;
        long long res = 0;
        for (uint64_t mask = 0; mask < (1 << ohalf); mask++) {
            uint64_t real_mask = mask << half;
            bool antichain = true;
            for (int j = half; j < m; j++) {
                if (real_mask & ((uint64_t) 1 << j)) {
                    if ((DAG[j] & real_mask) != 0) {
                        antichain = false;
                        break;
                    }
                }
            }
            if (antichain) {
                uint64_t left_mask = 0;
                for (int j = 0; j < half; j++) {
                    if ((DAG[j] & real_mask) == 0) {
                        left_mask = left_mask | (1 << j);
                    }
                }
                res += sos_dp[half][left_mask];
            }
        }
        cout << res << "\n";
    } else {
        cout << sos_dp[half][(1 << half) - 1] << "\n";
    }
}