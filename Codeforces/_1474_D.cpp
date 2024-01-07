#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    
    int a[n];
    int prefix[n];
    int suffix[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        prefix[i] = -1;
        suffix[i] = -1;
    }

    int val = a[0];
    prefix[0] = a[0];
    for (int i = 1; i < n; i++) {
        if (val > a[i]) {
            break;
        } else {
            val = a[i] - val;
            prefix[i] = val;
        }
    }
    
    val = a[n - 1];
    suffix[n - 1] = a[n - 1];
    for (int i = n - 2; i >= 0; i--) {
        if (val > a[i]) {
            break;
        } else {
            val = a[i] - val;
            suffix[i] = val;
        }
    }

    bool possible = false;
    if (prefix[n - 1] == 0) {
        possible = true;
    } else if (n > 2 && prefix[n - 3] != -1 && prefix[n - 3] + a[n - 2] == a[n - 1]) {
        possible = true;
    } else if (n > 2 && suffix[2] ! -1 && suffix[2] + a[1] == a[0]) {
        possible = true;
    } else {
        for (int i = 1; i < n - 2; i++) {
            int l = a[i + 1];
            int r = a[i];
            if (prefix[i - 1] != -1 && suffix[i + 2] != -1 && l >= prefix[i - 1] && r >= suffix[i + 2] && l - prefix[i - 1] == r - suffix[i + 2]) {
                possible = true;
                break;
            }
        }
    }
    if (possible) {
        cout << "YES" << "\n";
    } else {
        cout << "NO" << "\n";
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