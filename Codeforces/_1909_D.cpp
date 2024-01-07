#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    long long k;
    cin >> n >> k;
    long long a[n];
    int above = 0;
    int below = 0;
    int equal = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        if (a[i] > k) {
            above++;
        } else if (a[i] == k) {
            equal++;
        } else {
            below++;
        }
    }

    if (equal > 0 && above == 0 && below == 0) {
        cout << "0\n";
    } else if ((above > 0 && equal == 0 && below == 0) || (below > 0 && equal == 0 && above == 0)) {
        long long gcd_all = 0;
        for (int i = 0; i < n; i++) {
            gcd_all = __gcd(gcd_all, abs(a[i] - k));
        }

        long long res = 0;
        for (int i = 0; i < n; i++) {
            long long add = abs(a[i] - k) / gcd_all - 1;
            res += add;
        }
        cout << res << "\n";
    } else {
        cout << "-1\n";
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