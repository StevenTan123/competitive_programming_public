#include <bits/stdc++.h>
using namespace std;

struct SegTree {
    int *a;
    int *t;
    int *inds;

    SegTree(int n) {
        a = new int[n];
        inds = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = -1;
            inds[i] = -1;
        }
        a[0] = 0;
        t = new int[n * 4];
        construct(1, 0, n - 1);
    }

    ~SegTree() {
        delete[] a;
        delete[] t;
        delete[] inds;
    }

    void construct(int v, int l, int r) {
        if (l == r) {
            t[v] = l;
        } else {
            int m = (l + r) / 2;
            construct(v * 2, l, m);
            construct(v * 2 + 1, m + 1, r);
            t[v] = t[v * 2 + 1];
            if (a[t[v * 2]] > a[t[v * 2 + 1]] || (a[t[v * 2]] == a[t[v * 2 + 1]] && inds[t[v * 2]] < inds[t[v * 2 + 1]])) {
                t[v] = t[v * 2];
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
            if (left != -1 && (right == -1 || a[left] > a[right] || (a[left] == a[right] && inds[left] < inds[right]))) {
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
        t[v] = t[v * 2 + 1];
        if (a[t[v * 2]] > a[t[v * 2 + 1]] || (a[t[v * 2]] == a[t[v * 2 + 1]] && inds[t[v * 2]] < inds[t[v * 2 + 1]])) {
            t[v] = t[v * 2];
        }
    }
};

void solve() {
    int n;
    cin >> n;
    int a[n];
    pair<int, int> sorted[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        sorted[i] = {a[i], i};
    }
    sort(sorted, sorted + n);

    int count = 0;
    for (int i = 0; i < n; i++) {
        if (i > 0 && sorted[i].first > sorted[i - 1].first) {
            count++;
        }
        a[sorted[i].second] = count;
    }
    count++;

    SegTree st(count);
    SegTree st2(n);
    int dp[n];
    int res = 0;
    for (int i = 0; i < n; i++) {
        st2.update(1, 0, n - 1, i, a[i]);
        dp[i] = 1;
        int ind = st.rmq(1, 0, count - 1, 0, a[i]);
        int max_ind = st2.rmq(1, 0, n - 1, st.inds[ind] + 1, i - 1);
        int val = st.a[ind] + 1;
        if (max_ind != -1 && a[max_ind] > a[i]) val++;
        dp[i] = max(dp[i], val);
        if (dp[i] > st.a[a[i]]) {
            st.inds[a[i]] = i;
            st.update(1, 0, count - 1, a[i], dp[i]);
        }
        res = max(res, dp[i]);
    }
    cout << res << "\n";
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