#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[2 * n];
    for (int i = 0; i < 2 * n; i++) {
        cin >> a[i];
    }
    sort(a, a + 2 * n);

    long long res = 0;
    for (int i = 0; i < 2 * n; i += 2) {
        res += a[i];
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