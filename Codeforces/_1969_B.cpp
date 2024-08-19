#include <bits/stdc++.h>
using namespace std;

void solve() {
    string s;
    cin >> s;
    
    int cnt = 0;
    long long res = 0;
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '0') {
            if (i > cnt) {
                res += i - cnt + 1;
            }
            cnt++;
        }
    }
    cout << res << "\n";
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