#include <bits/stdc++.h>
using namespace std;

void dfs(vector<int> graph[], vector<int> &par, int cur, int prev) {
    if (par[cur] != -1) {
        return;
    }
    par[cur] = prev;
    for (int nei : graph[cur]) {
        dfs(graph, par, nei, cur);
    }
}

int dfs2(vector<int> graph[], vector<bool> &path, vector<int> &par, int cur, int prev) {
    if (par[cur] != -1) {
        return -1;
    }
    par[cur] = prev;
    for (int nei : graph[cur]) {
        if (nei != prev) {
            if (path[nei]) {
                par[nei] = cur;
                return nei;
            }
            int ret = dfs2(graph, path, par, nei, cur);
            if (ret != -1) {
                return ret;
            }
        }
    }
    return -1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    vector<int> graph[n];
    map<int, int> edges[n];
    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
        edges[a][b] = i;
        edges[b][a] = i;
    }

    vector<int> par(n, -1);
    dfs(graph, par, 0, 0);
    vector<bool> path(n, 0);
    vector<int> path_list;
    int cur = 1;
    while (cur != 0) {
        path[cur] = 1;
        path_list.push_back(cur);
        cur = par[cur];
    }
    path[cur] = 1;
    path_list.push_back(cur);

    vector<int> par2(n, -1);
    int loop = dfs2(graph, path, par2, 1, par[1]);
    if (loop == -1) {
        cout << "No solution\n";
    } else {
        vector<int> bob_path_list;
        cur = loop;
        do {
            bob_path_list.push_back(cur);
            cur = par2[cur];
        } while (cur != 1);
        bob_path_list.push_back(cur);

        if (loop == 1) {

            for (int i = 1; i < (int) path_list.size(); i++) {
                cout << edges[path_list[i - 1]][path_list[i]] << " ";
            }
            for (int i = (int) bob_path_list.size() - 2; i >= 1; i--) {
                cout << edges[bob_path_list[i + 1]][bob_path_list[i]] << " ";
            }
            cout << "\n";
            cout << edges[bob_path_list[0]][bob_path_list[1]] << " ";
            cout << "\n";

            vector<int> keys;
            for (int i = (int) path_list.size() - 2; i >= 0; i--) {
                cout << "MOVE " << path_list[i] << "\n";
                keys.push_back(edges[path_list[i + 1]][path_list[i]]);
            }
            for (int i = (int) bob_path_list.size() - 2; i >= 1; i--) {
                cout << "MOVE " << bob_path_list[i] << "\n";
            }
            cout << "DROP ";
            for (int key : keys) {
                cout << key << " ";
            }
            cout << "\n";
            for (int i = 2; i < (int) bob_path_list.size(); i++) {
                cout << "MOVE " << bob_path_list[i] << "\n";
            }
            cout << "DONE\n";

            cout << "MOVE " << bob_path_list[1] << "\n";
            cout << "GRAB\n";
            cout << "MOVE " << bob_path_list[0] << "\n";
            cur = loop;
            while (cur != 0) {
                cout << "MOVE " << par[cur] << "\n";
                cur = par[cur];
            }
            cout << "DONE\n";

        } else {

            for (int i = 1; i < (int) path_list.size(); i++) {
                cout << edges[path_list[i - 1]][path_list[i]] << " ";
            }
            cout << "\n";
            for (int i = 1; i < (int) bob_path_list.size(); i++) {
                cout << edges[bob_path_list[i - 1]][bob_path_list[i]] << " ";
            }
            cout << "\n";

            for (int i = (int) path_list.size() - 2; i >= 0; i--) {
                cout << "MOVE " << path_list[i] << "\n";
                if (i > 0) {
                    cout << "DROP " << edges[path_list[i + 1]][path_list[i]] << "\n";
                }
            }
            cout << "DONE\n";

            for (int i = (int) bob_path_list.size() - 2; i >= 0; i--) {
                cout << "MOVE " << bob_path_list[i] << "\n";
            }
            cur = loop;
            while (cur != 0) {
                cout << "GRAB\n";
                cout << "MOVE " << par[cur] << "\n";
                cur = par[cur];
            }
            cout << "DONE\n";

        }
    }
}