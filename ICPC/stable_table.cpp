#include <bits/stdc++.h>
using namespace std;
 
const int MAXV = 105;
const int MAXT = 10005;
int grid[MAXV][MAXV];

vector<vector<bool>> visited(MAXV, vector<bool>(MAXV, 0));
vector<bool> is_end(MAXT, 0);
 
int h, w;
int dirs[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

void dfs(set<int> graph1[], set<int> graph2[], int r, int c, int id) {
    if (r < 0 || r >= h || c < 0 || c >= w || visited[r][c] || grid[r][c] != id) {
        return;
    }
    visited[r][c] = true;
 
    if (r < h - 1 && grid[r + 1][c] != grid[r][c]) {
        graph1[id].insert(grid[r + 1][c]);
        graph2[grid[r + 1][c]].insert(id);
    }
 
    for (int i = 0; i < 4; i++) {
        int newr = r + dirs[i][0];
        int newc = c + dirs[i][1];
        dfs(graph1, graph2, newr, newc, id);
    }
}

void bfs_bottom(set<int> graph[], vector<int> &dists, vector<int> &bottom) {
    deque<pair<int, int>> bfs;
    for (int start : bottom) {
        bfs.push_back({0, start});
    }
    while (!bfs.empty()) {
        pair<int, int> p = bfs.front();
        bfs.pop_front();
 
        int dist = p.first;
        int cur = p.second;
        if (dists[cur] != -1) {
            continue;
        }
        dists[cur] = dist;

        for (int nei : graph[cur]) {
            bfs.push_back({dist + 1, nei});
        }
    }
}

void bfs_top(set<int> graph[], vector<int> &dists, int start) {
    deque<pair<int, int>> bfs;
    bfs.push_back({0, start});
    while (!bfs.empty()) {
        pair<int, int> p = bfs.front();
        bfs.pop_front();
 
        int dist = p.first;
        int cur = p.second;
        if (dists[cur] != -1) {
            continue;
        }
        dists[cur] = dist;

        for (int nei : graph[cur]) {
            bfs.push_back({dist + 1, nei});
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
 
    cin >> h >> w;
    int n = 0;
    int top1 = -1;
    int top2 = -1;
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            cin >> grid[i][j];
            n = max(n, grid[i][j]);
            grid[i][j]--;
            if (i == 0) {
                if (top1 == -1) {
                    top1 = grid[i][j];
                } else if (grid[i][j] != top1) {
                    top2 = grid[i][j];
                }
            }
            if (i == h - 1) {
                is_end[grid[i][j]] = true;
            }
        }
    }
    vector<int> bottom;
    for (int i = 0; i < n; i++) {
        if (is_end[i]) {
            bottom.push_back(i);
        }
    }
 
    set<int> graph1[n];
    set<int> graph2[n];
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            if (!visited[i][j]) {
                dfs(graph1, graph2, i, j, grid[i][j]);
            }
        }
    }
 
    vector<int> dist_bottom(n, -1);
    bfs_bottom(graph2, dist_bottom, bottom);
    vector<int> dist_top1(n, -1);
    bfs_top(graph1, dist_top1, top1);

    if (top2 == -1) {
        cout << dist_bottom[top1] + 1 << endl;
    } else {
        vector<int> dist_top2(n, -1);
        bfs_top(graph1, dist_top2, top2);

        int len = dist_bottom[top1] + dist_bottom[top2] + 2;
        for (int i = 0; i < n; i++) {
            if (dist_top1[i] != -1 && dist_top2[i] != -1) {
                int cur = dist_bottom[i] + 1 + dist_top1[i] + dist_top2[i];
                len = min(len, cur);
            }
        }
        cout << len << endl;
    }   
}