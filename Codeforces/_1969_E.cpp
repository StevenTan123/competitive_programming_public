#include <bits/stdc++.h>
using namespace std;

#include <bits/stdc++.h>
using namespace std;

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
            lazy[i] = 0;
            t[i] = 0;
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
};

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i]--;
    }

    LazySegTree lst(n);
    vector<int> occs[n];
    int prev = -1;
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        vector<int> &occ = occs[a[i]];
        if (occ.size() >= 2) {
            int ind = occ[occ.size() - 2];
            lst.update(1, 0, n - 1, 0, ind, 1);
        }
        if (occ.size() >= 1) {
            int ind = occ[occ.size() - 1];
            lst.update(1, 0, n - 1, 0, ind, -2);
        }
        lst.update(1, 0, n - 1, 0, i, 1);
        occ.push_back(i);

        int min_val = lst.rmq(1, 0, n - 1, prev + 1, i);
        if (min_val == 0) {
            prev = i;
            cnt++;
        }
    }
    cout << cnt << "\n";
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