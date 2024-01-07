#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    long long a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    long long MOD = 2;
    while (true) {
        int zeros = 0;
        int ones = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                zeros++;
            } else {
                ones++;
            }
        }
        if (zeros > 0 && ones > 0) {
            break;
        }
        MOD *= 2;
        for (int i = 0; i < n; i++) {
            a[i] /= 2;
        }
    }
    cout << MOD << "\n";
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