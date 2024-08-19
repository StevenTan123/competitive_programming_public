#include <bits/stdc++.h>
using namespace std;

struct SegTree {
    int *a, *t;
    SegTree(int n, int _a[]) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = _a[i];
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
            t[v] = min(t[v * 2], t[v * 2 + 1]);
        }
    }
    // Returns smallest i such that ql <= i <= qr and a[i] < x. If no such i exists, return -1.
    int less_than(int v, int l, int r, int ql, int qr, int x) {
        if (ql > qr) {
            return -1;
        } else if (l == r && t[v] < x) {
            return l;
        } else if (l == ql && r == qr && t[v] >= x) {
            return -1;
        }
        int m = (l + r) / 2;
        int left = less_than(v * 2, l, m, ql, min(m, qr), x);
        if (left == -1) {
            return less_than(v * 2 + 1, m + 1, r, max(ql, m + 1), qr, x);
        } 
        return left;
    }
};

void solve() {
    int n;
    cin >> n;
    int a[n];
    int c[n];
    long long ssum = 0;
    int sign = 1;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        c[i] = a[i] - (i > 0 ? c[i - 1] : 0);
        ssum += a[i] * sign;
        sign *= -1;
    }
    
    if (n == 1) {
        if (a[0] == 0) {
            cout << "1\n";
        } else {
            cout << "0\n";
        }
        return;
    }

    int odds[n / 2];
    int evens[n - n / 2];
    for (int i = 0; i < n; i += 2) {
        evens[i / 2] = c[i];
    }
    for (int i = 1; i < n; i += 2) {
        odds[i / 2] = c[i];
    }

    SegTree ost(n / 2, odds);
    SegTree est(n - n / 2, evens);

    unordered_map<long long, vector<int>> suffixes;
    vector<long long> ssums(n);
    vector<int> inds(n);
    sign = 1;
    for (int i = 0; i < n; i++) {
        inds[i] = suffixes[ssum].size();
        suffixes[ssum].push_back(i);
        ssums[i] = ssum;
        ssum -= a[i] * sign;
        sign *= -1;
    }
    suffixes[0].push_back(n);
    
    long long res = 0;
    for (int i = 0; i < n; i++) {
        int even_r, odd_r;
        if (i % 2 == 0) {
            int x = (i > 0 ? odds[i / 2 - 1] : 0);
            even_r = est.less_than(1, 0, n - n / 2 - 1, i / 2, n - n / 2 - 1, -x);
            odd_r = ost.less_than(1, 0, n / 2 - 1, i / 2, n / 2 - 1, x);
        } else {
            int x = evens[i / 2];
            even_r = est.less_than(1, 0, n - n / 2 - 1, i / 2 + 1, n - n / 2 - 1, x);
            odd_r = ost.less_than(1, 0, n / 2 - 1, i / 2, n / 2 - 1, -x);
        }

        if (even_r == -1) {
            even_r = n;
        } else {
            even_r *= 2;
        }
        if (odd_r == -1) {
            odd_r = n;
        } else {
            odd_r = 2 * odd_r + 1;
        }
        int r = min(even_r, odd_r);

        vector<int> &c_inds = suffixes[ssums[i]];

        int left = inds[i];
        int right = c_inds.size() - 1;
        long long add = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (c_inds[mid] <= r) {
                add = mid - inds[i];
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        res += add;
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