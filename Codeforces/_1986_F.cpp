#include <bits/stdc++.h>
using namespace std;

int cur_time;
long long min_edges;

void dfs(int n, vector<int> graph[], vector<int> &sizes, vector<int> &tin, vector<int> &back, int cur, int prev) {
    tin[cur] = cur_time++;
    sizes[cur] = 1;
    for (int nei : graph[cur]) {
        if (tin[nei] == 0) {
            dfs(n, graph, sizes, tin, back, nei, cur);
            back[cur] = min(back[cur], back[nei]);
            sizes[cur] += sizes[nei];
        } else if (nei != prev) {
            back[cur] = min(back[cur], tin[nei]);
        }
    }
    if (prev != -1) {
        if (back[cur] > tin[prev]) {
            min_edges = min(min_edges, (long long) sizes[cur] * (sizes[cur] - 1) / 2 + (long long) (n - sizes[cur]) * (n - sizes[cur] - 1) / 2);
        }
    }
}

void solve() {
    int n, m;
    cin >> n >> m;
    vector<int> graph[n];
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    vector<int> sizes(n);
    vector<int> tin(n);
    vector<int> back(n, INT_MAX);

    cur_time = 1;
    min_edges = (long long) n * (n - 1) / 2;
    dfs(n, graph, sizes, tin, back, 0, -1);
    cout << min_edges << "\n";
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