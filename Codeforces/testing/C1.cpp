#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    long long minv = 0;
    long long maxv = 0;
    for (int i = 0; i < n; i++) {
        minv = minv + a[i];
        maxv = max(abs(maxv + a[i]), abs(minv));
    }
    cout << maxv << "\n";
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