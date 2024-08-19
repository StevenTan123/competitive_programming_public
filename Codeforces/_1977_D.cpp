#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;
    bool grid[n][m];
    for (int i = 0; i < n; i++) {
        string line;
        cin >> line;
        for (int j = 0; j < m; j++) {
            if (line[j] == '0') {
                grid[i][j] = 0;
            } else {
                grid[i][j] = 1;
            }
        }
    }

    if (n <= m) {
        unordered_map<string, int> cnts;
        for (int j = 0; j < m; j++) {
            string flips(n, '0');
            for (int i = 0; i < n; i++) {
                if (grid[i][j]) {
                    flips[i] = '1';
                }
            }
            for (int i = 0; i < n; i++) {
                flips[i] = (flips[i] == '0' ? '1' : '0');
                cnts[flips]++;
                flips[i] = (flips[i] == '0' ? '1' : '0');
            }
        }
        string max_str;
        int max_cnt = 0;
        for (auto [flip_str, cnt] : cnts) {
            if (cnt > max_cnt) {
                max_str = flip_str;
                max_cnt = cnt;
            }
        }
        cout << max_cnt << "\n";
        cout << max_str << "\n";
    } else {
        pair<int, int> max_one;
        int max_cnt = 0;
        for (int j = 0; j < m; j++) {
            vector<int> cnts(m);
            for (int i = 0; i < n; i++) {
                for (int j2 = 0; j2 < m; j2++) {
                    bool cur = grid[i][j2];
                    if (grid[i][j]) {
                        cur = !cur;
                    }
                    cnts[j2] += cur;
                }
            }
            for (int i = 0; i < n; i++) {
                bool flipped = grid[i][j];
                int special_cnt = 0;
                for (int j2 = 0; j2 < m; j2++) {
                    int add;
                    if (flipped) {
                        add = grid[i][j2] ? 1 : -1;
                    } else {
                        add = grid[i][j2] ? -1 : 1;
                    }
                    if (add + cnts[j2] == 1) {
                        special_cnt++;
                    }
                }
                if (special_cnt > max_cnt) {
                    max_cnt = special_cnt;
                    max_one = {i, j};
                }
            }
        }
        cout << max_cnt << "\n";
        string max_str(n, '0');
        for (int i = 0; i < n; i++) {
            if (grid[i][max_one.second]) {
                max_str[i] = '1';
            }
        }
        max_str[max_one.first] = (max_str[max_one.first] == '0' ? '1' : '0');
        cout << max_str << "\n";
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