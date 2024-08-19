#include <bits/stdc++.h>
using namespace std;

int n, k;

void dfs(vector<pair<int, int>> tree[], vector<vector<long long>> &dp, int cur, int prev) {
    vector<long long> diff;
    for (pair<int, int> e : tree[cur]) {
        int v = e.first;
        int w = e.second;
        if (v != prev) {
            dfs(tree, dp, v, cur);
            dp[cur][1] += dp[v][1];
            diff.push_back(max(dp[v][0] + w - dp[v][1], 0ll));
        }
    }
    sort(diff.rbegin(), diff.rend());
    dp[cur][0] = dp[cur][1];
    for (int i = 0; i < min(k, (int) diff.size()); i++) {
        dp[cur][1] += diff[i];
        if (i < k - 1) {
            dp[cur][0] += diff[i];
        }
    }
}

void solve() {
    cin >> n >> k;
    vector<pair<int, int>> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        tree[--u].push_back({--v, w});
        tree[v].push_back({u, w});
    }

    vector<vector<long long>> dp(n, vector<long long>(2));
    dfs(tree, dp, 0, -1);
    cout << dp[0][1] << "\n";
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