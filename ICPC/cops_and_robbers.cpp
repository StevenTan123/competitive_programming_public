#include <bits/stdc++.h>
using namespace std;
 
const int INF = 1e9;
int dirs[][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
 
int bfs(int s, int t, vector<int> network[], vector<vector<int>> &capacity, vector<int>& parent) {
    fill(parent.begin(), parent.end(), -1);
    parent[s] = -2;
    queue<pair<int, int>> q;
    q.push({s, INF});
 
    while (!q.empty()) {
        int cur = q.front().first;
        int flow = q.front().second;
        q.pop();
 
        for (int next : network[cur]) {
            if (parent[next] == -1 && capacity[cur][next]) {
                parent[next] = cur;
                int new_flow = min(flow, capacity[cur][next]);
                if (next == t) {
                    return new_flow;
                }
                q.push({next, new_flow});
            }
        }
    }
 
    return 0;
}
 
int maxflow(int n, int s, int t, vector<int> network[], vector<vector<int>> &capacity) {
    int flow = 0;
    vector<int> parent(n);
    int new_flow;
 
    while ((new_flow = bfs(s, t, network, capacity, parent))) {
        flow += new_flow;
        int cur = t;
        while (cur != s) {
            int prev = parent[cur];
            capacity[prev][cur] -= new_flow;
            capacity[cur][prev] += new_flow;
            cur = prev;
        }
    }
 
    return flow;
}
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
 
    int n, m, c;
    cin >> m >> n >> c;
    int grid[n][m];
    int br, bc;
    for (int i = 0; i < n; i++) {
        string line;
        cin >> line;
        for (int j = 0; j < m; j++) {
            if (line[j] == '.') {
                grid[i][j] = -1;
            } else if (line[j] == 'B') {
                br = i; bc = j;
                grid[i][j] = -1;
            } else {
                grid[i][j] = line[j] - 'a';
            }
        }
    }
    int cost[c];
    for (int i = 0; i < c; i++) {
        cin >> cost[i];
    }
 
    int v = n * m * 2 + 1;
    int s = (br * m + bc) * 2 + 1;
    int t = n * m * 2;
    
    vector<vector<int>> capacity(v, vector<int>(v, 0));
    vector<int> network[v];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int v_enter = (i * m + j) * 2;
            int v_exit = (i * m + j) * 2 + 1;
 
            network[v_enter].push_back(v_exit);
            network[v_exit].push_back(v_enter);
            if (grid[i][j] >= 0) {
                capacity[v_enter][v_exit] = cost[grid[i][j]];
            } else {
                capacity[v_enter][v_exit] = INF;
            }
 
            if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                network[v_exit].push_back(t);
                capacity[v_exit][t] = INF;
            } else {
                for (int k = 0; k < 4; k++) {
                    int newi = i + dirs[k][0];
                    int newj = j + dirs[k][1];
                    int u_enter = (newi * m + newj) * 2;
                    network[v_exit].push_back(u_enter);
                    network[u_enter].push_back(v_exit);
                    if (grid[newi][newj] >= 0) {
                        capacity[v_exit][u_enter] = cost[grid[newi][newj]];
                    } else {
                        capacity[v_exit][u_enter] = INF;
                    }
                }
            }
        }
    }
 
    int res = maxflow(v, s, t, network, capacity);
    if (res >= INF) {
        res = -1;
    }
    cout << res << endl;
}