#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    string a;
    cin >> a;
    int cnt = 0;
    bool adj = false;
    for (int i = 0; i < n; i++) {
        if (a[i] == '1') {
            cnt++;
        }
        if (i > 0 && a[i] == '1' && a[i - 1] == '1') {
            adj = true;
        }
    }
    if (cnt % 2 != 0 || (cnt == 2 && adj)) {
        cout << "NO\n";
    } else {
        cout << "YES\n";
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