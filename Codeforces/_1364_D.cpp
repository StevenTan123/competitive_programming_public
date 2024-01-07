#include <bits/stdc++.h>
using namespace std;

vector<int> res;

bool dfs(vector<int> graph[], bool visited[], int depths[], vector<int> &path, int cur, int prev) {
    visited[cur] = true;
    depths[cur] = (prev == -1 ? 0 : depths[prev] + 1);
    path.push_back(cur);
    int max_depth = -1;
    for (int nei : graph[cur]) {
        if (nei != prev && visited[nei]) {
            if (max_depth == -1 || depths[nei] < depths[max_depth]) {
                max_depth = nei;
            }
        }
    }
    if (max_depth != -1) {
        res = path;
        res.push_back(max_depth);
        return true;
    }
    for (int nei : graph[cur]) {
        if (!visited[nei]) {
            if (dfs(graph, visited, depths, path, nei, cur)) {
                return true;
            }
        }
    }
    path.pop_back();
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, k;
    cin >> n >> m >> k;
    int ceil = k / 2;
    if (k % 2 == 1) {
        ceil++;
    }
    vector<int> graph[n];
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        graph[--u].push_back(--v);
        graph[v].push_back(u);
    }

    bool visited[n] = {0};
    int depths[n];
    vector<int> path;
    dfs(graph, visited, depths, path, 0, -1); 
    if (m == n - 1) {
        vector<int> odd;
        vector<int> even;
        for (int i = 0; i < n; i++) {
            if (depths[i] % 2 == 0) {
                even.push_back(i);
            } else {
                odd.push_back(i);
            }
        }
        vector<int> &res = (even.size() > odd.size() ? even : odd);
        cout << 1 << "\n";
        for (int i = 0; i < ceil; i++) {
            cout << (res[i] + 1) << " ";
        }
        cout << "\n";
    } else {
        vector<int> cycle;
        vector<int> LIS;
        for (int i = res.size() - 1; i >= 0; i--) {
            if (i < res.size() - 1 && res[i] == res[res.size() - 1]) {
                break;
            }
            if ((res.size() - 1 - i) % 2 == 0) {
                LIS.push_back(res[i]);
            }
            cycle.push_back(res[i]);
        }
        if (cycle.size() <= k) {
            cout << "2\n" << cycle.size() << "\n";
            for (int v : cycle) {
                cout << (v + 1) << " ";
            }
        } else {
            cout << "1\n";
            for (int i = 0; i < ceil; i++) {
                cout << (LIS[i] + 1) << " ";
            }
        }
    }
}