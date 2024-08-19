#include <bits/stdc++.h>
using namespace std;

int f(string &s, vector<bool> &ones, int l, int r) {
    for (int i = l; i <= r; i++) {
        ones[i] = false;
    }
    int cnt = 0;
    for (int i = l; i <= r; i++) {
        if (s[i] == '0') continue;
        if ((i > l && ones[i - 1]) || ones[i] || (i < r && ones[i + 1])) {
            continue;
        } else {
            if (i < r) {
                ones[i + 1] = 1;
            } else {
                ones[i] = 1;
            }
            cnt++;
        }
    }
    return cnt;
}

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;

    long long res = 0;
    vector<bool> ones(n);
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            res += f(s, ones, i, j);
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