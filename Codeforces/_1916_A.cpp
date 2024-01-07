#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;
    long long prod = 1;
    for (int i = 0; i < n; i++) {
        int bi;
        cin >> bi;
        prod *= bi;
    }
    if (prod != 0 && 2023 % prod == 0) {
        cout << "YES\n";
        long long quo = 2023 / prod;
        for (int i = 0; i < k - 1; i++) {
            cout << "1 ";
        }
        cout << quo << "\n";
    } else {
        cout << "NO\n";
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