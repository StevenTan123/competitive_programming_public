#include <bits/stdc++.h>
using namespace std;

void max_range(int l, int r, vector<pair<int, int>> &ops) {
    if (r > l) {
        max_range(l, r - 1, ops);
        ops.emplace_back(l, r);
        ops.emplace_back(l, r - 1);
        max_range(l, r - 1, ops);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    // dp[i] = max sum if we do operations on a[0...i]
    vector<int> dp(n);
    vector<int> prev(n);
    vector<bool> change(n, 0);
    for (int i = 0; i < n; i++) {
        dp[i] = (i > 0 ? dp[i - 1] : 0) + a[i];
        prev[i] = i - 1;
        for (int j = i; j >= 0; j--) {
            int val = (j > 0 ? dp[j - 1] : 0) + (i - j + 1) * (i - j + 1);
            if (val > dp[i]) {
                dp[i] = val;
                prev[i] = j - 1;
                change[i] = 1;
            }
        }
    }

    int cur = n - 1;
    vector<pair<int, int>> ops;
    while (cur != -1) {
        int pprev = prev[cur];
        if (change[cur]) {
            bool has_zero = false;
            for (int i = pprev + 1; i <= cur; i++) {
                if (a[i] == 0) {
                    has_zero = true;
                    break;
                }
            }
            ops.emplace_back(pprev + 1, cur);
            if (has_zero) {
                ops.emplace_back(pprev + 1, cur);
            }
            max_range(pprev + 1, cur, ops);
            ops.emplace_back(pprev + 1, cur);
        }
        cur = pprev;
    }
    cout << dp[n - 1] << " " << ops.size() << "\n";
    for (auto [l, r] : ops) {
        cout << l + 1 << " " << r + 1 << "\n";
    }
}