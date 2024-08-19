#include <bits/stdc++.h>
using namespace std;

struct Kuhn {
    int n, k;
    vector<int> mt;
    vector<bool> used;

    // graph split into 2 groups of size n and size k.
    // graph[i] stores edges out of node i in group 1.
    // mt[i] stores node in group 1 matched to node i in group 2.
    Kuhn(int _n, int _k, vector<int> graph[]) : n(_n), k(_k) {
        mt.assign(k, -1);
        for (int v = 0; v < n; ++v) {
            used.assign(n, false);
            try_kuhn(graph, v);
        }
    }

    bool try_kuhn(vector<int> graph[], int v) {
        if (used[v]) {
            return false;
        }
        used[v] = true;
        for (int to : graph[v]) {
            if (mt[to] == -1 || try_kuhn(graph, mt[to])) {
                mt[to] = v;
                return true;
            }
        }
        return false;
    }
};