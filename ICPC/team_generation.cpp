#include <bits/stdc++.h>
using namespace std;

bool works(int a[], int b[], int i, int j, int k) {
    return a[k - 1] <= i && b[i - 1] >= k;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    int a[n];
    int b[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i] >> b[i];
    }

    // dp[i] = max teams formed from people ranked 1...i
    int dp[n + 1] = {0};
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i - 1];
        if (works(a, b, i - 2, i - 1, i)) {
            dp[i] = max(dp[i], dp[i - 3] + 1);
        }
    }
    cout << dp[n] << endl;
}