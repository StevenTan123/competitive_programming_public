#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    bool same_parity = true;
    int minv = INT_MAX;
    int maxv = INT_MIN;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        if (i > 0 && a[i] % 2 != a[i - 1] % 2) {
            same_parity = false;
        }
        minv = min(minv, a[i]);
        maxv = max(maxv, a[i]);
    }

    if (same_parity) {
        vector<int> res;
        while (minv < maxv) {
            int x = (minv + maxv) / 2;
            res.push_back(x);
            minv = INT_MAX;
            maxv = INT_MIN;
            for (int i = 0; i < n; i++) {
                a[i] = abs(x - a[i]);
                minv = min(minv, a[i]);
                maxv = max(maxv, a[i]);
            }
        }
        res.push_back(minv);
        cout << res.size() << "\n";
        for (int val : res) {
            cout << val << " ";
        }
        cout << "\n";
    } else {
        cout << "-1\n";
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