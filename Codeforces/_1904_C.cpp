#include <bits/stdc++.h>
using namespace std;

const long long inf = 1e18 + 5;

int bsearch(int n, long long a[], long long val) {
    int l = 0;
    int r = n - 1;
    int res = -1;
    while (l <= r) {
        int m = (l + r) / 2;
        if (a[m] < val) {
            l = m + 1;
            res = m;
        } else {
            r = m - 1;
        }
    }
    return res;
}

void solve() {
    int n, k;
    cin >> n >> k;
    long long a[n];
    long long min_val = inf;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        min_val = min(min_val, a[i]);
    }
    sort(a, a + n);

    long long min_gap = inf;
    for (int i = 1; i < n; i++) {
        min_gap = min(min_gap, a[i] - a[i - 1]);
    }

    if (k == 1) {
        cout << min(min_gap, min_val) << endl;
    } else if (k >= 3) {
        cout << 0 << endl;
    } else {
        long long res = inf;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long long diff = a[j] - a[i];
                int ind = bsearch(n, a, diff);
                
                long long cur_min_val = min(min_val, diff);
                long long cur_min_gap = min_gap;
                if (ind < n - 1) {
                    cur_min_gap = min(cur_min_gap, a[ind + 1] - diff);
                }
                if (ind >= 0) {
                    cur_min_gap = min(cur_min_gap, diff - a[ind]);
                }
                res = min(res, min(cur_min_val, cur_min_gap));
            }
        }
        cout << res << endl;
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