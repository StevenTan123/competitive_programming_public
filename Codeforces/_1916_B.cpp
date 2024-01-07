#include <bits/stdc++.h>
using namespace std;

void solve() {
    int a, b;
    cin >> a >> b;
    if (b % a == 0) {
        if (a == 1) {
            cout << ((long long) b * b) << "\n";
        } else {
            cout << ((long long) b / a * b) << "\n";
        }
    } else {
        long long lcm = (long long) a * b / __gcd(a, b);
        cout << lcm << "\n";
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