#include <bits/stdc++.h>
using namespace std;

struct SegTree {
    int *a, *tl, *tr, *sum;
    SegTree(int n) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = 0;
        }
        tl = new int[n * 4];
        tr = new int[n * 4];
        sum = new int[n * 4];
        construct(1, 0, n - 1);
    }
    ~SegTree() {
        delete[] a; delete[] tl; delete[] tr;
    }
    void construct(int v, int l, int r) {
        if (l == r) {
            tl[v] = l;
            tr[v] = l;
            sum[v] = a[l];
        } else {
            int m = (l + r) / 2;
            construct(v * 2, l, m);
            construct(v * 2 + 1, m + 1, r);
            tl[v] = tl[v * 2];
            if (a[tl[v * 2 + 1]] < a[tl[v * 2]]) {
                tl[v] = tl[v * 2 + 1];
            }
            tr[v] = tr[v * 2];
            if (a[tr[v * 2 + 1]] <= a[tr[v * 2]]) {
                tr[v] = tr[v * 2 + 1];
            }
            sum[v] = sum[v * 2] + sum[v * 2 + 1];
        }
    }
    int rmq_left(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return -1;
        } else if (l == ql && r == qr) {
            return tl[v];
        } else {
            int m = (l + r) / 2;
            int left = rmq_left(v * 2, l, m, ql, min(m, qr));
            int right = rmq_left(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            if (left != -1 && (right == -1 || a[left] <= a[right])) {
                return left;
            } else {
                return right;
            }
        }
    }
    int rmq_right(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return -1;
        } else if (l == ql && r == qr) {
            return tr[v];
        } else {
            int m = (l + r) / 2;
            int left = rmq_right(v * 2, l, m, ql, min(m, qr));
            int right = rmq_right(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            if (left != -1 && (right == -1 || a[left] < a[right])) {
                return left;
            } else {
                return right;
            }
        }
    }
    int sum_query(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return 0;
        } else if (l == ql && r == qr) {
            return sum[v];
        } else {
            int m = (l + r) / 2;
            int left = sum_query(v * 2, l, m, ql, min(m, qr));
            int right = sum_query(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            return left + right;
        }
    }
    void update(int v, int l, int r, int i, int val) {
        if (l == r) {
            a[l] = val;
            sum[v] = val;
            return;
        }
        int m = (l + r) / 2;
        if (i <= m) {
            update(v * 2, l, m, i, val);
        } else {
            update(v * 2 + 1, m + 1, r, i, val);
        }
        tl[v] = tl[v * 2];
        if (a[tl[v * 2 + 1]] < a[tl[v * 2]]) {
            tl[v] = tl[v * 2 + 1];
        }
        tr[v] = tr[v * 2];
        if (a[tr[v * 2 + 1]] <= a[tr[v * 2]]) {
            tr[v] = tr[v * 2 + 1];
        }
        sum[v] = sum[v * 2] + sum[v * 2 + 1];
    }
};

int cnt_len(int n, SegTree &st, string &s, int ind) {
    int left = st.rmq_right(1, 0, n - 1, 0, ind - 1);
    int right = st.rmq_left(1, 0, n - 1, ind + 1, n - 1);
    if (left != -1 && st.a[left] > -1) {
        left = -1;
    }
    if (right == -1 || st.a[right] > -1) {
        right = n;
    }
    int cnt_left = st.sum_query(1, 0, n - 1, left + 1, ind - 1);
    int cnt_right = st.sum_query(1, 0, n - 1, ind + 1, right - 1);
    if (s[ind] == '0') {
        return max(cnt_left, cnt_right);
    } else {
        return cnt_left + cnt_right + 1;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k;
    cin >> n >> k;
    pair<int, int> r[n];
    for (int i = 0; i < n; i++) {
        cin >> r[i].first;
        r[i].second = i;
    }
    sort(r, r + n);
    string s;
    cin >> s;

    SegTree st(n);
    int res = 0;
    int prev = n - 1;
    for (int i = n - 1; i >= 0; i--) {
        if (s[r[i].second] == '0') {
            st.update(1, 0, n - 1, r[i].second, -1);
        } else {
            st.update(1, 0, n - 1, r[i].second, 1);
        }
        if (i == 0 || r[i].first > r[i - 1].first) {
            bool found = false;
            for (int j = prev; j >= i; j--) {
                if (cnt_len(n, st, s, r[j].second) >= k) {
                    res = r[i].first;
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
            prev = i - 1;
        }
    }
    cout << res << "\n";
}