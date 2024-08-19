#include <bits/stdc++.h>
using namespace std;

const int MAXN = 1 << 20;
bitset<MAXN> dp;

int main() {
    cin.tie(0)->sync_with_stdio(0);

    int n, m;
    cin >> n >> m;
    string engineers[n];
    vector<int> cnt(m, 0);
    vector<vector<int>> reduced;
    for (int i = 0; i < n; i++) {
        cin >> engineers[i];
        vector<int> bits;
        for (int j = 0; j < m; j++) {
            if (engineers[i][j] == '1') {
                if (cnt[j] < m) {
                    cnt[j]++;
                    bits.push_back(j);
                }
            }
        }
        if (bits.size() > 0) {
            reduced.push_back(bits);
        }
    }

    int pow2 = 1 << m;
    dp[0] = 1;
    for (int i = 0; i < reduced.size(); i++) {
        for (int j = pow2 - 1; j >= 0; j--) {
            if (dp[j]) {
                for (int bit : reduced[i]) {
                    dp[j | (1 << bit)] = 1;
                }
            }
        }
    }

    int res = m;
    for (int i = 0; i < pow2; i++) {
        if (dp[i] == 0) {
            int num = __builtin_popcount(i);
            res = min(res, num - 1);
        }
    }
    cout << res << endl;
}
