#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int minx = 105;
    int maxx = -105;
    int miny = 105;
    int maxy = -105;
    for (int i = 0; i < n; i++) {
        int x, y;
        cin >> x >> y;
        minx = min(minx, x);
        maxx = max(maxx, x);
        miny = min(miny, y);
        maxy = max(maxy, y);
    }
    if (minx >= 0 || maxx <= 0 || miny >= 0 || maxy <= 0) {
        cout << "YES\n";
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