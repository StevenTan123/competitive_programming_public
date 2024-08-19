#include <bits/stdc++.h>
using namespace std;

int min_exp(vector<int> a) {
    for (int i = 0; i < a.size(); i++) {
        if (a[i] == 0) {
            return 0;
        }
    }
    int res = 0;
    for (int i = 0; i < a.size(); i++) {
        if (a[i] != 1) {
            res += a[i];
        }
    }
    if (res == 0) {
        res = 1;
    }
    return res;
}

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    int res = INT_MAX;
    for (int i = 1; i < n; i++) {
        vector<int> a;
        for (int j = 0; j < i - 1; j++) {
            a.push_back(s[j] - '0');
        }
        int p_dig = s[i - 1] - '0';
        int c_dig = s[i] - '0';
        a.push_back(p_dig * 10 + c_dig);
        for (int j = i + 1; j < s.length(); j++) {
            a.push_back(s[j] - '0');
        }
        res = min(res, min_exp(a));
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