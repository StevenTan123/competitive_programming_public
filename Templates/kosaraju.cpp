#include <bits/stdc++.h>
using namespace std;

void dfs1(vector<int> adj[], vector<bool> &used, vector<int> &order, int v) {
    used[v] = true;
    for (int u : adj[v]) {
        if (!used[u]) {
            dfs1(adj, used, order, u);
        }
    }
    order.push_back(v);
}

void dfs2(vector<int> adj_rev[], vector<bool> &used, int comps[], int v, int comp) {
    used[v] = true;
    comps[v] = comp;
    for (int u : adj_rev[v]) {
        if (!used[u]) {
            dfs2(adj_rev, used, comps, u, comp);
        }
    }
}

// Reads in directed graph with n vertices, m edges. comps[i] stores
// the strongly connected component ID node i is in.
void solve() {
    int n, m;
    cin >> n >> m;
    
    vector<int> adj[n];
    vector<int> adj_rev[n];
    vector<int> order;
    vector<bool> used(n, false);

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        adj[--u].push_back(--v);
        adj_rev[v].push_back(u);
    }

    for (int i = 0; i < n; i++) {
        if (!used[i]) {
            dfs1(adj, used, order, i);
        }
    }

    used.assign(n, false);
    reverse(order.begin(), order.end());
    int num_comps = 0;
    int comps[n];
    for (int v : order) {
        if (!used[v]) {
            dfs2(adj_rev, used, comps, v, num_comps);
            num_comps++;
        }
    }
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