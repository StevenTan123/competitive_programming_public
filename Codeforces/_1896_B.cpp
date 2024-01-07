#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    bool seenA = false;
    int firstA = -1;
    int lastB = -1;
    for (int i = 0; i < n; i++) {
        if (!seenA && s[i] == 'A') {
            seenA = true;
            firstA = i;
        }
        if (s[i] == 'B') {
            lastB = i;
        }
    }
    if (seenA && lastB != -1) {
        if (firstA < lastB) {
            cout << lastB - firstA << endl;
        } else {
            cout << 0 << endl;
        }
    } else {
        cout << 0 << endl;
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