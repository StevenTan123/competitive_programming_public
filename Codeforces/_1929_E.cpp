#include <bits/stdc++.h>
using namespace std;

bool dfs(vector<pair<int, int>> tree[], int edges[], int pairID, int dest, int cur, int prev) {
    if (cur == dest) {
        return true;
    }
    for (auto [nei, ID] : tree[cur]) {
        if (nei != prev) {
            bool found = dfs(tree, edges, pairID, dest, nei, cur);
            if (found) {
                edges[ID] |= (1 << pairID);
                return true;
            }
        }
    }
    return false;
}

void solve() {
    int n;
    cin >> n;
    vector<pair<int, int>> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        tree[u].push_back({v, i});
        tree[v].push_back({u, i});
    }

    int k;
    cin >> k;
    pair<int, int> pairs[k];
    for (int i = 0; i < k; i++) {
        int a, b;
        cin >> a >> b;
        pairs[i] = {a - 1, b - 1};
    }

    int edges[n - 1] = {0};
    for (int i = 0; i < k; i++) {
        dfs(tree, edges, i, pairs[i].second, pairs[i].first, -1);
    }
    set<int> unique;
    for (int i = 0; i < n - 1; i++) {
        unique.insert(edges[i]);
    }

    int pow2 = 1 << k;
    // dp[i] = min edges to cover bitmask i of pairs
    vector<int> dp(pow2, INT_MAX);
    dp[0] = 0;
    for (int bitmask : unique) {
        for (int i = pow2 - 1; i >= 0; i--) {
            if (dp[i] != INT_MAX) {
                dp[i | bitmask] = min(dp[i | bitmask], dp[i] + 1);
            }
        }
    }
    cout << dp[pow2 - 1] << "\n";
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