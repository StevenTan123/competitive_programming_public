#include <bits/stdc++.h>
using namespace std;

const int MAXD = 2005;
bitset<MAXD> dp[MAXD / 2];

void solve() {
    int n, d;
    cin >> n >> d;
    int l[n];
    for (int i = 0; i < n; i++) {
        cin >> l[i];
    }
    sort(l, l + n);

    if (l[n - 1] + l[n - 2] > d) {
        cout << "NO\n";
    } else {
        for (int i = 0; i < d / 2 + 1; i++) {
            for (int j = 0; j < d; j++) {
                dp[i].set(j, 0);
            }
        }
        dp[0].set(0);

        bool contain_max = false;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                for (int j = 0; j < d / 2 + 1; j++) {
                    int other = d - j - l[n - 1];
                    if (other >= 0 && dp[j].test(other)) {
                        contain_max = true;
                        break;
                    }
                }
            }
            for (int j = d / 2; j >= 0; j--) {
                bitset<MAXD> temp = dp[j] << l[i];
                if (j >= l[i]) {
                    dp[j] |= dp[j - l[i]];
                }
                dp[j] |= temp;
            }
        }
        if (contain_max) {
            cout << "YES\n";
        } else {
            bool possible = false;
            for (int i = l[n - 1]; i < d / 2 + 1; i++) {
                if (dp[i].test(d - i)) {
                    possible = true;
                    break;
                }
            }
            cout << (possible ? "YES\n" : "NO\n");
        }
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
