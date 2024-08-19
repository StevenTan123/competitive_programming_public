#include <bits/stdc++.h>
using namespace std;

void solve() {
    int x;
    cin >> x;
    
    vector<int> digits;
    while (x > 0) {
        digits.push_back(x % 10);
        x /= 10;
    }

    bool possible = true;
    int carry = 0;
    for (int i = 0; i < digits.size(); i++) {
        if (i + 1 == digits.size()) {
            if (digits[i] != 1) {
                possible = false;
            }
            break;
        }
        if (digits[i] + 10 - carry - 9 > 9) {
            possible = false;
            break;
        } 
        if (digits[i] + 10 - carry - 5 < 5) {
            possible = false;
            break;
        }
        carry = 1;  
    } 
    cout << (possible ? "YES\n" : "NO\n");
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