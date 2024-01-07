#include <bits/stdc++.h>
using namespace std;

void solve() {
    int m, d, w;
    cin >> m >> d >> w;

    int mod = w / __gcd(w, d - 1);
    int mx = min(m, d) - 1;
    // Problem reduces to finding all pairs (x, y) with 0 <= x < y <= mx satisfying mod | (y - x)
    int residue1 = mx % mod + 1;
    int count1 = mx / mod + 1;
    int residue2 = mod - residue1;
    int count2 = mx / mod;
    long long res = (long long) residue1 * count1 * (count1 - 1) / 2 + (long long) residue2 * count2 * (count2 - 1) / 2;
    cout << res << endl;
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