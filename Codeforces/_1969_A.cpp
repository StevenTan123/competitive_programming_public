#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int p[n];
    for (int i = 0; i < n; i++) {
        cin >> p[i];
        p[i]--;
    }

    for (int i = 0; i < n; i++) {
        if (p[p[i]] == i) {
            cout << 2 << "\n";
            return;
        }
    }
    cout << 3 << "\n";
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