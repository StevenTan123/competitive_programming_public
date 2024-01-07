#include <bits/stdc++.h>
using namespace std;

int dirs[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

void solve() {
    int n, m, sr, sc;
    cin >> n >> m;
    bool grid[n][m];
    int num = 0;

    // dist_to_exit[i][j][0/1] stores the 1st/2nd closest exit from (i, j),
    // each exit represented by pair {distance, ID of exit}
    pair<int, int> dist_to_exit[n][m][2];
    // dists[i][j] stores distance from (sr, sc) to (i, j)
    int dists[n][m];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            char ch;
            cin >> ch;
            if (ch == '#') {
                grid[i][j] = 1;
                num++;
            } else {
                grid[i][j] = 0;
            }
            if (ch == 'V') {
                sr = i;
                sc = j;
            }
            dist_to_exit[i][j][0] = {-1, -1};
            dist_to_exit[i][j][1] = {-1, -1};
            dists[i][j] = -1;
        }
    }

    // {distance, row, col}
    deque<tuple<int, int, int>> BFS2;
    BFS2.push_back({0, sr, sc});
    while (BFS2.size() > 0) {
        tuple<int, int, int> cur = BFS2.front();
        int d = get<0>(cur);
        int r = get<1>(cur);
        int c = get<2>(cur);
        BFS2.pop_front();

        if (dists[r][c] != -1) {
            continue;
        }
        dists[r][c] = d;

        for (int i = 0; i < 4; i++) {
            int newr = r + dirs[i][0];
            int newc = c + dirs[i][1];
            if (newr >= 0 && newr < n && newc >= 0 && newc < m && !grid[newr][newc]) {
                BFS2.push_back({d + 1, newr, newc});
            }
        }
    }

    // {distance, ID of exit, row, col}
    deque<tuple<int, int, int, int>> BFS;
    int exits = 0;
    for (int i = 0; i < m; i++) {
        if (!grid[0][i] && dists[0][i] != -1) {
            BFS.push_back({0, i, 0, i});
            exits++;
        }
        if (!grid[n - 1][i] && dists[n - 1][i] != -1) {
            BFS.push_back({0, (n - 1) * m + i, n - 1, i});
            exits++;
        }
    }
    for (int i = 1; i < n - 1; i++) {
        if (!grid[i][0] && dists[i][0] != -1) {
            BFS.push_back({0, i * m, i, 0});
            exits++;
        }
        if (!grid[i][m - 1] && dists[i][m - 1] != -1) {
            BFS.push_back({0, i * m + m - 1, i, m - 1});
            exits++;
        }
    }
    while (BFS.size() > 0) {
        tuple<int, int, int, int> cur = BFS.front();
        int d = get<0>(cur);
        int ID = get<1>(cur); 
        int r = get<2>(cur);
        int c = get<3>(cur);
        BFS.pop_front();

        if (dist_to_exit[r][c][0].first == -1) {
            dist_to_exit[r][c][0] = {d, ID};
        } else if (dist_to_exit[r][c][1].first == -1) {
            if (dist_to_exit[r][c][0].second == ID) {
                continue;
            }
            dist_to_exit[r][c][1] = {d, ID};
        } else {
            continue;
        }

        for (int i = 0; i < 4; i++) {
            int newr = r + dirs[i][0];
            int newc = c + dirs[i][1];
            if (newr >= 0 && newr < n && newc >= 0 && newc < m && !grid[newr][newc]) {
                BFS.push_back({d + 1, ID, newr, newc});
            }
        }
    }

    if (exits == 0) {
        cout << n * m - num - 1 << endl;
    } else if (exits == 1) {
        cout << n * m - num - dist_to_exit[sr][sc][0].first - 1 << endl;
    } else {
        int max_block = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!grid[i][j] && dists[i][j] != -1) {
                    int cur = dists[i][j] + dist_to_exit[i][j][0].first + dist_to_exit[i][j][1].first + 1;
                    max_block = max(max_block, n * m - num - cur);
                }
            }
        }
        cout << max_block << endl;
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