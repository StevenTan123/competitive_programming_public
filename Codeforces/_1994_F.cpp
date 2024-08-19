#include <bits/stdc++.h>
using namespace std;

void dfs(vector<multiset<int>> &graph0, vector<multiset<int>> &graph1, vector<bool> &vis, vector<int> &deg, int cur, int prev) {
    vis[cur] = true;
    for (int nei : graph0[cur]) {
        if (!vis[nei]) {
            dfs(graph0, graph1, vis, deg, nei, cur);
        }
    }
    if (deg[cur] % 2 == 1 && prev != -1) {
        graph1[prev].insert(cur);
        graph1[cur].insert(prev);
        deg[cur]++;
        deg[prev]++;
    }
}

void eulerian_tour(vector<multiset<int>> &graph, vector<int> &tour, int cur) {
    while (!graph[cur].empty()) {
        int nei = *graph[cur].begin();
        graph[cur].erase(graph[cur].find(nei));
        graph[nei].erase(graph[nei].find(cur));
        eulerian_tour(graph, tour, nei);
    }
    tour.push_back(cur);
}

void solve() {
    int n, m;
    cin >> n >> m;
    vector<multiset<int>> graph0(n);
    vector<multiset<int>> graph1(n);
    vector<int> deg(n);
    for (int i = 0; i < m; i++) {
        int u, v, c;
        cin >> u >> v >> c;
        u--; v--;
        if (c == 0) {
            graph0[u].insert(v);
            graph0[v].insert(u);
        } else {
            graph1[u].insert(v);
            graph1[v].insert(u);
            deg[u]++;
            deg[v]++;
        }
    }

    bool possible = true;
    vector<bool> vis(n);
    for (int i = 0; i < n; i++) {
        if (!vis[i]) {
            dfs(graph0, graph1, vis, deg, i, -1);
            if (deg[i] % 2 == 1) {
                possible = false;
                break;
            }
        }
    }

    if (possible) {
        cout << "YES\n";
        vector<int> tour;
        eulerian_tour(graph1, tour, 0);
        cout << tour.size() - 1 << "\n";
        for (int node : tour) {
            cout << node + 1 << " ";
        }
        cout << "\n";
    } else {
        cout << "NO\n";
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