#include <bits/stdc++.h>
using namespace std;

const int MAXA = 2000005; 

struct SegTree {
    int a[MAXA], t[4 * MAXA];
    SegTree() {
        for (int i = 0; i < MAXA; i++) {
            a[i] = 0;
            t[i] = 0;
        }
    }
    int rmq(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return 0;
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
        t[v] = max(t[v * 2], t[v * 2 + 1]);
    }
    // Finds smallest i s.t. a[i] >= val
    int walk(int v, int l, int r, int val) {
        if (t[v] < val) {
            return MAXA;
        }
        if (l == r) {
            return l;
        }
        int m = (l + r) / 2;
        if (t[v * 2] >= val) {
            return walk(v * 2, l, m, val);
        } else {
            return walk(v * 2 + 1, m + 1, r, val);
        }
    }
};

SegTree st;

void solve() {
    int n;
    cin >> n;
    set<int> active;
    active.insert(0);
    int prev = 1;
    for (int i = 0; i < n; i++) {
        int ai;
        cin >> ai;
        active.insert(ai);
        if (ai > prev) {
            st.update(1, 0, MAXA - 1, prev, ai - prev);   
        }
        prev = ai + 1;
    }
    st.update(1, 0, MAXA - 1, prev, MAXA);

    int m;
    cin >> m;
    for (int i = 0; i < m; i++) {
        char type;
        int val;
        cin >> type >> val;
        if (type == '+') {
            auto after = active.upper_bound(val);
            auto before = std::prev(after);
            st.update(1, 0, MAXA - 1, *before + 1, val - *before - 1);
            st.update(1, 0, MAXA - 1, val + 1, after == active.end() ? MAXA : *after - val - 1);
            active.insert(val);
        } else if (type == '-') {
            active.erase(val);
            auto after = active.upper_bound(val);
            auto before = std::prev(after);
            st.update(1, 0, MAXA - 1, *before + 1, after == active.end() ? MAXA : *after - *before - 1);
            st.update(1, 0, MAXA - 1, val + 1, 0);
        } else {
            int res = st.walk(1, 0, MAXA - 1, val);
            cout << res << " ";
        }
    }
    cout << "\n";

    for (int val : active) {
        st.update(1, 0, MAXA - 1, val + 1, 0);
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