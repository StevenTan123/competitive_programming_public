#include <bits/stdc++.h>
using namespace std;

struct SegTree {
    int *a, *minv, *maxv;
    SegTree(int n, int _a[]) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = _a[i];
        }
        minv = new int[n * 4];
        maxv = new int[n * 4];
        construct(1, 0, n - 1);
    }
    ~SegTree() {
        delete[] a; delete[] minv; delete[] maxv;
    }
    void construct(int v, int l, int r) {
        if (l == r) {
            minv[v] = a[l];
            maxv[v] = a[r];
        } else {
            int m = (l + r) / 2;
            construct(v * 2, l, m);
            construct(v * 2 + 1, m + 1, r);
            minv[v] = min(minv[v * 2], minv[v * 2 + 1]);
            maxv[v] = max(maxv[v * 2], maxv[v * 2 + 1]);
        }
    }
    int min_query(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return INT_MAX;
        } else if (l == ql && r == qr) {
            return minv[v];
        } else {
            int m = (l + r) / 2;
            int left = min_query(v * 2, l, m, ql, min(m, qr));
            int right = min_query(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            return min(left, right);
        }
    }
    int max_query(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return INT_MIN;
        } else if (l == ql && r == qr) {
            return maxv[v];
        } else {
            int m = (l + r) / 2;
            int left = max_query(v * 2, l, m, ql, min(m, qr));
            int right = max_query(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            return max(left, right);
        }
    }
    void update(int v, int l, int r, int i, int val) {
        if (l == r) {
            a[l] = val;
            minv[v] = val;
            maxv[v] = val;
            return;
        }
        int m = (l + r) / 2;
        if (i <= m) {
            update(v * 2, l, m, i, val);
        } else {
            update(v * 2 + 1, m + 1, r, i, val);
        }
        minv[v] = min(minv[v * 2], minv[v * 2 + 1]);  
        maxv[v] = max(maxv[v * 2], maxv[v * 2 + 1]);
    }
};

pair<int, int> find_subarray(int n, int a[], set<int> &invs, SegTree &st) {
    auto l_it = invs.begin();
    auto r_it = invs.rbegin();
    if (l_it == invs.end()) {
        return {-1, -1};
    }
    int left = *l_it;
    int right = *r_it + 1;
    int minv = st.min_query(1, 0, n - 1, left, right);
    int maxv = st.max_query(1, 0, n - 1, left, right);

    int l = right + 1;
    int r = n - 1;
    int r_ind = n;
    while (l <= r) {
        int m = (l + r) / 2;
        if (a[m] >= maxv) {
            r_ind = m;
            r = m - 1;
        } else {
            l = m + 1;
        }
    }

    l = 0;
    r = left - 1;
    int l_ind = -1;
    while (l <= r) {
        int m = (l + r) / 2;
        if (a[m] <= minv) {
            l_ind = m;
            l = m + 1;
        } else {
            r = m - 1;
        }
    }
    return {l_ind + 1 + 1, r_ind - 1 + 1};
}

void solve() {
    int n;
    cin >> n;
    int a[n];
    set<int> invs;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        if (i > 0 && a[i] < a[i - 1]) {
            invs.insert(i - 1);
        }
    }
    SegTree st(n, a);
    auto [l1, r1] = find_subarray(n, a, invs, st);
    cout << l1 << " " << r1 << "\n";

    int q;
    cin >> q;
    for (int i = 0; i < q; i++) {
        int ind, val;
        cin >> ind >> val;
        ind--;
        if (ind > 0 && a[ind - 1] > a[ind]) {
            invs.erase(ind - 1);
        }
        if (ind < n - 1 && a[ind] > a[ind + 1]) {
            invs.erase(ind);
        }
        a[ind] = val;
        st.update(1, 0, n - 1, ind, val);
        if (ind > 0 && a[ind - 1] > a[ind]) {
            invs.insert(ind - 1);
        }
        if (ind < n - 1 && a[ind] > a[ind + 1]) {
            invs.insert(ind);
        }
        auto [li, ri] = find_subarray(n, a, invs, st);
        cout << li << " " << ri << "\n";
    }
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