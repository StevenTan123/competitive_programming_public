#include <bits/stdc++.h>
using namespace std;

struct LazySegTree {
    int *t, *lazy;
    LazySegTree(int n) {
        t = new int[n * 4];
        lazy = new int[n * 4];
        for (int i = 0; i < n * 4; i++) {
            t[i] = 0;
            lazy[i] = 0;
        }
    }
    ~LazySegTree() {
        delete[] t; delete[] lazy;
    }
    void push(int v, int l, int r) {
        if (l < r) {
            lazy[v * 2] += lazy[v];
            lazy[v * 2 + 1] += lazy[v];
            t[v * 2] += lazy[v];
            t[v * 2 + 1] += lazy[v];
        }
        lazy[v] = 0;
    }
    void update(int v, int l, int r, int ql, int qr, int add) {
        if (ql > qr) {
            return;
        } else if (l == ql && r == qr) {
            t[v] += add;
            lazy[v] += add;
        } else {
            push(v, l, r);
            int m = (l + r) / 2;
            update(v * 2, l, m, ql, min(m, qr), add);
            update(v * 2 + 1, m + 1, r, max(ql, m + 1), qr, add);
            t[v] = max(t[v * 2], t[v * 2 + 1]);
        }
    }
    int rmq(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return -1;
        } else if (l == ql && r == qr) {
            return t[v];
        } else {
            push(v, l, r);
            int m = (l + r) / 2;
            int left = rmq(v * 2, l, m, ql, min(m, qr));
            int right = rmq(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            return max(left, right);
        }
    }
};

void dfs(vector<int> tree[], vector<int> same[], vector<int> &tour, int a[], int prev[], int cur) {
    if (prev[a[cur]] != -1) {
        same[prev[a[cur]]].push_back(cur);
    }
    int before = prev[a[cur]];
    prev[a[cur]] = cur;

    tour.push_back(cur);
    for (int child : tree[cur]) {
        dfs(tree, same, tour, a, prev, child);
    }
    
    prev[a[cur]] = before;
    tour.push_back(cur);
}

long long dfs_solve(int n, vector<int> tree[], vector<int> same[], int tour_inds[][2], LazySegTree &lst, int cur) {
    vector<int> diffs;
    int sub = lst.rmq(1, 0, 2 * n - 1, tour_inds[cur][0], tour_inds[cur][0]);
    for (int child : tree[cur]) {
        int diff = lst.rmq(1, 0, 2 * n - 1, tour_inds[child][0], tour_inds[child][1]) - sub;
        diffs.push_back(diff);
    }

    long long ret = 1;
    sort(diffs.begin(), diffs.end());
    if (diffs.size() > 1) {
        ret = (long long) (diffs[diffs.size() - 2] + 1) * (diffs[diffs.size() - 1] + 1);
    } else if (diffs.size() > 0) {
        ret = diffs[0] + 1;
    }

    lst.update(1, 0, 2 * n - 1, tour_inds[cur][0], tour_inds[cur][1] - 1, -1);
    for (int next : same[cur]) {
        lst.update(1, 0, 2 * n - 1, tour_inds[next][0], tour_inds[next][1] - 1, 1);
    }
    for (int child : tree[cur]) {
        ret = max(ret, dfs_solve(n, tree, same, tour_inds, lst, child));
    }
    return ret;
}

void solve() {
    int n;
    cin >> n;
    vector<int> tree[n];
    for (int i = 1; i < n; i++) {
        int p;
        cin >> p;
        tree[p - 1].push_back(i);
    }
    int a[n];
    int prev[n];
    int tour_inds[n][2];
    for (int i = 0; i < n; i++) {
        cin >> a[i]; a[i]--;
        prev[i] = -1;
        tour_inds[i][0] = -1;
        tour_inds[i][1] = -1;
    }

    vector<int> same[n];
    vector<int> tour;
    dfs(tree, same, tour, a, prev, 0);
    for (int i = 0; i < 2 * n; i++) {
        if (tour_inds[tour[i]][0] == -1) {
            tour_inds[tour[i]][0] = i;
        } else {
            tour_inds[tour[i]][1] = i;
        }
    }

    LazySegTree lst(2 * n);
    int in_deg[n] = {0};
    for (int i = 0; i < n; i++) {
        for (int sub : same[i]) {
            in_deg[sub]++;
        }
    }
    for (int i = 0; i < n; i++) {
        if (in_deg[i] == 0) {
            lst.update(1, 0, 2 * n - 1, tour_inds[i][0], tour_inds[i][1] - 1, 1);
        }
    }
    
    long long res = dfs_solve(n, tree, same, tour_inds, lst, 0);
    cout << res << "\n";
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