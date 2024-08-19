#include <bits/stdc++.h>
using namespace std;

const int MAXV = 1000005;

struct LazySegTree {
    int *a, *t, *lazy;
    LazySegTree(int n) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = 0;
        }
        t = new int[n * 4];
        lazy = new int[n * 4];
        for (int i = 0; i < n * 4; i++) {
            t[i] = 0;
            lazy[i] = 0;
        }
    }
    ~LazySegTree() {
        delete[] a; delete[] t; delete[] lazy;
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
    int rightmost_neg(int v, int l, int r) {
        push(v, l, r);
        if (t[v] < 0) {
            int m = (l + r) / 2;
            if (l == r) {
                return l;
            } else if (t[v * 2 + 1] < 0) {
                return rightmost_neg(v * 2 + 1, m + 1, r);
            } else {
                return rightmost_neg(v * 2, l, m);
            }
        } else {
            return -1;
        }
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    LazySegTree lst(MAXV);
    int n, m;
    cin >> n >> m;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        lst.update(1, 0, MAXV - 1, 0, a[i], -1);
    }
    int b[m];
    for (int i = 0; i < m; i++) {
        cin >> b[i];
        lst.update(1, 0, MAXV - 1, 0, b[i], 1);
    }
    
    int q;
    cin >> q;
    for (int i = 0; i < q; i++) {
        int type, ind, x;
        cin >> type >> ind >> x;
        ind--;
        if (type == 1) {
            lst.update(1, 0, MAXV - 1, 0, a[ind], 1);
            a[ind] = x;
            lst.update(1, 0, MAXV - 1, 0, a[ind], -1);
        } else {
            lst.update(1, 0, MAXV - 1, 0, b[ind], -1);
            b[ind] = x;
            lst.update(1, 0, MAXV - 1, 0, b[ind], 1);
        }
        cout << lst.rightmost_neg(1, 0, MAXV - 1) << endl;
    }
}