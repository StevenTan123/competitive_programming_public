#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;
    vector<vector<int>> graph(n);
    vector<int> max_edge(n);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        graph[--u].push_back(--v);
        max_edge[u] = max(max_edge[u], v);
    }
    for (int i = 0; i < n - 1; i++) {
        graph[i].push_back(i + 1);
    }
    vector<int> dists(n, -1);
    dists[0] = 0;
    deque<int> bfs;
    bfs.push_back(0);
    while (!bfs.empty()) {
        int cur = bfs.front();
        bfs.pop_front();
        for (int nei : graph[cur]) {
            if (dists[nei] == -1) {
                dists[nei] = dists[cur] + 1;
                bfs.push_back(nei);
            }
        }
    }

    vector<int> lose(n + 1);
    for (int i = 0; i < n; i++) {
        if (dists[i] != -1) {
            int to = max(i + 1, max_edge[i]);
            int l = i + 1;
            // r + dists[i] + 1 < to
            int r = to - dists[i] - 1;
            if (l < r) {
                lose[l]++;
                lose[r]--;
            }
        }
    }
    cout << "1";
    for (int i = 1; i < n - 1; i++) {
        lose[i] += lose[i - 1];
        if (lose[i] > 0) {
            cout << "0";
        } else {
            cout << "1";
        }
    }
    cout << "\n";
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