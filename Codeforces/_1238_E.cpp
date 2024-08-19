#include <bits/stdc++.h>
using namespace std;

const int MAXM = 20;
int cnt[MAXM][MAXM];
int dp[1 << MAXM];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
 
    int n, m;
    cin >> n >> m;
    string s;
    cin >> s;
    for (int i = 0; i < m; i++) for (int j = 0; j < m; j++) cnt[i][j] = 0;
    for (int i = 0; i < n - 1; i++) {
        int c1 = s[i] - 'a';
        int c2 = s[i + 1] - 'a';
        cnt[c1][c2]++;
        cnt[c2][c1]++;
    }
 
    int pow2 = 1 << m;
    // dp[bitmask] = min slowness with the bitmask characters as the prefix of the keyboard. 
    for (int i = 0; i < pow2; i++) dp[i] = INT_MAX;
    dp[0] = 0;
    for (int i = 0; i < pow2; i++) {
        if (dp[i] != INT_MAX) {
            vector<int> set_bits;
            vector<int> unset_bits;
            int cur = i;
            for (int j = 0; j < m; j++) {
                if (cur & 1) {
                    set_bits.push_back(j);
                } else {
                    unset_bits.push_back(j);
                }
                cur >>= 1;
            }
            int add = 0;
            for (int j : set_bits) {
                for (int k : unset_bits) {
                    add += cnt[j][k];
                }
            }
            for (int j : unset_bits) {
                dp[i | (1 << j)] = min(dp[i | (1 << j)], dp[i] + add);
            }
        }
    }
    cout << dp[pow2 - 1] << endl;
}