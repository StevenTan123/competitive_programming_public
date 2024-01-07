#include <bits/stdc++.h>
using namespace std;

// Queries return leftmost index of the min.
struct SegTree {
    int *a, *t;
    SegTree(int n, int _a[]) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = _a[i];
        }
        t = new int[n * 4];
        construct(1, 0, n - 1);
    }
    ~SegTree() {
        delete[] a; delete[] t;
    }
    void construct(int v, int l, int r) {
        if (l == r) {
            t[v] = l;
        } else {
            int m = (l + r) / 2;
            construct(v * 2, l, m);
            construct(v * 2 + 1, m + 1, r);
            t[v] = t[v * 2];
            if (a[t[v * 2 + 1]] < a[t[v * 2]]) {
                t[v] = t[v * 2 + 1];
            }
        }
    }
    int rmq(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return -1;
        } else if (l == ql && r == qr) {
            return t[v];
        } else {
            int m = (l + r) / 2;
            int left = rmq(v * 2, l, m, ql, min(m, qr));
            int right = rmq(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            if (left != -1 && (right == -1 || a[left] <= a[right])) {
                return left;
            } else {
                return right;
            }
        }
    }
    void update(int v, int l, int r, int i, int val) {
        if (l == r) {
            a[l] = val;
            return;
        }
        int m = (l + r) / 2;
        if (i <= m) {
            update(v * 2, l, m, i, val);
        } else {
            update(v * 2 + 1, m + 1, r, i, val);
        }
        t[v] = t[v * 2];
        if (a[t[v * 2 + 1]] < a[t[v * 2]]) {
            t[v] = t[v * 2 + 1];
        }
    }
};