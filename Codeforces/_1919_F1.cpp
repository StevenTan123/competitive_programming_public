#include <bits/stdc++.h>
using namespace std;

// Queries return max value in a range
struct LazySegTree {
    long long *a, *t, *lazy;
    LazySegTree(int n, long long _a[]) {
        a = new long long[n];
        for (int i = 0; i < n; i++) {
            a[i] = _a[i];
        }
        t = new long long[n * 4];
        lazy = new long long[n * 4];
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
            t[v] = max(t[v * 2], t[v * 2 + 1]);
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
    void update(int v, int l, int r, int ql, int qr, long long add) {
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
    long long rmq(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return INT_MIN;
        } else if (l == ql && r == qr) {
            return t[v];
        } else {
            push(v, l, r);
            int m = (l + r) / 2;
            long long left = rmq(v * 2, l, m, ql, min(m, qr));
            long long right = rmq(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            return max(left, right);
        }
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, q;
    cin >> n >> q;
    int a[n];
    int b[n];
    long long diff[n];
    long long suf[n];
    long long trash;
    long long a_sum = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a_sum += a[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> b[i];
        diff[i] = a[i] - b[i];
    }
    for (int i = n - 1; i >= 0; i--) {
        if (i > 0) cin >> trash;
        suf[i] = (i < n - 1 ? suf[i + 1] : 0) + diff[i];
    }

    LazySegTree lst(n, suf);
    for (int i = 0; i < q; i++) {
        int p, x, y;
        cin >> p >> x >> y >> trash;
        p--;
        long long cur_diff = x - y;
        long long add = cur_diff - diff[p];
        diff[p] = cur_diff;
        a_sum += x - a[p];
        a[p] = x;
        lst.update(1, 0, n - 1, 0, p, add);
        long long left = max(lst.rmq(1, 0, n - 1, 0, n - 1), 0ll);
        cout << a_sum - left << "\n";
    }
}