#include <bits/stdc++.h>
using namespace std;

const double EPS = 1e-6;

struct SegTree {
    int *a, *t;
    SegTree(int n) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = 0;
        }
        t = new int[n * 4];
        for (int i = 0; i < n * 4; i++) {
            t[i] = 0;
        }
    }
    ~SegTree() {
        delete[] a; delete[] t;
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
};

bool possible(int n, int k, int a[], double min_temp) {
    double norm[n];
    double psum[n];
    pair<double, int> psum_sort[n];
    for (int i = 0; i < n; i++) {
        norm[i] = a[i] - min_temp;
        psum[i] = (i > 0 ? psum[i - 1] : 0) + norm[i];
        psum_sort[i] = {psum[i], i};
    }
    sort(psum_sort, psum_sort + n);

    int rank[n];
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        if (i == 0 || psum_sort[i].first - psum_sort[i - 1].first > EPS) {
            cnt++;
        }
        rank[psum_sort[i].second] = cnt;
    }
    cnt++;

    SegTree st(cnt);
    int dp[n];
    for (int i = 0; i < n; i++) {
        dp[i] = 0;
        if (psum[i] >= 0) {
            dp[i] = 1;
            dp[i] = max(dp[i], st.rmq(1, 0, cnt - 1, 0, rank[i]) + 1);
        }
        st.update(1, 0, cnt - 1, rank[i], dp[i]);
    }
    return dp[n - 1] >= k;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k;
    cin >> n >> k;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    double l = 0;
    double r = 1005;
    double res = 0;
    while ((r - l) > EPS) {
        double m = (l + r) / 2;
        if (possible(n, k, a, m)) {
            l = m;
            res = m;
        } else {
            r = m;
        }
    }
    cout << fixed << setprecision(10) << res << "\n";
}