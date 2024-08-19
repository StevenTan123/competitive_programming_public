#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    vector<int> cnts(4);
    int wrong = 0;
    for (int i = 0; i < 4 * n; i++) {
        if (s[i] != '?') {
            cnts[s[i] - 'A']++;
        } else {
            wrong++;
        }
    }
    for (int i = 0; i < 4; i++) {
        if (cnts[i] > n) {
            wrong += cnts[i] - n;
        }
    }
    cout << 4 * n - wrong << "\n";
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