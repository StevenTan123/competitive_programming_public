#include <bits/stdc++.h>
using namespace std;

int dirs[8][2] = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1} };

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, M;
    cin >> N >> M;
    vector<vector<bool>> grid(N + 2, vector<bool>(M + 2, false));
    for (int i = 1; i <= N; i++) {
        string line;
        cin >> line;
        for (int j = 1; j <= M; j++) {
            if (line[j - 1] == 'X') {
                grid[i][j] = true;
            }
        }
    }

    deque<pair<int, int>> BFS;
    vector<vector<int>> dists(N + 2, vector<int>(M + 2, INT_MAX));
    for (int i = 0; i <= N + 1; i++) {
        for (int j = 0; j <= M + 1; j++) {
            if (!grid[i][j]) {
                dists[i][j] = 0;
                BFS.emplace_back(i, j);
            }
        }
    }

    int max_dist = 0;
    while (!BFS.empty()) {
        auto [r, c] = BFS.front();
        BFS.pop_front();

        for (int i = 0; i < 8; i++) {
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];
            if (nr >= 0 && nr <= N + 1 && nc >= 0 && nc <= M + 1) {
                if (dists[r][c] + 1 < dists[nr][nc]) {
                    dists[nr][nc] = dists[r][c] + 1;
                    max_dist = max(max_dist, dists[nr][nc] - 1);
                    BFS.emplace_back(nr, nc);
                }
            }
        }
    }

    vector<vector<int>> vis(N + 2, vector<int>(M + 2, INT_MAX));
    int left = 0;
    int right = max_dist;
    int res = -1;
    while (left <= right) {
        int m = (left + right) / 2;
        BFS.clear();
        for (int i = 0; i <= N + 1; i++) {
            fill(vis[i].begin(), vis[i].end(), INT_MAX);
            for (int j = 0; j <= M + 1; j++) {
                if (dists[i][j] > m) {
                    vis[i][j] = 0;
                    BFS.emplace_back(i, j);
                }
            }
        }

        while (!BFS.empty()) {
            auto [r, c] = BFS.front();
            BFS.pop_front();

            if (vis[r][c] == m) {
                continue;
            }
            for (int i = 0; i < 8; i++) {
                int nr = r + dirs[i][0];
                int nc = c + dirs[i][1];
                if (nr >= 0 && nr <= N + 1 && nc >= 0 && nc <= M + 1) {
                    if (vis[r][c] + 1 < vis[nr][nc]) {
                        vis[nr][nc] = vis[r][c] + 1;
                        BFS.emplace_back(nr, nc);
                    }
                }
            }
        }

        bool works = true;
        for (int i = 0; i <= N + 1; i++) {
            for (int j = 0; j <= M + 1; j++) {
                if (grid[i][j] && vis[i][j] == INT_MAX) {
                    works = false;
                    break;
                }
            }
            if (!works) {
                break;
            }
        }

        if (works) {
            res = m;
            left = m + 1;
        } else {
            right = m - 1;
        }
    }
    cout << res << "\n";
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            if (dists[i][j] > res) {
                cout << 'X';
            } else {
                cout << '.';
            }
        }
        cout << "\n";
    }
}
