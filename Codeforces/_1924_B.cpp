#include <bits/stdc++.h>
using namespace std;

long long aprog_sum(long long a, long long d, int n) {
    return (a + a + (n - 1) * d) * n / 2;
}

struct AProg {
    long long a, d;
    AProg() : a(0), d(0) {}
    AProg(long long aa, long long dd) : a(aa), d(dd) {}
    AProg operator+(const AProg &rhs) const {
        return AProg(a + rhs.a, d + rhs.d);
    }
};

struct LazySegTree {
    long long *t;
    AProg *lazy;
    LazySegTree(int n) {
        t = new long long[n * 4];
        lazy = new AProg[n * 4];
        for (int i = 0; i < n * 4; i++) {
            t[i] = 0;
        }
    }
    ~LazySegTree() {
        delete[] t; delete[] lazy;
    }
    void push(int v, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            AProg left(lazy[v].a + lazy[v].d * (r - m), lazy[v].d);
            t[v * 2] += aprog_sum(left.a, lazy[v].d, m - l + 1);
            t[v * 2 + 1] += aprog_sum(lazy[v].a, lazy[v].d, r - m);
            lazy[v * 2] = lazy[v * 2] + left;
            lazy[v * 2 + 1] = lazy[v * 2 + 1] + lazy[v];
        }
        lazy[v] = AProg();
    }
    void update(int v, int l, int r, int ql, int qr, AProg add) {
        if (ql > qr) {
            return;
        } else if (l == ql && r == qr) {
            t[v] += aprog_sum(add.a, add.d, r - l + 1);
            lazy[v] = lazy[v] + add;
        } else {
            push(v, l, r);
            int m = (l + r) / 2;
            int bound = min(m, qr);
            AProg left(add.a + (qr - bound) * add.d, add.d);
            update(v * 2, l, m, ql, bound, left);
            update(v * 2 + 1, m + 1, r, max(ql, m + 1), qr, add);
            t[v] = t[v * 2] + t[v * 2 + 1];
        }
    }
    long long sum(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return 0;
        } else if (l == ql && r == qr) {
            return t[v];
        } else {
            push(v, l, r);
            int m = (l + r) / 2;
            long long left = sum(v * 2, l, m, ql, min(m, qr));
            long long right = sum(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            return left + right;
        }
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, q;
    cin >> n >> m >> q;
    LazySegTree lst(n);
    set<int> harbors;
    vector<int> values(n);
    
    int coords[m];
    for (int i = 0; i < m; i++) {
        cin >> coords[i];
        coords[i]--;
        harbors.insert(coords[i]);
    }
    for (int i = 0; i < m; i++) {
        int v;
        cin >> v;
        values[coords[i]] = v;
    }

    for (auto it = harbors.begin(); it != harbors.end(); it++) {
        if (*it != 0) {
            int prev_h = *prev(it);
            lst.update(1, 0, n - 1, prev_h + 1, *it - 1, AProg(values[prev_h], values[prev_h]));
        }
    }

    for (int i = 0; i < q; i++) {
        int t;
        cin >> t;
        if (t == 1) {
            int x, v;
            cin >> x >> v;
            x--;
            auto ne = harbors.lower_bound(x);
            auto pr = prev(ne);
            AProg right(v - values[*pr], v - values[*pr]);
            lst.update(1, 0, n - 1, x + 1, *ne - 1, right);
            long long val = (long long) values[*pr] * (*ne - x);
            lst.update(1, 0, n - 1, *pr + 1, x, AProg(-val, 0));

            harbors.insert(x);
            values[x] = v;
        } else {
            int l, r;
            cin >> l >> r;
            cout << lst.sum(1, 0, n - 1, l - 1, r - 1) << "\n";
        }
    }
}