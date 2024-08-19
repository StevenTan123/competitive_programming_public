#include <bits/stdc++.h>
using namespace std;

void solve() {
    string s;
    cin >> s;
    string calico = "calico";
    stringstream ss;
    bool found = false;
    for (int i = 1; i <= 6; i++) {
        if (s.length() >= i && s.substr(0, i) == calico.substr(6 - i, i)) {
            found = true;
            ss << "CALICO";
            ss << s.substr(i);
            break;
        }
    }
    if (found) {
        cout << ss.str() << "\n";
    } else {
        cout << s << "\n";
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