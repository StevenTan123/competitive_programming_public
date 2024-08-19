#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;
    string s;
    cin >> s;
    set<int> inds;
    for (int i = 0; i < m; i++) {
        int indi;
        cin >> indi;
        inds.insert(indi);
    }
    string c;
    cin >> c;
    vector<char> c_chars;
    for (int i = 0; i < m; i++) {
        c_chars.push_back(c[i]);
    } 
    sort(c_chars.begin(), c_chars.end());
    int i = 0;
    for (int ind : inds) {
        s[ind - 1] = c_chars[i++];
    }
    cout << s << "\n";
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