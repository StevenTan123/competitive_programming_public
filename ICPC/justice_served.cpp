#include <bits/stdc++.h>
using namespace std;

struct SegTree {
    int *a, *t;
    SegTree(int n) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = -1;
        }
        t = new int[n * 4];
        construct(1, 0, n - 1);
    }
    ~SegTree() {
        delete[] a; delete[] t;
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
    int rmq(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return -1;
        } else if (l == ql && r == qr) {
            return t[v];
        } else {
            int m = (l + r) / 2;
            int left = rmq(v * 2, l, m, ql, min(m, qr));
            int right = rmq(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            return max(left, right);
        }
    }
    void update(int v, int l, int r, int i, int val) {
        if (l == r) {
            a[l] = max(a[l], val);
            t[v] = a[l];
            return;
        }
        int m = (l + r) / 2;
        if (i <= m) {
            update(v * 2, l, m, i, val);
        } else {
            update(v * 2 + 1, m + 1, r, i, val);
        }
        t[v] = max(t[v * 2], t[v * 2 + 1]);
    }
};

struct Seg {
    int l, r, ind;
    Seg() {}
    Seg(int ll, int rr, int i) : l(ll), r(rr), ind(i) {}
    bool operator<(const Seg &o) const {
        if (r > o.r) {
            return true;
        } else if (r < o.r) {
            return false;
        }
        return l < o.l;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    set<int> coords;
    Seg segs[n];
    for (int i = 0; i < n; i++) {
        int a, t;
        cin >> a >> t;
        coords.insert(a);
        coords.insert(a + t);
        segs[i] = Seg(a, a + t, i);
    }
    map<int, int> compress;
    int count = 0;
    for (int coord : coords) {
        compress[coord] = count;
        count++;
    }
    for (int i = 0; i < n; i++) {
        segs[i].l = compress[segs[i].l];
        segs[i].r = compress[segs[i].r];
    }
    sort(segs, segs + n);

    SegTree st(count);
    int res[n];
    for (int i = 0; i < n; i++) {
        int l = segs[i].l;
        int max_convince = max(0, st.rmq(1, 0, count - 1, 0, l) + 1);
        st.update(1, 0, count - 1, l, max_convince);
        res[segs[i].ind] = max_convince;
    }
    
    for (int i = 0; i < n; i++) {
        cout << res[i] << " ";
    }
    cout << endl;
}