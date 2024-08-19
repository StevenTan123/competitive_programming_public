#include <bits/stdc++.h>
using namespace std;

long long res = 0;

void dfs_sum(set<int> graph[], int w[], vector<bool> &vis, int cur) {
    if (vis[cur]) {
        return;
    }
    vis[cur] = true;
    res += w[cur];
    for (int nei : graph[cur]) {
        dfs_sum(graph, w, vis, nei);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    int w[n];
    for (int i = 0; i < n; i++) {
        cin >> w[i];
    }
    set<int> graph[n];
    vector<int> deg(n);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        graph[u].insert(v);
        graph[v].insert(u);
        deg[u]++;
        deg[v]++;
    }
    int s;
    cin >> s;
    s--;

    vector<long long> val(n);
    for (int i = 0; i < n; i++) {
        val[i] = w[i];
    }
    
    deque<int> leaves;
    for (int i = 0; i < n; i++) {
        if (deg[i] == 1 && i != s) {
            leaves.push_back(i);
        }
    }
    long long max_path = 0;
    while (!leaves.empty()) {
        int cur = leaves.front();
        leaves.pop_front();

        int par = *graph[cur].begin();
        val[par] = max(val[par], w[par] + val[cur]);
        graph[par].erase(cur);
        deg[par]--;
        max_path = max(max_path, val[cur]);
        if (par != s && deg[par] == 1) {
            leaves.push_back(par);
        }
    }
    vector<bool> vis(n, false);
    dfs_sum(graph, w, vis, s);
    cout << res + max_path << endl;
}