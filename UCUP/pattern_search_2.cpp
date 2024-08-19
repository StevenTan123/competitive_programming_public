#include <bits/stdc++.h>
using namespace std;

const int FIB = 32;
int fibs[FIB];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    fibs[0] = 1;
    fibs[1] = 1;
    for (int i = 2; i < FIB; i++) {
        fibs[i] = fibs[i - 1] + fibs[i - 2];
    }
    
    vector<vector<int>> starts(fibs[FIB - 1]);
    starts[0].push_back(1);
    starts[0].push_back(2);
    starts[1].push_back(0);
    for (int i = 3; i < FIB; i++) {
        for (int j = 0; j < fibs[i - 2]; j++) {
            for (int k : starts[j]) {
                if (k > i - 2) {
                    break;
                }
                starts[fibs[i - 1] + j].push_back(k);
            }
        }
        starts[0].push_back(i);
    }

    string s;
    cin >> s;
    int n = s.length();

    // dp[i][j] = max subsequence match of s[i...] to S_j
    vector<vector<int>> dp(n + 1, vector<int>(FIB));
    for (int i = 0; i < n; i++) {
        if (s[i] == 'a') {
            dp[i][1] = 1;
        } else {
            dp[i][0] = 1;
        }
    }
    for (int j = 2; j < FIB; j++) {
        for (int i = 0; i < n; i++) {
            dp[i][j] = max(dp[i][j], dp[i][j - 1] + dp[i + dp[i][j - 1]][j - 2]);
        }
    }

    int res = INT_MAX;
    for (int i = 0; i < fibs[FIB - 2]; i++) {
        int cur = i;
        int ind = 0;
        while (true) {
            bool moved = false;
            for (int j = starts[cur].size() - 1; j >= 0; j--) {
                if (ind + dp[ind][starts[cur][j]] < n) {
                    ind += dp[ind][starts[cur][j]];
                    cur += fibs[starts[cur][j]];
                    moved = true;
                    break;
                }
            }
            if (!moved) {
                break;
            }
        }
        res = min(res, cur - i + 1);
    }
    cout << res << "\n";
}