#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    int b[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    int one_cnt = 0;
    int two_cnt = 0;
    bool all_even = true;
    bool odd = true;
    int xor_0 = 0;
    for (int i = 0; i < n; i++) {
        cin >> b[i];
        if (b[i] == 1 && a[i] == 1) {
            b[i] = 0;
        }

        if (b[i] == 2) {
            two_cnt++;
            if (a[i] % 2 != 0) {
                all_even = false;
            }
        } else if (b[i] == 1) {
            if (a[i] % 2 == 0) {
                odd = false;
            }
            one_cnt++;
        } else {
            xor_0 ^= a[i];
        }
    }

    if (two_cnt >= 2 || (two_cnt == 1 && !all_even)) {
        cout << "Bob\n";
    } else if (two_cnt == 1) {
        if (one_cnt >= 1) {
            cout << "Bob\n";
        } else {
            cout << (xor_0 != 0 ? "Bob" : "Alice") << "\n";    
        }
    } else if (one_cnt == 0) {
        cout << (xor_0 == 0 ? "Bob" : "Alice") << "\n";
    } else if (one_cnt >= 2) {
        cout << "Bob\n";
    } else {
        if (odd) {
            cout << (xor_0 != 0 ? "Bob" : "Alice") << "\n";    
        } else {
            cout << ((xor_0 ^ 1) != 0 ? "Bob" : "Alice") << "\n";
        }
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