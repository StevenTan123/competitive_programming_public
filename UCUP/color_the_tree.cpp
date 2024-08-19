#include <bits/stdc++.h>
using namespace std;
 
const int MAXN = 1000005;
const int MAXLOG = 20;
int log_two[MAXN];
 
struct SparseTable {
    int N;
    int **mi;
    SparseTable(int n, int *a) : N(n) {
        mi = new int*[N];
        for (int i = 0; i < N; i++) {
            mi[i] = new int[MAXLOG + 1];
            mi[i][0] = a[i];
        }
        for (int j = 1; j <= MAXLOG; j++) {
            for(int i = 0; i + (1 << j) <= N; i++) {
                mi[i][j] = min(mi[i][j - 1], mi[i + (1 << (j - 1))][j - 1]);
            }
        }
    }
    ~SparseTable() {
        for (int i = 0; i < N; i++) delete[] mi[i];
        delete[] mi;
    }
    int rmq(int l, int r) {
        int j = log_two[r - l + 1];
        return min(mi[l][j], mi[r - (1 << j) + 1][j]);
    }
};
 
struct LCA {
    int n, timer;
    vector<int> *levels;
    int *depth, *tin, *tout, **up;
    LCA(int _n, vector<int> tree[]) : n(_n), timer(0) {
        levels = new vector<int>[n];
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
        delete[] levels; delete[] depth; delete[] tin; delete[] tout;
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
        levels[depth[cur]].push_back(cur);
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
 
void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    SparseTable rmq(n, a);
    vector<int> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        tree[u].push_back(v);
        tree[v].push_back(u);
    }
 
    LCA lca(n, tree);
    long long res = 0;
    long long child_sum[n];
    long long cost[n];
    for (int depth = 0; depth < n; depth++) {
        vector<int> level = lca.levels[depth];
        if (level.size() == 0) {
            continue;
        }
        set<pair<int, int>> aux;
        for (int i = 0; i < level.size(); i++) {
            aux.emplace(lca.tin[level[i]], level[i]);
            child_sum[level[i]] = LONG_LONG_MAX;
            if (i + 1 < level.size()) {
                int adj_lca = lca.lca(level[i], level[i + 1]);
                child_sum[adj_lca] = 0;
                aux.emplace(lca.tin[adj_lca], adj_lca);
            }
        }
        stack<int> stk;
        for (auto [tin, node] : aux) {
            while (!stk.empty() && !lca.is_ancestor(stk.top(), node)) {
                int cur = stk.top();
                stk.pop();
                cost[cur] = min(child_sum[cur], (long long) rmq.rmq(depth - lca.depth[cur], depth));
                child_sum[stk.top()] += cost[cur];
            }
            stk.push(node);
        }
        while (!stk.empty()) {
            int cur = stk.top();
            stk.pop();
            cost[cur] = min(child_sum[cur], (long long) rmq.rmq(depth - lca.depth[cur], depth));
            if (!stk.empty()) {
                child_sum[stk.top()] += cost[cur];
            }
        }
        res += cost[aux.begin()->second];
    }
    cout << res << "\n";
}
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
 
    log_two[1] = 0;
    for (int i = 2; i < MAXN; i++) {
        log_two[i] = log_two[i / 2] + 1;
    }
 
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}