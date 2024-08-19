#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;

    vector<int> dp(n);
    for (int i = 1; i < n; i++) {
        if (i == 1) {
            if (s[0] == '1') {
                dp[1] = 1;
            }
        } else {
            if (s[i - 1] == '1') {
                dp[i] = i - dp[i - 1] - dp[i - 2];
            }
        }
    }

    long long res = 0;
    for (int i = 0; i < n; i++) {
        if (i < n - 1) {
            res += (long long) (n - i - 1) * dp[i];
        }
        if (s[i] == '0') {
            res += dp[i];
        } else {
            res += i + 1 - (i > 0 ? dp[i - 1] : 0);
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