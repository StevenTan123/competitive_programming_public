#include <bits/stdc++.h>
using namespace std;

const int MAXB = 30;

void solve() {
    int n, x;
    cin >> n >> x;
    
    int xcur = x;
    bool x_bits[MAXB];
    for (int i = MAXB - 1; i >= 0; i--) {
        x_bits[i] = xcur & 1;
        xcur >>= 1;
    }
    
    int a[n];
    bool bits[n][MAXB];
    int xor_all = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        xor_all ^= a[i];
        int cur = a[i];
        for (int j = MAXB - 1; j >= 0; j--) {
            bits[i][j] = cur & 1;
            cur >>= 1;
        }
    }
    if (xor_all > x) {
        cout << "-1\n";
        return;
    }

    // maxs[i] = max segments to get all bits up to i <= x
    int maxs[MAXB + 1];
    for (int i = 0; i <= MAXB; i++) {
        vector<bool> cur(MAXB, 0);
        maxs[i] = 0;
        for (int j = 0; j < n; j++) {
            bool works = true;
            for (int k = 0; k <= min(i, MAXB - 1); k++) {
                cur[k] = (cur[k] != bits[j][k]);
                if (!x_bits[k] || k == i) {
                    if (cur[k]) {
                        works = false;
                    }
                }
            }
            if (works) {
                maxs[i]++;
            }
            if (j == n - 1 && !works) {
                maxs[i] = -1;
            }
        }
    }

    int res = 1;
    for (int i = 0; i <= MAXB; i++) {
        if (i == MAXB || x_bits[i]) {
            res = max(res, maxs[i]);
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