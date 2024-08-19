#include <bits/stdc++.h>
using namespace std;

const int MAXLOG = 20;

struct LCA {
    int n, timer;
    int *depth, *tin, *tout, **up, **max_up;
    LCA(int _n, vector<pair<int, int>> tree[]) : n(_n), timer(0) {
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        up = new int*[MAXLOG];
        max_up = new int*[MAXLOG];
        for (int i = 0; i < MAXLOG; i++) {
            up[i] = new int[n];
            max_up[i] = new int[n];
        }
        dfs(tree, 0, -1, -1);
        compute_up();
    }
    ~LCA() {
        delete[] depth; delete[] tin; delete[] tout;
        for (int i = 0; i < MAXLOG; i++) {
            delete[] up[i];
            delete[] max_up[i];
        }
        delete[] up; delete[] max_up;
    }
    void dfs(vector<pair<int, int>> tree[], int cur, int weight, int prev) {
        if (prev == -1) {
            depth[cur] = 0;
            up[0][cur] = 0;
            max_up[0][cur] = 0;
        } else {
            depth[cur] = depth[prev] + 1;
            up[0][cur] = prev;
            max_up[0][cur] = weight;
        }
        tin[cur] = timer++;
        for (auto [nei, w] : tree[cur]) {
            if (nei != prev) {
                dfs(tree, nei, w, cur);
            }
        }
        tout[cur] = timer++;
    }
    void compute_up() {
        for (int i = 1; i < MAXLOG; i++) {
            for (int j = 0; j < n; j++) {
                up[i][j] = up[i - 1][up[i - 1][j]];
                max_up[i][j] = max(max_up[i - 1][j], max_up[i - 1][up[i - 1][j]]);
            }
        }
    }
    bool is_ancestor(int a, int b) {
        return tin[a] <= tin[b] && tout[a] >= tout[b];
    }
    int lca(int a, int b) {
        if (depth[a] > depth[b]) {
            swap(a, b);
        }
        if (is_ancestor(a, b)) {
            return a;
        }
        for (int i = MAXLOG - 1; i >= 0; i--) {
            if (!is_ancestor(up[i][a], b)) {
                a = up[i][a];
            }
        }
        return up[0][a];
    }
    int max_up_path(int a, int b) {
        if (a == b) {
            return 0;
        }
        int res = 0;
        for (int i = MAXLOG - 1; i >= 0; i--) {
            if (!is_ancestor(up[i][a], b)) {
                res = max(res, max_up[i][a]);
                a = up[i][a];
            }
        }
        res = max(res, max_up[0][a]);
        return res;
    }
    int max_path(int a, int b) {
        int c = lca(a, b);
        return max(max_up_path(a, c), max_up_path(b, c));
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    vector<pair<int, int>> edges;
    vector<tuple<int, int, int>> graph[n];
    for (int i = 0; i < m; i++) {
        int a, b, e;
        cin >> a >> b >> e;
        a--; b--;
        graph[a].emplace_back(b, e, i);
        graph[b].emplace_back(a, e, i);
        edges.emplace_back(a, b);
    }

    set<int> MST_edges;
    vector<pair<int, int>> MST[n];
    vector<bool> visited(n, 0);
    priority_queue<tuple<int, int, int, int>> pq;
    pq.push({0, 0, -1, -1});
    while (!pq.empty()) {
        auto [weight, node, prev, edge_ID] = pq.top();
        pq.pop();
        weight *= -1;

        if (visited[node]) {
            continue;
        }
        visited[node] = true;
        MST_edges.insert(edge_ID);
        if (prev != -1) {
            MST[prev].push_back({node, weight});
        }

        for (auto [to, w, ID] : graph[node]) {
            pq.push({-w, to, node, ID});
        }
    }

    LCA lca(n, MST);
    for (int i = 0; i < m; i++) {
        if (!MST_edges.count(i)) {
            auto [a, b] = edges[i];
            cout << lca.max_path(a, b) << "\n";
        }
    }
}