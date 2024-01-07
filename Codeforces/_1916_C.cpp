#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    long long sum = 0;
    int even = 0;
    int odd = 0;
    for (int i = 0; i < n; i++) {
        if (a[i] % 2 == 0) {
            even++;
        } else {
            odd++;
        }
        sum += a[i];

        if (i == 0) {
            cout << a[i] << " ";
        } else {
            int wasted = odd / 3;
            if (odd % 3 == 1) {
                wasted++;
            }
            cout << (sum - wasted) << " ";
        }
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