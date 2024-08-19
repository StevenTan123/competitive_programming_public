#include <bits/stdc++.h>
using namespace std;

int solve() {
    int n, k;
    cin >> n >> k;

    int maxv = -1;
    for (int i = 1; i <= n; i++) {
        cout << "? 1 " << i * n << endl;
        int r;
        cin >> r;
        if (r == n) {
            maxv = i;
            break;
        }
    }

    int res = -1;
    for (int i = 1; i <= n / k; i++) {
        int m = i * maxv;
        int cur = 1;
        bool possible = true;
        for (int j = 0; j < k; j++) {
            if (cur > n) {
                possible = false;
                break;
            }
            cout << "? " << cur << " " << m << endl;
            int r;
            cin >> r;
            if (r > n) {
                possible = false;
                break;
            }
            cur = r + 1;
        }
        if (possible && cur == n + 1) {
            res = max(res, m);
        }
    }
    cout << "! " << res << endl;
    int success;
    cin >> success;
    return success;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t--) {
        int success = solve();
        if (success != 1) {
            break;
        }
    }
}