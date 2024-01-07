#include <bits/stdc++.h>
using namespace std;

const long long MAXK = 1e18 + 5;

struct SegTree {
    int *a;
    long long *b;
    // stores {max val in a, sum of b with max val}
    pair<int, long long> *t;
    SegTree(int n) {
        a = new int[n];
        b = new long long[n];
        for (int i = 0; i < n; i++) {
            a[i] = 0;
            b[i] = 0;
        }
        t = new pair<int, long long>[n * 4];
        construct(1, 0, n - 1);
    }

    ~SegTree() {
        delete[] a;
        delete[] b;
        delete[] t;
    }

    void construct(int v, int l, int r) {
        if (l == r) {
            t[v] = {a[l], b[l]};
        } else {
            int m = (l + r) / 2;
            construct(v * 2, l, m);
            construct(v * 2 + 1, m + 1, r);
            t[v] = t[v * 2];
            if (t[v * 2 + 1].first > t[v].first) {
                t[v] = t[v * 2 + 1];
            } else if (t[v * 2 + 1].first == t[v].first) {
                t[v].second += t[v * 2 + 1].second;
            }
        }
    }
    
    pair<int, long long> rmq(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return {0, 0};
        } else if (l == ql && r == qr) {
            return t[v];
        } else {
            int m = (l + r) / 2;
            pair<int, long long> left = rmq(v * 2, l, m, ql, min(m, qr));
            pair<int, long long> right = rmq(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            if (left.first > right.first) {
                return left;
            } else if (left.first == right.first) {
                return {left.first, min(left.second + right.second, MAXK)};
            } else {
                return right;
            }
        }
    }

    void update(int v, int l, int r, int i, int a_val, long long b_val) {
        if (l == r) {
            if (a_val > a[l]) {
                a[l] = a_val;
                b[l] = b_val;
            } else if (a_val == a[l]) {
                b[l] += b_val;
            }
            t[v] = {a_val, b_val};
            return;
        }
        int m = (l + r) / 2;
        if (i <= m) {
            update(v * 2, l, m, i, a_val, b_val);
        } else {
            update(v * 2 + 1, m + 1, r, i, a_val, b_val);
        }
        t[v] = t[v * 2];
        if (t[v * 2 + 1].first > t[v].first) {
            t[v] = t[v * 2 + 1];
        } else if (t[v * 2 + 1].first == t[v].first) {
            t[v].second = min(t[v].second + t[v * 2 + 1].second, MAXK);
        }
    }
};

int main() {
    ifstream in("itout.in");
    ofstream out("itout.out");

    int N;
    long long K;
    in >> N >> K;
    int p[N];
    for (int i = 0; i < N; i++) {
        in >> p[i];
        p[i]--;
    }

    SegTree st(N);
    int LIS[N];
    long long dp[N];
    int m_LIS = 0;
    for (int i = N - 1; i >= 0; i--) {
        pair<int, long long> best = st.rmq(1, 0, N - 1, p[i], N - 1);
        LIS[i] = best.first + 1;
        dp[i] = (best.second == 0 ? 1 : best.second);
        st.update(1, 0, N - 1, p[i], LIS[i], dp[i]);
        m_LIS = max(m_LIS, LIS[i]);
    }
    vector<pair<int, int>> inds[m_LIS];
    for (int i = 0; i < N; i++) {
        inds[m_LIS - LIS[i]].push_back({-p[i], i});
    }
    for (int i = 0; i < m_LIS; i++) {
        sort(inds[i].begin(), inds[i].end());
    }

    long long num_LIS = 0;
    int prev = -1;
    bool in_K_LIS[N] = {false};
    for (int i = 0; i < m_LIS; i++) {
        for (pair<int, int> pr : inds[i]) {
            int ind = pr.second;
            if (prev != -1 && (prev > ind || p[prev] > p[ind])) {
                continue;
            }
            if (num_LIS + dp[ind] >= K) {
                in_K_LIS[ind] = true;
                prev = ind;
                break;
            }
            num_LIS += dp[ind];
        }
    }
    
    vector<int> res;
    for (int i = 0; i < N; i++) {
        if (!in_K_LIS[i]) {
            res.push_back(p[i] + 1);
        }
    }
    sort(res.begin(), res.end());
    out << res.size() << "\n";
    for (int i = 0; i < res.size(); i++) {
        out << res[i] << "\n";
    }
}