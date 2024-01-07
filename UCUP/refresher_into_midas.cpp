#include <bits/stdc++.h>
using namespace std;

void solve() {
    int a, b, m;
    cin >> a >> b >> m;

    vector<int> dp(m + 1, 0);
    for (int i = m; i >= 0; i--) {
        if (i + b <= m) {
            int before = b / a;
            if (b % a == 0) {
                before--;
            }
            dp[i] = max(dp[i], dp[i + b] + before + 1);

            int next = i + (before + 1) * a;
            if (next <= m) {
                dp[i] = max(dp[i], dp[next] + before + 2);
            }
        } else {
            int floor = (m - i) / a;
            dp[i] = dp[i + floor * a] + floor;
        }
    }
    int res = 160 * (dp[0] + 2);
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