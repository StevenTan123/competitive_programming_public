#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    vector<int> b(n);
    for (int i = 0; i < n; i++) {
        cin >> b[i];
    }
    bool same = true;
    bool reverse = true;
    for (int i = 0; i < n; i++) {
        if (a[i] != b[i]) {
            same = false;
        }
        if (a[i] != b[n - i - 1]) {
            reverse = false;
        }
    }
    if (same || reverse) {
        cout << "Bob\n";
    } else {
        cout << "Alice\n";
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