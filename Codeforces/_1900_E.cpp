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

void solve() {
    int n, m;
    cin >> n >> m;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
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
    
    vector<int> DAG[num_comps];
    long long comp_val[num_comps] = {0};
    int comp_size[num_comps] = {0};
    for (int i = 0; i < n; i++) {
        comp_val[comps[i]] += a[i];
        comp_size[comps[i]]++;
        for (int nei : adj[i]) {
            if (comps[nei] != comps[i]) {
                DAG[comps[nei]].push_back(comps[i]);
            }
        }
    }

    // dp[i] = {first, second} stores the longest walk ending at first, with min second cost
    pair<int, long long> dp[num_comps];
    pair<int, long long> best = {0, 0};
    for (int i = 0; i < num_comps; i++) {
        dp[i] = {comp_size[i], comp_val[i]};
        for (int nei : DAG[i]) {
            if (dp[nei].first + comp_size[i] > dp[i].first) {
                dp[i] = {dp[nei].first + comp_size[i], dp[nei].second + comp_val[i]};
            } else if (dp[nei].first + comp_size[i] == dp[i].first) {
                dp[i].second = min(dp[i].second, dp[nei].second + comp_val[i]);
            }
        }
        cout << endl;
        if (dp[i].first > best.first) {
            best = dp[i];
        } else if (dp[i].first == best.first) {
            best.second = min(best.second, dp[i].second);
        }
    }
    cout << best.first << " " << best.second << endl;
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