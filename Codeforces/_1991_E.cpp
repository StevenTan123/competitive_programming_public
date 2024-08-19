#include <bits/stdc++.h>
using namespace std;

const int BITS = 30;

bool dfs_color(vector<vector<int>> &graph, vector<int> &colors, int cur, int col) {
    if (colors[cur] == -1) {
        colors[cur] = col;
    } else if (colors[cur] != col) {
        return false;
    } else {
        return true;
    }

    for (int nei : graph[cur]) {
        if (!dfs_color(graph, colors, nei, 1 - col)) {
            return false;
        }
    }
    return true;
}

int solve() {
    int n, m;
    cin >> n >> m;

    vector<vector<int>> graph(n);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    vector<int> colors(n, -1);
    bool two_colorable = true;
    for (int i = 0; i < n; i++) {
        if (colors[i] == -1) {
            if (!dfs_color(graph, colors, i, 0)) {
                two_colorable = false;
                break;
            }
        }
    }

    if (two_colorable) {
        cout << "Bob" << endl;
        stack<int> ones;
        stack<int> twos;
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                ones.push(i);
            } else {
                twos.push(i);
            }
        }
        for (int i = 0; i < n; i++) {
            int a, b;
            cin >> a;
            if (a == -1) {
                return 1;
            }
            cin >> b;

            if (ones.empty()) {
                int col = 3;
                if (a == 2 || b == 2) {
                    col = 2;
                }
                cout << twos.top() + 1 << " " << col << endl;
                twos.pop();
            } else if (twos.empty()) {
                int col = 3;
                if (a == 1 || b == 1) {
                    col = 1;
                }
                cout << ones.top() + 1 << " " << col << endl;
                ones.pop();
            } else {
                if (a == 1 || b == 1) {
                    cout << ones.top() + 1 << " " << 1 << endl;
                    ones.pop();
                } else {
                    cout << twos.top() + 1 << " " << 2 << endl;
                    twos.pop();
                }
            }
        }
    } else {
        cout << "Alice" << endl;
        for (int i = 0; i < n; i++) {
            cout << "1 2" << endl;
            int trash;
            cin >> trash;
            if (trash == -1) {
                return 1;
            }
            cin >> trash;
        }
    }
    return 0;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t--) {
        if (solve()) {
            return 0;
        }
    }
}