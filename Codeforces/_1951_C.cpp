#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m, k;
    cin >> n >> m >> k;

    int a[n];
    pair<int, int> sorted[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        sorted[i] = {a[i], i};
    }
    sort(sorted, sorted + n);

    vector<int> buy(n);
    for (int i = 0; i < n; i++) {
        if (k == 0) {
            break;
        }
        buy[sorted[i].second] = min(m, k);
        k -= min(m, k);
    }

    long long res = 0;
    long long prev = 0;
    for (int i = 0; i < n; i++) {
        res += (long long) buy[i] * (a[i] + prev);
        prev += buy[i];
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