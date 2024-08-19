#include <bits/stdc++.h>
using namespace std;

bool back_edge = false;

void dfs(vector<pair<int, int>> graph[], vector<int> &marks, vector<int> &edge_colors, int cur) {
    if (marks[cur] != 0) {
        return; 
    }
    marks[cur] = 1;
    for (auto [nei, edge_id] : graph[cur]) {
        if (marks[nei] == 1) {
            back_edge = true;
            edge_colors[edge_id] = 2;
        } else {
            edge_colors[edge_id] = 1;
        }
        dfs(graph, marks, edge_colors, nei);
        
    }
    marks[cur] = 2;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    vector<pair<int, int>> graph[n];
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        graph[u - 1].emplace_back(v - 1, i);
    }

    vector<int> marks(n);
    vector<int> edge_colors(m);
    for (int i = 0; i < n; i++) {
        if (marks[i] == 0) {
            dfs(graph, marks, edge_colors, i);
        }
    }
    cout << (back_edge ? 2 : 1) << endl;
    for (int i = 0; i < m; i++) {
        cout << edge_colors[i] << " ";
    }
    cout << endl;
}