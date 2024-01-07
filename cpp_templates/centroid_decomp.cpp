#include <bits/stdc++.h>
using namespace std;

// For node i, pars[i] stores a list of all centroid ancestors of i along with their distances from i
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