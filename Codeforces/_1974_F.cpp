#include <bits/stdc++.h>
using namespace std;

void solve() {
    int a, b, n, m;
    cin >> a >> b >> n >> m;

    set<pair<int, int>> by_r;
    set<pair<int, int>> by_c;
    for (int i = 0; i < n; i++) {
        int r, c;
        cin >> r >> c;
        by_r.emplace(r, c);
        by_c.emplace(c, r);
    }

    int r_min = 1; int r_max = a;
    int c_min = 1; int c_max = b;
    vector<int> cnts(2);
    for (int i = 0; i < m; i++) {
        char ch;
        int k;
        cin >> ch >> k;
        if (ch == 'U') {
            r_min += k;
            while (!by_r.empty() && by_r.begin()->first < r_min) {
                auto er = by_r.begin();
                by_c.erase({er->second, er->first});
                by_r.erase(er);
                cnts[i % 2]++;
            }
        } else if (ch == 'D') {
            r_max -= k;
            while (!by_r.empty() && by_r.rbegin()->first > r_max) {
                auto er = prev(by_r.end());
                by_c.erase({er->second, er->first});
                by_r.erase(er);
                cnts[i % 2]++;
            }
        } else if (ch == 'L') {
            c_min += k;
            while (!by_c.empty() && by_c.begin()->first < c_min) {
                auto er = by_c.begin();
                by_r.erase({er->second, er->first});
                by_c.erase(er);
                cnts[i % 2]++;
            }
        } else {
            c_max -= k;
            while (!by_c.empty() && by_c.rbegin()->first > c_max) {
                auto er = prev(by_c.end());
                by_r.erase({er->second, er->first});
                by_c.erase(er);
                cnts[i % 2]++;
            }
        }
    }
    cout << cnts[0] << " " << cnts[1] << "\n";
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