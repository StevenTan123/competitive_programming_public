#include <bits/stdc++.h>
using namespace std;

void z_function(string s, vector<int> &z) {
    int n = s.size();
    int l = 0, r = 0;
    for (int i = 1; i < n; i++) {
        if (i < r) {
            z[i] = min(r - i, z[i - l]);
        }
        while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
            z[i]++;
        }
        if (i + z[i] > r) {
            l = i;
            r = i + z[i];
        }
    }
}

void solve() {
    int n, l, r;
    cin >> n >> l >> r;
    string s;
    cin >> s;

    int left = 1;
    int right = n;
    int res = 0;
    while (left <= right) {
        int m = (left + right) / 2;
        stringstream ss;
        ss << s.substr(0, m) << '$' << s;
        string query = ss.str();
        
        vector<int> z(query.length());
        z_function(query, z);
    
        int cnt = 0;
        int prev = -1e9;
        for (int i = 0; i < n - m + 1; i++) {
            if (z[i + m + 1] == m) {
                if (i - m >= prev) {
                    prev = i;
                    cnt++;
                }
            }
        }

        if (cnt >= l) {
            res = m;
            left = m + 1;
        } else {
            right = m - 1;
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