#include <bits/stdc++.h>
using namespace std;
 
struct SegTreeMax {
    int *a;
    int *t;
    SegTreeMax(int n, int aa[]) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = aa[i];
        }
        t = new int[n * 4];
        construct(1, 0, n - 1);
    }

    ~SegTreeMax() {
        delete[] a;
        delete[] t;
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
            return INT_MIN;
        } else if (l == ql && r == qr) {
            return t[v];
        } else {
            int m = (l + r) / 2;
            int left = rmq(v * 2, l, m, ql, min(m, qr));
            int right = rmq(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            return max(left, right);
        }
    }
};

struct SegTreeMin {
    int *a;
    int *t;
    SegTreeMin(int n, int aa[]) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = aa[i];
        }
        t = new int[n * 4];
        construct(1, 0, n - 1);
    }

    ~SegTreeMin() {
        delete[] a;
        delete[] t;
    }

    void construct(int v, int l, int r) {
        if (l == r) {
            t[v] = a[l];
        } else {
            int m = (l + r) / 2;
            construct(v * 2, l, m);
            construct(v * 2 + 1, m + 1, r);
            t[v] = min(t[v * 2], t[v * 2 + 1]);
        }
    }
    
    int rmq(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return INT_MAX;
        } else if (l == ql && r == qr) {
            return t[v];
        } else {
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
    int b[n];
    int prev[n];
    int prev2[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i]--;
        prev[i] = -1;
        prev2[i] = n;
    }
    for (int i = 0; i < n; i++) {
        cin >> b[i];
        b[i]--;
    }
    SegTreeMax st_a(n, a);
    SegTreeMin st_b(n, b);

    bool marked[n] = {0};
    for (int i = 0; i < n; i++) {
        prev[a[i]] = i;
        if (a[i] <= b[i]) {
            if (prev[b[i]] != -1) {
                int max_a = st_a.rmq(1, 0, n - 1, prev[b[i]], i);
                int min_b = st_b.rmq(1, 0, n - 1, prev[b[i]], i);
                if (max_a == b[i] && min_b == b[i]) {
                    marked[i] = true;
                }
            }
        }
    }

    for (int i = n - 1; i >= 0; i--) {
        prev2[a[i]] = i;
        if (a[i] <= b[i]) {
            if (prev2[b[i]] != n) {
                int max_a = st_a.rmq(1, 0, n - 1, i, prev2[b[i]]);
                int min_b = st_b.rmq(1, 0, n - 1, i, prev2[b[i]]);
                if (max_a == b[i] && min_b == b[i]) {
                    marked[i] = true;
                }
            }
        }
    }

    bool possible = true;
    for (int i = 0; i < n; i++) {
        possible = possible && marked[i];
    }
    cout << (possible ? "YES" : "NO") << endl;
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