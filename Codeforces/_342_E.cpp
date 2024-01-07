#include <bits/stdc++.h>
using namespace std;

const int inf = 1e9;

struct CentroidDecomp {
    int n;
    int *dists, *sizes;
    bool *visited;
    vector<pair<int, int>> *pars;
    vector<vector<int>> tree;
    CentroidDecomp(int _n, vector<vector<int>> &_tree) : n(_n), tree(_tree) {
        dists = new int[n];
        sizes = new int[n];
        visited = new bool[n];
        for (int i = 0; i < n; i++) {
            visited[i] = 0;
        }
        pars = new vector<pair<int, int>>[n];
        build(0);
    }
    ~CentroidDecomp() {
        delete[] dists; delete[] sizes; delete[] visited; delete[] pars;
    }
    void dfs_sizes(int cur, int prev) {
        sizes[cur] = 1;
        for (int nei : tree[cur]) {
            if (nei != prev && !visited[nei]) {
                dfs_sizes(nei, cur);
                sizes[cur] += sizes[nei];
            }
        }
    }
    void dfs(int start, int cur, int prev) {
        dists[cur] = (prev == -1 ? 0 : dists[prev] + 1);
        pars[cur].push_back({start, dists[cur]});
        for (int nei : tree[cur]) {
            if (nei != prev && !visited[nei]) {
                dfs(start, nei, cur);
            }
        }
    }
    int find_centroid(int cur, int prev, int size) {
        for (int nei : tree[cur]) {
            if (nei != prev && !visited[nei]) {
                if (sizes[nei] * 2 > size) {
                    return find_centroid(nei, cur, size);
                }
            }
        }
        return cur;
    }
    void build(int cur) {
        dfs_sizes(cur, -1);
        int centroid = find_centroid(cur, -1, sizes[cur]);
        dfs(centroid, centroid, -1);
        visited[centroid] = 1;
        for (int nei : tree[centroid]) {
            if (!visited[nei]) {
                build(nei);
            }
        }
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    vector<vector<int>> tree(n);
    for (int i = 0; i < n - 1; i++) {
        int a, b;
        cin >> a >> b;
        tree[--a].push_back(--b);
        tree[b].push_back(a);
    }

    CentroidDecomp cd(n, tree);
    vector<int> dist(n, inf);

    for (pair<int, int> centroid : cd.pars[0]) {
        dist[centroid.first] = min(dist[centroid.first], centroid.second);
    }

    for (int i = 0; i < m; i++) {
        int t, v;
        cin >> t >> v;
        v--;
        if (t == 1) {
            for (pair<int, int> centroid : cd.pars[v]) {
                dist[centroid.first] = min(dist[centroid.first], centroid.second);
            }
        } else {
            int res = inf;
            for (pair<int, int> centroid : cd.pars[v]) {
                res = min(res, dist[centroid.first] + centroid.second);
            }
            cout << res << "\n";
        }
    }
}