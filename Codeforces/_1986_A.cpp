#include <bits/stdc++.h>
using namespace std;

void solve() {
    int x1, x2, x3;
    cin >> x1 >> x2 >> x3;
    int res = INT_MAX;
    for (int i = 1; i <= 10; i++) {
        res = min(res, abs(x1 - i) + abs(x2 - i) + abs(x3 - i));
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