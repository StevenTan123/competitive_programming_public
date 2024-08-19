#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;

    int a[n][n];
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            cin >> a[i][j];
        }
    }

    // dp[i] stores list of k highest beauty subsets of 1...i
    vector<int> dp[n];
    for (int i = 0; i < n; i++) {
        // {beauty, i (previous dp[i]), j (j-th highest beauty of dp[i])}
        priority_queue<tuple<int, int, int>> pq;
        pq.emplace(a[0][i], -1, -1);
        if (i > 0) {
            pq.emplace(a[1][i], -1, -1);
        } else {
            pq.emplace(0, -1, -1);
        }
        for (int j = 0; j < i; j++) {
            pq.emplace(dp[j][0] + (j < i - 1 ? a[j + 2][i] : 0), j, 0);
        }

        int cnt = 0;
        while (!pq.empty() && cnt < k) {
            auto [beauty, ind, j] = pq.top();
            pq.pop();

            dp[i].push_back(beauty);
            if (j != -1 && j < (int) dp[ind].size() - 1) {
                pq.emplace(dp[ind][j + 1] + (ind < i - 1 ? a[ind + 2][i] : 0), ind, j + 1);
            }

            cnt++;
        }
    }

    for (int i = 0; i < k; i++) {
        cout << dp[n - 1][i] << " ";
    }
    cout << "\n";
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