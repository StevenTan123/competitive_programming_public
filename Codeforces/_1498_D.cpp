#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, m;
    cin >> n >> m;
    int t[n];
    long long x[n];
    int y[n];
    for (int i = 0; i < n; i++) {
        cin >> t[i] >> x[i] >> y[i];
        if (t[i] == 1) {
            if (x[i] % 100000 == 0) {
                x[i] /= 100000;
            } else {
                x[i] = x[i] / 100000 + 1;
            }
        }
    }

    // dp[i][j] = possible to reach value j on i-th timestep.
    bool dp[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= m; j++) {
            dp[i][j] = 0;
        }
    }

    int res[m + 1];
    for (int i = 0; i <= m; i++) {
        res[i] = -1;
    }

    for (int i = 0; i <= n; i++) {
        dp[i][0] = 1;
        for (int j = m; j >= 0; j--) {
            if (dp[i][j]) {
                if (res[j] == -1) {
                    res[j] = i;
                }
                
                if (i == n) {
                    continue;
                }
                if (t[i] == 1) {
                    for (int k = 0; k <= y[i]; k++) {
                        int cur = j + k * x[i];
                        if (cur > m || dp[i + 1][cur]) {
                            break;
                        }
                        dp[i + 1][cur] = 1;
                    }
                } else {
                    long long prod = j;
                    for (int k = 0; k <= y[i]; k++) {
                        if (prod > m || dp[i + 1][prod]) {
                            break;
                        }
                        dp[i + 1][prod] = true;
                        
                        prod *= x[i];
                        if (prod % 100000 == 0) {
                            prod /= 100000;
                        } else {
                            prod = prod / 100000 + 1;
                        }
                    }
                }
            }
        }
    }

    for (int i = 1; i <= m; i++) {
        cout << res[i] << " ";
    }
    cout << endl;
}