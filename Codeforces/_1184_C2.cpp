#include <bits/stdc++.h>
using namespace std;

const int OFFSET = 2000000;
const int MAXC = 6000005;

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
            t[v] = max(t[v * 2], t[v * 2 + 1]);
        }
    }
    int rmq(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return INT_MIN;
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

struct Seg {
    int x, ymin, ymax, t;
    Seg(int xx, int ymi, int yma, int tt) : x(xx), ymin(ymi), ymax(yma), t(tt) {}
    bool operator<(const Seg &o) const {
        if (x == o.x) {
            return t < o.t;
        }
        return x < o.x;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, r;
    cin >> n >> r;
    vector<Seg> segs;
    for (int i = 0; i < n; i++) {
        int xi, yi;
        cin >> xi >> yi;
        
        int x = xi - yi;
        int y = xi + yi;
        y += OFFSET;
        segs.push_back(Seg(x, y, y + 2 * r, 0));
        segs.push_back(Seg(x + 2 * r, y, y + 2 * r, 1));
    }
    sort(segs.begin(), segs.end());

    LazySegTree lst(MAXC);
    int res = 0;
    for (Seg s : segs) {
        int add = (s.t == 0 ? 1 : -1);
        lst.update(1, 0, MAXC - 1, s.ymin, s.ymax, add);
        res = max(res, lst.rmq(1, 0, MAXC - 1, 0, MAXC - 1));
    }
    cout << res << endl;
}