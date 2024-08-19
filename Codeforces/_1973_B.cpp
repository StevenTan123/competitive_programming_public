#include <bits/stdc++.h>
using namespace std;

const int MAXB = 25;

void solve() {
    int n;
    cin >> n;
    bool a[n][MAXB];
    int or_all = 0;
    for (int i = 0; i < n; i++) {
        int ai;
        cin >> ai;
        or_all |= ai;
        for (int j = 0; j < MAXB; j++) {
            a[i][j] = ai & 1;
            ai >>= 1;
        }
    }

    vector<bool> bits(MAXB);
    for (int i = 0; i < MAXB; i++) {
        bits[i] = or_all & 1;
        or_all >>= 1;
    }

    vector<int> cnt(MAXB);
    vector<int> r_bounds(n);
    int r = 0;
    int res = 0;
    for (int i = 0; i < n; i++) {
        if (i > 0) {
            for (int j = 0; j < MAXB; j++) {
                if (a[i - 1][j]) {
                    cnt[j]--;
                }
            }
        }
        bool found = false;
        while (r < n) {
            bool match = true;
            for (int j = 0; j < MAXB; j++) {
                if (a[r][j]) {
                    cnt[j]++;
                }
                if (cnt[j] == 0 && bits[j]) {
                    match = false;
                }
            }
            r++;
            if (match) {
                found = true;
                break;
            }
        }
        if (!found) {
            res = max(res, n - i + 1);
            break;
        }
        r_bounds[i] = r;
        res = max(res, r - i);
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