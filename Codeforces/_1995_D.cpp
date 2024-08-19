#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, c, k;
    cin >> n >> c >> k;
    string s;
    cin >> s;

    vector<vector<int>> psum(c, vector<int>(n));
    for (int i = 0; i < c; i++) {
        for (int j = 0; j < n; j++) {
            psum[i][j] = (j > 0 ? psum[i][j - 1] : 0) + (s[j] - 'A' == i); 
        }
    }

    vector<bool> masks(1 << c);
    for (int i = 0; i < n - k + 1; i++) {
        int cur = 0;
        for (int j = 0; j < c; j++) {
            int cnt = psum[j][i + k - 1] - (i > 0 ? psum[j][i - 1] : 0);
            if (cnt > 0) {
                cur = cur | (1 << j);
            }
        }
        masks[cur] = true;
    }
    for (int i = 0; i < (1 << c); i++) {
        if (masks[i]) {
            for (int j = 0; j < c; j++) {
                masks[i | (1 << j)] = true;
            }
        }
    }

    int res = c;
    for (int i = 0; i < (1 << c); i++) {
        bool bad = masks[((1 << c) - 1) ^ i];
        if ((i & (1 << (s[n - 1] - 'A'))) && !bad) {
            res = min(res, __builtin_popcount(i));
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