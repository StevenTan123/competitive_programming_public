#include <bits/stdc++.h>
using namespace std;

void dfs(vector<int> tree[], int par[], int cur, int prev) {
    par[cur] = prev;
    for (int nei : tree[cur]) {
        if (nei != prev) {
            dfs(tree, par, nei, cur);
        }
    }
}

void solve() {
    int n, c0;
    cin >> n >> c0;
    int order[n - 1];
    for (int i = 0; i < n - 1; i++) {
        cin >> order[i];
        order[i]--;
    }
    vector<int> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        tree[--u].push_back(--v);
        tree[v].push_back(u);
    }

    int par[n];
    dfs(tree, par, --c0, -1);
    int closest[n];
    for (int i = 0; i < n; i++) {
        closest[i] = n - 1;
    }
    closest[c0] = 0;
    bool marked[n] = {0};
    marked[c0] = true;
    int ans = n - 1;
    for (int i = 0; i < n - 1; i++) {
        int cur = order[i];
        int j = 0;
        ans = min(ans, closest[cur]);
        while (cur != -1 && j <= ans) {
            ans = min(ans, j + closest[cur]);
            closest[cur] = min(closest[cur], j);
            cur = par[cur];
            j++;
        }
        marked[order[i]] = true;
        cout << ans << " ";
    }
    cout << endl;
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