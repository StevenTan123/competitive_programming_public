#include <bits/stdc++.h>
using namespace std;

void solve() {
    string s;
    cin >> s;
    int z = 0;
    int o = 0;
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '0') {
            z++;
        } else {
            o++;
        }
    }
    int z2 = 0;
    int o2 = 0;
    int res = s.length();
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '0') {
            o2++;
        } else {
            z2++;
        }
        if (z2 > z || o2 > o) {
            res = i;
            break;
        }
    }
    cout << s.length() - res << "\n";
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