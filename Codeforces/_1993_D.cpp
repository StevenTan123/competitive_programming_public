#include <bits/stdc++.h>
using namespace std;

bool can_achieve_median(int n, int k, vector<int> &a, vector<int> &residues, int median) {
    int mod = residues[n];
    int residue = mod;
    if (residue == 0) {
        residue = k;
    }

    // dp[i][0/1] = max left numbers in 1...i that are >= median.
    // 0/1 indicates if we have used all free numbers already.
    vector<vector<int>> dp(n + 1, vector<int>(2, -1));
    dp[0][0] = 0;
    for (int i = 1; i <= n; i++) {
        if (residues[i] == mod) {
            if (dp[i - 1][0] >= 0) {
                dp[i][1] = dp[i - 1][0] + (a[i - 1] >= median);
            }
            if (i >= k) {
                dp[i][0] = max(dp[i][0], dp[i - k][0]);
                dp[i][1] = max(dp[i][1], dp[i - k][1]);
            }
        } else {
            if (dp[i - 1][0] >= 0) {
                dp[i][0] = dp[i - 1][0] + (a[i - 1] >= median);
            }
            if (i >= k) {
                dp[i][0] = max(dp[i][0], dp[i - k][0]);
            }
        }
    }
    int need = (residue + 2) / 2;
    return max(dp[n][0], dp[n][1]) >= need; 
}

void solve() {
    int n, k;
    cin >> n >> k;
    vector<int> a(n);
    vector<int> sorted(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        sorted[i] = a[i];
    }
    sort(sorted.begin(), sorted.end());

    vector<int> residues(n + 1);
    for (int i = 0; i <= n; i++) {
        if (i < k) {
            residues[i] = i;
        } else {
            residues[i] = residues[i - k];
        }
    }

    int left = 0;
    int right = n - 1;
    int res = -1;
    while (left <= right) {
        int mid = (left + right) / 2;
        if (can_achieve_median(n, k, a, residues, sorted[mid])) {
            res = sorted[mid];
            left = mid + 1;
        } else {
            right = mid - 1;
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