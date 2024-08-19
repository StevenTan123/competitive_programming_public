#include <bits/stdc++.h>
using namespace std;

map<char, int> dir_map = { {'N', 0}, {'E', 1}, {'S', 2}, {'W', 3} };
int dirs[4][2] = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

const int MAXV = 55;
bool blocked[MAXV][MAXV][4];
int dists[MAXV][MAXV][MAXV][MAXV];
int bumps[MAXV][MAXV][MAXV][MAXV];

int c, r, e;

pair<int, int> calc_next(int x, int y, int dir) {
    if (x == 0) {
        return {0, 0};
    }
    if (x == e && y == 1 && dir == 2) {
        return {0, 0};
    }
    if (blocked[x][y][dir]) {
        return {x, y};
    }
    return {x + dirs[dir][0], y + dirs[dir][1]};
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> c >> r >> e;
    int c1, r1, c2, r2;
    char d1, d2;
    cin >> c1 >> r1 >> d1 >> c2 >> r2 >> d2;
    int gap = (dir_map[d2] - dir_map[d1] + 4) % 4;

    // blocked[x][y][d] = 1 if d direction from cell (x, y) is blocked, 0 otherwise.
    for (int i = 1; i <= c; i++)  {
        for (int j = 1; j <= r; j++) {
            for (int k = 0; k < 4; k++) {
                blocked[i][j][k] = 0;        
            }
            if (i == 1) blocked[i][j][3] = 1;
            if (i == c) blocked[i][j][1] = 1;
            if (j == 1) blocked[i][j][2] = 1;
            if (j == r) blocked[i][j][0] = 1;
        }
    }
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        int ci, ri;
        cin >> ci >> ri;
        blocked[ci][ri][0] = 1;
        blocked[ci][ri + 1][2] = 1;
    }
    int m;
    cin >> m;
    for (int i = 0; i < m; i++) {
        int ci, ri;
        cin >> ci >> ri;
        blocked[ci][ri][1] = 1;
        blocked[ci + 1][ri][3] = 1;
    }

    for (int i = 0; i <= c; i++) {
        for (int j = 0; j <= r; j++) {
            for (int k = 0; k <= c; k++) {
                for (int l = 0; l <= r; l++) {
                    dists[i][j][k][l] = INT_MAX;
                    bumps[i][j][k][l] = INT_MAX;
                }
            }
        }
    }

    // {x1, y1, x2, y2}
    queue<tuple<int, int, int, int>> BFS;
    BFS.push({c1, r1, c2, r2});
    dists[c1][r1][c2][r2] = 0;
    bumps[c1][r1][c2][r2] = 0;
    while (!BFS.empty()) {
        auto [x1, y1, x2, y2] = BFS.front();
        int dist = dists[x1][y1][x2][y2];
        int bump = bumps[x1][y1][x2][y2];
        BFS.pop();
        if (x1 == 0 && x2 == 0) {
            cout << dist << " " << bump << endl;
            break;
        }
        if (x1 == x2 && y1 == y2) {
            continue;
        }

        for (int dir1 = 0; dir1 < 4; dir1++) {
            int dir2 = (dir1 + gap) % 4;
            auto [xx1, yy1] = calc_next(x1, y1, dir1);
            auto [xx2, yy2] = calc_next(x2, y2, dir2);

            int bump_cnt = bump;
            if (xx1 != 0 && xx1 == x1 && yy1 == y1) {
                bump_cnt++;
            }
            if (xx2 != 0 && xx2 == x2 && yy2 == y2) {
                bump_cnt++;
            }

            if (dist + 1 < dists[xx1][yy1][xx2][yy2]) {
                dists[xx1][yy1][xx2][yy2] = dist + 1;
                bumps[xx1][yy1][xx2][yy2] = bump_cnt;
                BFS.push({xx1, yy1, xx2, yy2});
            } else if (dist + 1 == dists[xx1][yy1][xx2][yy2] && bump_cnt < bumps[xx1][yy1][xx2][yy2]) {
                bumps[xx1][yy1][xx2][yy2] = bump_cnt;
            }
        }
    }
}