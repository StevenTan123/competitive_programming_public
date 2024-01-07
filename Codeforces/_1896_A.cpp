#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    for (int i = 0; i < 2 * n; i++) {
        for (int j = 1; j < n - 1; j++) {
            if (a[j] > a[j - 1] && a[j] > a[j + 1]) {
                swap(a[j], a[j + 1]);
            }
        }
    }

    bool sorted = true;
    for (int i = 1; i < n; i++) {
        if (a[i] != a[i - 1] + 1) {
            sorted = false;
            break;
        }
    }
    cout << (sorted ? "YES" : "NO") << endl;
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