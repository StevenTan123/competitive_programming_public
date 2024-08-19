#include <bits/stdc++.h>
using namespace std;

const int inf = 1e9;

// Queries return leftmost index of the min.
struct LazySegTree {
    int *a, *t, *inds, *lazy;
    LazySegTree(int n, int _a[]) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = _a[i];
        }
        t = new int[n * 4];
        inds = new int[n * 4];
        lazy = new int[n * 4];
        for (int i = 0; i < n * 4; i++) {
            lazy[i] = 0;
        }
        construct(1, 0, n - 1);
    }
    ~LazySegTree() {
        delete[] a; delete[] t; delete[] inds; delete[] lazy;
    }
    void construct(int v, int l, int r) {
        if (l == r) {
            t[v] = a[l];
            inds[v] = l;
        } else {
            int m = (l + r) / 2;
            construct(v * 2, l, m);
            construct(v * 2 + 1, m + 1, r);
            t[v] = t[v * 2];
            inds[v] = inds[v * 2];
            if (t[v * 2 + 1] < t[v * 2]) {
                t[v] = t[v * 2 + 1];
                inds[v] = inds[v * 2 + 1];
            }
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
            t[v] = t[v * 2];
            inds[v] = inds[v * 2];
            if (t[v * 2 + 1] < t[v * 2]) {
                t[v] = t[v * 2 + 1];
                inds[v] = inds[v * 2 + 1];
            }
        }
    }
    pair<int, int> rmq(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return {-1, inf};
        } else if (l == ql && r == qr) {
            return {inds[v], t[v]};
        } else {
            push(v, l, r);
            int m = (l + r) / 2;
            pair<int, int> left = rmq(v * 2, l, m, ql, min(m, qr));
            pair<int, int> right = rmq(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            if (left.second <= right.second) {
                return left;
            } else {
                return right;
            }
        }
    }
};