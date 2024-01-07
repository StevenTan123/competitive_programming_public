#include <bits/stdc++.h>
using namespace std;

void solve() {
    int ab;
    cin >> ab;

    int cur = ab;
    int pow10 = 1;
    bool found = false;
    while (cur > 0) {
        int digit = cur % 10;
        cur /= 10;
        pow10 *= 10;
        if (cur <= 0) {
            break;
        }
        if (digit != 0) {
            int b = ab - cur * pow10;
            if (cur < b) {
                cout << cur << " " << b << "\n";
                found = true;
                break;
            } 
        }
    }
    if (!found) {
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