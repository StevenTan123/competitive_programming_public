#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    sort(a, a + n);
    int med = n / 2 - 1;
    if (n % 2 != 0) {
        med++;
    }
    int cnt = 1;
    for (int i = med + 1; i < n; i++) {
        if (a[i] == a[i - 1]) {
            cnt++;
        } else {
            break;
        }
    }
    cout << cnt << "\n";
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