#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int zeros = 0;
    int sign = 1;
    for (int i = 0; i < n; i++) {
        int val;
        cin >> val;
        if (val == 0) {
            zeros++;
        } else if (val < 0) {
            sign *= -1;
        }
    }
    if (zeros > 0 || sign == -1) {
        cout << "0\n";
    } else {
        cout << "1\n";
        cout << "1 0\n";
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