#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k, d;
    cin >> n >> k >> d;
    int a[n];
    int v[k];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < k; i++) {
        cin >> v[i];
    }

    int res = 0;
    for (int i = 0; i < min(2 * n + 5, d); i++) {
        int fixed = 0;
        for (int j = 0; j < n; j++) {
            if (a[j] == j + 1) {
                fixed++;
            }
        }
        int cur = fixed + (d - i - 1) / 2;
        res = max(res, cur);

        for (int j = 0; j < v[i % k]; j++) {
            a[j]++;
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