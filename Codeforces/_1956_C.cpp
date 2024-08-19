#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;

    long long sum = 0;
    long long len = 1;
    for (int i = 1; i <= n; i++) {
        sum += len * i;
        len += 2;
    }

    cout << sum << " " << 2 * n << "\n";
    for (int i = n; i >= 1; i--) {
        cout << 1 << " " << i;
        for (int j = 1; j <= n; j++) {
            cout << " " << j;
        }
        cout << "\n";
        cout << 2 << " " << i;
        for (int j = 1; j <= n; j++) {
            cout << " " << j;
        }
        cout << "\n";
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