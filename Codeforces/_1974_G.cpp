#include <bits/stdc++.h>
using namespace std;

struct LazySegTree {
    int *a, *t, *lazy;
    LazySegTree(int n, int _a[]) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = _a[i];
        }
        t = new int[n * 4];
        lazy = new int[n * 4];
        for (int i = 0; i < n * 4; i++) {
            lazy[i] = 0;
        }
        construct(1, 0, n - 1);
    }
    ~LazySegTree() {
        delete[] a; delete[] t; delete[] lazy;
    }
    void construct(int v, int l, int r) {
        if (l == r) {
            t[v] = a[l];
        } else {
            int m = (l + r) / 2;
            construct(v * 2, l, m);
            construct(v * 2 + 1, m + 1, r);
            t[v] = min(t[v * 2], t[v * 2 + 1]);
        }
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
            t[v] = min(t[v * 2], t[v * 2 + 1]);
        }
    }
    int rmq(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return INT_MAX;
        } else if (l == ql && r == qr) {
            return t[v];
        } else {
            push(v, l, r);
            int m = (l + r) / 2;
            int left = rmq(v * 2, l, m, ql, min(m, qr));
            int right = rmq(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            return min(left, right);
        }
    }
};

void solve() {
    int m, x;
    cin >> m >> x;

    int c[m];
    pair<int, int> sorted[m];
    for (int i = 0; i < m; i++) {
        cin >> c[i];
        sorted[i] = {c[i], i};
    }
    sort(sorted, sorted + m);

    int money[m];
    for (int i = 0; i < m; i++) {
        money[i] = i * x;
    }
    LazySegTree lst(m, money);
    int res = 0;
    for (auto [ci, i] : sorted) {
        int spend_less_than = lst.rmq(1, 0, m - 1, i, m - 1);
        if (ci <= spend_less_than) {
            res++;
            lst.update(1, 0, m - 1, i, m - 1, -ci);
        }
    }
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