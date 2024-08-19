#include <bits/stdc++.h>
using namespace std;

const int THRES = 10000000;

void solve() {
    long long n, k;
    cin >> n >> k;

    long long ceil = n / 2 + (n % 2 == 0 ? 0 : 1);
    if (k > n) {
        cout << "NO\n";
    } else if (k > ceil && k < n) {
        if (n > THRES) {
            cout << "NO\n";
        } else {
            bool found = false;
            for (int i = 1; i < n; i++) {
                if (n / i + (n % i) == k) {
                    cout << "YES\n";
                    cout << "2\n";
                    cout << i << " 1\n";
                    found = true;
                    break;
                }
            }
            if (!found) {
                cout << "NO\n";
            }
        }
    } else if (n == k) {
        cout << "YES\n";
        cout << "1\n1\n";
    } else {
        cout << "YES\n";
        cout << "2\n";
        cout << n - k + 1 << " 1\n";
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