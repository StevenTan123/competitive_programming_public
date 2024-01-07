#include <bits/stdc++.h>
using namespace std;

void dfs(vector<int> tree[], int a[], long long dp[], long long dp2[], int cur, int prev) {
    vector<long long> sorted;
    for (int nei : tree[cur]) {
        if (nei != prev) {
            dfs(tree, a, dp, dp2, nei, cur);
            sorted.push_back(dp[nei]);
        }
    }
    sort(sorted.begin(), sorted.end());

    dp[cur] = a[cur];
    dp2[cur] = a[cur];
    if (sorted.size() > 0) {
        dp[cur] = max(dp[cur], sorted[sorted.size() - 1]);
        dp2[cur] = max(dp2[cur], a[cur] + sorted[sorted.size() - 1]);
        
        long long tot = 0;
        for (int i = sorted.size() - 1; i >= 0; i--) {
            if (i < (int) sorted.size() - 3 && sorted[i] < 0) {
                break;
            }
            tot += sorted[i];
            if (i < (int) sorted.size() - 1) {
                if (i == (int) sorted.size() - 2) {
                    dp2[cur] = max(dp2[cur], tot);
                } else {
                    dp2[cur] = max(dp2[cur], a[cur] + tot);
                }
                dp[cur] = max(dp[cur], a[cur] + tot);
            }
        }
    }
}

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    vector<int> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        tree[--u].push_back(--v);
        tree[v].push_back(u);
    }

    long long dp[n];
    long long dp2[n];
    dfs(tree, a, dp, dp2, 0, -1);
    long long max_val = 0;
    for (int i = 0; i < n; i++) {
        max_val = max(max_val, dp2[i]);
    }
    cout << max_val << endl;
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