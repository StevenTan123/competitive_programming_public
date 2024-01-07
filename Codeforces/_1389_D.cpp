#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;
    long long l1, r1, l2, r2;
    cin >> l1 >> r1 >> l2 >> r2;
    if (l1 > l2) {
        swap(l1, l2);
        swap(r1, r2);
    }
    long long inter = max(min(r1, r2) - l2, 0ll);
    long long activation = max(l2 - r1, 0ll);
    long long max_single = max(r1, r2) - min(l1, l2);
    
    long long res = LONG_LONG_MAX;
    for (int i = 1; i <= n; i++) {
        long long cur_activ = activation * i;
        if (max_single * i >= k) {
            long long left = k - inter * i;
            if (left <= 0) {
                res = 0;
                break;
            }
            res = min(res, cur_activ + left);
        } else {
            long long left = k - max_single * i;
            long long cur_moves = cur_activ + (max_single - inter) * i;
            res = min(res, cur_moves + left * 2);
        }
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