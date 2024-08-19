#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    vector<pair<int, int>> segs[n];
    for (int i = 0; i < n; i++) {
        vector<int> cnt(n + 1);
        int mex = 0;
        for (int j = i; j >= 0; j--) {
            cnt[a[j]]++;
            while (cnt[mex] > 0) {
                mex++;
            }
            if (a[i] < mex && a[j] < mex && cnt[a[i]] == 1 && cnt[a[j]] == 1) {
                segs[i].emplace_back(j, mex);
            }
        }
    }

    // dp[i][j] = possible to reach a xor of mex value j using a[1...i]
    vector<vector<bool>> dp(n + 1, vector<bool>(n + 1, 0));
    int res = 0;
    for (int i = 0; i <= n; i++) {
        dp[i][0] = 1;
    }
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (dp[i - 1][j]) {
                dp[i][j] = 1;
            } else {
                for (auto [prev, mex] : segs[i - 1]) {
                    if ((j ^ mex) <= n && dp[prev][j ^ mex]) {
                        dp[i][j] = 1;
                        break;
                    }
                }
            }
            if (dp[i][j]) {
                res = max(res, j);
            }
        }
    }
    cout << res << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}