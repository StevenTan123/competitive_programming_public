#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    string s;
    string t;
    cin >> s >> t;

    vector<int> cnts(26);
    vector<int> cntt(26);
    for (int i = 0; i < n; i++) {
        cnts[s[i] - 'a']++;
        cntt[t[i] - 'a']++;
    }
    bool possible = true;
    for (int i = 0; i < 26; i++) {
        if (cnts[i] != cntt[i]) {
            possible = false;
            break;
        }
    }
    if (!possible) {
        cout << "-1\n";
        return;
    }

    vector<vector<int>> dp(n + 1, vector<int>(n + 1));
    int max_match = 0;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            dp[i][j] = max(dp[i][j], dp[i - 1][j]);
            if (s[i - 1] == t[j - 1]) {
                dp[i][j] = max(dp[i][j], dp[i - 1][j - 1] + 1);
            }
            max_match = max(max_match, dp[i][j]);
        }
    }
    cout << n - max_match << "\n";
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