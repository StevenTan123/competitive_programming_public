#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
const int MAXN = 5005;

long long binom[MAXN][MAXN];

void solve() {
    int n;
    cin >> n;
    int p[n];
    for (int i = 0; i < n; i++) {
        cin >> p[i];
    }
    long long res = 0;
    
    // for each i, count # arrays with sum p[i]
    for (int i = 0; i < n; i++) {
        if (i > 0 && p[i] == p[i - 1]) {
            continue;
        }

        // count[i] = # occurrences of i - n as a prefix
        int count[2 * n + 1] = {0};
        int cnt = 0;
        for (int j = 1; j <= p[n - 1]; j++) {
            count[j + n]++;
            cnt++;
        }
        for (int j = max(p[n - 1] - 1, -1); j >= p[i]; j--) {
            count[j + n]++;
            cnt++;
        }
        
        int prev = n - 1;
        long long cur_ways = 1;
        for (int j = n - 1; j >= 0; j--) {
            if (j == 0 || p[j - 1] < p[j]) {
                int need = prev - j + 1;
                int have = count[p[j] + n];
                if (have > need) {
                    cur_ways = 0;
                    break;
                }
                if (have == need) {
                    prev = j - 1;
                    continue;
                }

                // we need to insert (need - have) {-1, 1} pairs shapes among the existing (have) gaps
                int gaps = have + (p[j] == 0 ? 1 : 0);
                cur_ways *= binom[need - have + gaps - 1][gaps - 1];
                cur_ways %= MOD;
                
                // increase count of current height and below height by (need - have)
                if (p[j] + n - 1 < 0) {
                    cur_ways = 0;
                    break;
                }
                count[p[j] + n] += need - have;
                count[p[j] + n - 1] += need - have;
                
                prev = j - 1;
            }
        }

        int total = 0;
        for (int j = 0; j <= 2 * n; j++) {
            total += count[j];
        }
        if (total != n) {
            cur_ways = 0;
        }
        res += cur_ways;
        res %= MOD;
    }
    cout << res << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 0; i < MAXN; i++) {
        binom[i][0] = 1;
        binom[i][i] = 1;
        for (int j = 1; j < i; j++) {
            binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
        }
    }
    
    int t;
    cin >> t;
    while (t-- > 0) {
        solve();
    }
}