#include <bits/stdc++.h>
using namespace std;

const int MAXN = 20000005;
vector<int> spf(MAXN);
vector<int> primes;

void solve() {
    int n, m, l, f;
    cin >> n >> m >> l >> f;
    if (l < f) {
        swap(l, f);
    }

    int start1 = 0;
    int start2 = 0;
    if (n > 100) {
        auto it = lower_bound(primes.begin(), primes.end(), n);
        start1 = *prev(it);
        start2 = *prev(prev(it));
    }
    int diff1 = n - start1 + 1;
    int diff2 = n - start2 + 1;
    vector<vector<bool>> dp(diff1, vector<bool>(diff2));
    dp[0][0] = 1;
    long long res = 0;
    for (int i = 0; i < diff1; i++) {
        for (int j = 0; j < diff2 && j + start2 <= i + start1; j++) {
            if (__gcd(i + start1, j + start2) <= 1) {
                if (i > 0) {
                    dp[i][j] = dp[i][j] | dp[i - 1][j];
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] | dp[i][j - 1];
                }
            }
            if (dp[i][j]) {
                res = max(res, (long long) l * (i + start1) + (long long) f * (j + start2));
            }
        }
    }
    cout << res << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 2; i < MAXN; i++) {
        for (int j = i; j < MAXN; j += i) {
            if (spf[j] == 0) {
                spf[j] = i;
            }
        }
        if (spf[i] == i) {
            primes.push_back(i);
        }
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}