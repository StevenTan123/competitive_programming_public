#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;

    int res[n];
    for (int i = 0; i < n; i += 2) {
        res[i] = i / 2 + 1;
    }
    for (int i = 1; i < n; i += 2) {
        res[i] = n - (i - 1) / 2;
    }
    for (int i = 0; i < n; i++) {
        cout << res[i] << " ";
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