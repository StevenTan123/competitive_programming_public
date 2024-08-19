#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;

    long long cnt = 0;
    for (int b = 1; b <= m; b++) {
        for (int a = b; a <= n; a += b) {
            long long rhs = (long long) b * gcd(a, b);
            if ((a + b) % rhs == 0) {
                cnt++;
            }
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