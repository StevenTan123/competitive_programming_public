#include <bits/stdc++.h>
using namespace std;

// Returns length of longest suffix of x that is a prefix of y.
int longest_suf_pref(string x, string y) {
    int res = 0;
    for (int i = 0; i < x.length(); i++) {
        int cnt = 0;
        while (i + cnt < x.length() && cnt < y.length() && x[i + cnt] == y[cnt]) {
            cnt++;
        }
        if (i + cnt >= x.length()) {
            res = max(res, cnt);
        }
    }
    return res;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    string a, b;
    cin >> a >> b;

    // next_match[i][0/1] = if concatenate 0/1 to end of b[1...i], what's the longest suffix
    // of that string that's also a prefix of b?
    int next_match[m + 1][2];
    stringstream ss;
    for (int i = 0; i <= m; i++) {
        string next1 = ss.str() + "0";
        next_match[i][0] = longest_suf_pref(next1, b);
        string next2 = ss.str() + "1";
        next_match[i][1] = longest_suf_pref(next2, b);
        if (i < m) ss << b[i];
    }

    // dp[i][j][k] = min changes to a[1...i] such that longest suffix of a[1...i] that is also a prefix of
    // b is j, and there are k matches so far.
    vector<vector<vector<int>>> dp(n + 1, vector<vector<int>>(m + 1, vector<int>(n - m + 2, INT_MAX)));
    dp[0][0][0] = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= m; j++) {
            for (int k = 0; k <= n - m + 1; k++) {
                if (dp[i][j][k] == INT_MAX) continue;
                
                // If we make next character 0.
                int j2 = next_match[j][0];
                dp[i + 1][j2][k + (j2 == m)] = min(dp[i + 1][j2][k + (j2 == m)], dp[i][j][k] + (a[i] == '1'));
            
                // If we make next character 1.
                j2 = next_match[j][1];
                dp[i + 1][j2][k + (j2 == m)] = min(dp[i + 1][j2][k + (j2 == m)], dp[i][j][k] + (a[i] == '0'));
            }
        }
    }
    
    for (int k = 0; k <= n - m + 1; k++) {
        int res = INT_MAX;
        for (int j = 0; j <= m; j++) {
            res = min(res, dp[n][j][k]);
        }
        cout << (res == INT_MAX ? -1 : res) << " ";
    }
    cout << "\n";
}