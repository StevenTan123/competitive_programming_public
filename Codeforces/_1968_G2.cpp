#include <bits/stdc++.h>
using namespace std;

const int THRES = 100;

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

    stringstream ss;
    ss << s << '$' << s;
    string query = ss.str();    
    vector<int> z(query.length());
    z_function(query, z);

    vector<int> res(n + 1);
    for (int i = 1; i <= min(THRES, n); i++) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int m = (left + right) / 2;

            int cnt = 0;
            for (int j = 0; j < n - m + 1; j++) {
                if (z[j + n + 1] >= m) {
                    cnt++;
                    j += m - 1;
                }
            }

            if (cnt >= i) {
                res[i] = m;
                left = m + 1;
            } else {
                right = m - 1;
            }
        }
    }

    int max_lcp = min(n, n / THRES + 1);
    for (int i = 1; i <= max_lcp; i++) {
        int cnt = 0;
        for (int j = 0; j < n - i + 1; j++) {
            if (z[j + n + 1] >= i) {
                cnt++;
                j += i - 1;
            }
        }
        res[cnt] = max(res[cnt], i);
    }
    for (int i = n - 1; i >= 0; i--) {
        res[i] = max(res[i], res[i + 1]);
    }

    for (int i = l; i <= r; i++) {
        cout << res[i] << " ";
    }
    cout << "\n";
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