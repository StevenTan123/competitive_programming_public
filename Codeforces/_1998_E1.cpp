#include <bits/stdc++.h>
using namespace std;

struct SegTree {
    vector<int> a;
    vector<long long> t;
    SegTree(int n) : a(n), t(4 * n) {}
    long long query(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return 0;
        } else if (l == ql && r == qr) {
            return t[v];
        } else {
            int m = (l + r) / 2;
            long long left = query(v * 2, l, m, ql, min(m, qr));
            long long right = query(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            return left + right;
        }
    }
    void update(int v, int l, int r, int i, int val) {
        if (l == r) {
            a[l] = val;
            t[v] = val;
            return;
        }
        int m = (l + r) / 2;
        if (i <= m) {
            update(v * 2, l, m, i, val);
        } else {
            update(v * 2 + 1, m + 1, r, i, val);
        }
        t[v] = t[v * 2] + t[v * 2 + 1];
    }
    // Finds smallest i s.t. sum of a[l...i] >= val
    int walk_left(int v, int l, int r, long long val) {
        if (t[v] < val) {
            return r + 1;
        }
        if (l == r) {
            return l;
        }
        int m = (l + r) / 2;
        if (t[v * 2] >= val) {
            return walk_left(v * 2, l, m, val);
        } else {
            return walk_left(v * 2 + 1, m + 1, r, val - t[v * 2]);
        }
    }
    // Finds largest i s.t. sum of a[i...r] >= val
    int walk_right(int v, int l, int r, long long val) {
        if (t[v] < val) {
            return l - 1;
        }
        if (l == r) {
            return l;
        }
        int m = (l + r) / 2;
        if (t[v * 2 + 1] >= val) {
            return walk_right(v * 2 + 1, m + 1, r, val);
        } else {
            return walk_right(v * 2, l, m, val - t[v * 2 + 1]);
        }
    }
};

void solve() {
    int n, x;
    cin >> n >> x;
    vector<int> a(n);
    SegTree st(n);
    long long rsum = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        rsum += a[i];
        st.update(1, 0, n - 1, i, a[i]);
    }

    set<int> active;
    vector<vector<int>> remove(n + 1);
    vector<int> blocked(n);
    long long lsum = 0;
    for (int i = 0; i < n; i++) {
        int left = st.walk_right(1, 0, n - 1, rsum + a[i]);
        auto it = active.lower_bound(left);
        if (it != active.end()) {
            blocked[*it + 1]++;
            blocked[i]--;
        }

        if (i > 0 && lsum < a[i]) {
            blocked[0]++;
            blocked[i]--;
        }
        rsum -= a[i];
        lsum += a[i];
        if (i < n - 1 && rsum < a[i]) {
            blocked[i + 1]++;
        }

        int right = st.walk_left(1, 0, n - 1, lsum + a[i]);
        active.insert(i);
        remove[right].push_back(i);

        for (int val : remove[i]) {
            active.erase(val);
        }
    }

    int cnt = 0;
    for (int i = 0; i < n; i++) {
        if (i > 0) {
            blocked[i] += blocked[i - 1];
        }
        if (blocked[i] == 0) {
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