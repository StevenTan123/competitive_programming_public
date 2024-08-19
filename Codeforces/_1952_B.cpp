#include <bits/stdc++.h>
using namespace std;

void solve() {
    string s;
    cin >> s;
    bool found = false;
    for (int i = 0; i < s.length() - 1; i++) {
        if (s[i] == 'i' && s[i + 1] == 't') {
            found = true;
        }
    }
    cout << (found ? "YES\n" : "NO\n");
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