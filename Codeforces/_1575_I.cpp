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

struct BIT {
    long long *bit;
    int len;
    BIT(int n) {
        bit = new long long[n + 1];
        len = n + 1;
        for (int i = 0; i <= n; i++) {
            bit[i] = 0;
        }
    }
    ~BIT() {
        delete[] bit;
    }
    void update(int index, int add) {
        index++;
        while (index < len) {
            bit[index] += add;
            index += index & -index;
        }
    }
    long long sum(int l, int r) {
        return psum(r) - (l == 0 ? 0 : psum(l - 1));
    }
    long long psum(int index) {
        index++;
        long long res = 0;
        while (index > 0) {
            res += bit[index];      
            index -= index & -index;
        }
        return res;
    }
};

void euler_tour(vector<int> tree[], int cur, int prev, vector<int> &tour) {
    tour.push_back(cur);
    for (int nei : tree[cur]) {
        if (nei != prev) {
            euler_tour(tree, nei, cur, tour);
        }
    }
    tour.push_back(cur);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, q;
    cin >> n >> q;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        if (a[i] < 0) {
            a[i] *= -1;
        }
    }
    vector<int> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int s, t;
        cin >> s >> t;
        tree[s - 1].push_back(t - 1);
        tree[t - 1].push_back(s - 1);
    }
    LCA lca_ds(n, tree);

    vector<int> tour;
    euler_tour(tree, 0, -1, tour);
    vector<int> enter(n, -1);
    vector<int> leave(n, -1);
    BIT bit(tour.size());
    for (int i = 0; i < tour.size(); i++) {
        if (enter[tour[i]] == -1) {
            enter[tour[i]] = i;
            bit.update(i, a[tour[i]]);
        } else {
            leave[tour[i]] = i;
            bit.update(i, -a[tour[i]]);
        }
    }

    for (int i = 0; i < q; i++) {
        int type, u, x;
        cin >> type >> u >> x;
        u--;
        if (type == 1) {
            if (x < 0) {
                x *= -1;
            }
            a[u] = x;
            int prev = bit.sum(enter[u], enter[u]);
            bit.update(enter[u], x - prev);
            prev = bit.sum(leave[u], leave[u]);
            bit.update(leave[u], -x - prev);
        } else {
            x--;
            int lca = lca_ds.lca(u, x);
            long long left = bit.sum(enter[lca], enter[u]) * 2;
            long long right = bit.sum(enter[lca], enter[x]) * 2;
            long long res = left - a[u] + right - a[x] - 2 * a[lca];
            cout << res << "\n";
        }
    }

}
