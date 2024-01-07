#include <bits/stdc++.h>
using namespace std;

const int MAXLOG = 20;

struct LCA {
    int n, timer;
    int *depth, *tin, *tout, **up;
    LCA(int _n, vector<int> tree[]) : n(_n), timer(0) {
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        up = new int*[MAXLOG];
        for (int i = 0; i < MAXLOG; i++) {
            up[i] = new int[n];
        }
        dfs(tree, 0, -1);
        compute_up();
    }
    ~LCA() {
        delete[] depth; delete[] tin; delete[] tout;
        for (int i = 0; i < MAXLOG; i++) delete[] up[i];
        delete[] up;
    }
    void dfs(vector<int> tree[], int cur, int prev) {
        if (prev == -1) {
            depth[cur] = 0;
            up[0][cur] = 0;
        } else {
            depth[cur] = depth[prev] + 1;
            up[0][cur] = prev;
        }
        tin[cur] = timer++;
        for (int nei : tree[cur]) {
            if (nei != prev) {
                dfs(tree, nei, cur);
            }
        }
        tout[cur] = timer++;
    }
    void compute_up() {
        for (int i = 1; i < MAXLOG; i++) {
            for (int j = 0; j < n; j++) {
                up[i][j] = up[i - 1][up[i - 1][j]];
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
    int dist(int a, int b) {
        int c = lca(a, b);
        return (depth[a] - depth[c]) + (depth[b] - depth[c]);
    }
};