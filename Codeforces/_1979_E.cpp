#include <bits/stdc++.h>
using namespace std;

int find_in_diag(map<int, set<int>> &diag, int diag_sum, int x_min, int x_max) {
    if (diag.count(diag_sum)) {
        set<int> &cur = diag[diag_sum];
        auto it = cur.lower_bound(x_min);
        if (it != cur.end() && *it <= x_max) {
            return *it;
        }
    }
    return INT_MAX;
}

void solve() {
    int n, d;
    cin >> n >> d;
    vector<tuple<int, int>> pts;
    map<int, map<int, int>> ID;
    map<int, set<int>> horz;
    map<int, set<int>> diag1;
    map<int, set<int>> diag2;
    for (int i = 0; i < n; i++) {
        int x, y;
        cin >> x >> y;
        pts.emplace_back(x, y);
        ID[x][y] = i;
        horz[y].emplace(x);
        diag1[x - y].emplace(x);
        diag2[x + y].emplace(x);
    }
    tuple<int, int, int> res = {-1, -1, -1};
    for (int i = 0; i < n; i++) {
        auto [x, y] = pts[i];
        if (horz[y + d / 2].count(x + d / 2)) {
            int i2 = ID[x + d / 2][y + d / 2];
            
            int x3 = find_in_diag(diag1, x - y - d, x - d / 2, x);
            if (x3 != INT_MAX) {
                int i3 = ID[x3][x3 - (x - y - d)];
                res = {i, i2, i3};
                break;
            }

            x3 = find_in_diag(diag1, x - y + d, x + d / 2, x + d);
            if (x3 != INT_MAX) {
                int i3 = ID[x3][x3 - (x - y + d)];
                res = {i, i2, i3};
                break;
            }
        }
        if (horz[y - d / 2].count(x + d / 2)) {
            int i2 = ID[x + d / 2][y - d / 2];

            int x3 = find_in_diag(diag2, x + y + d, x + d / 2, x + d);
            if (x3 != INT_MAX) {
                int i3 = ID[x3][(x + y + d) - x3];
                res = {i, i2, i3};
                break;
            }

            x3 = find_in_diag(diag2, x + y - d, x - d / 2, x);
            if (x3 != INT_MAX) {
                int i3 = ID[x3][(x + y - d) - x3];
                res = {i, i2, i3};
                break;
            }
        }
    }
    auto [i1, i2, i3] = res;
    cout << i1 + 1 << " " << i2 + 1 << " " << i3 + 1 << "\n";
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