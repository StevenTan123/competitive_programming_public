#include <bits/stdc++.h>
using namespace std;

void dfs(vector<int> graph[], vector<bool> &visited, vector<int> &comp, int cur) {
    if (visited[cur]) {
        return;
    }
    visited[cur] = true;
    comp.push_back(cur);
    for (int nei : graph[cur]) {
        dfs(graph, visited, comp, nei);
    }
}

bool cyclic_dfs(vector<int> digraph[], vector<int> &marks, int cur, int avoid) {
    if (marks[cur] == 1) {
        return true;
    } else if (marks[cur] == 2) {
        return false;
    }
    marks[cur] = 1;
    for (int nei : digraph[cur]) {
        if (nei == avoid) {
            continue;
        }
        if (cyclic_dfs(digraph, marks, nei, avoid)) {
            return true;
        }
    }
    marks[cur] = 2;
    return false;
}

bool cyclic(vector<int> digraph[], vector<int> &marks, vector<int> &comp, int avoid) {
    for (int node : comp) {
        if (node != avoid && !marks[node]) {
            if (cyclic_dfs(digraph, marks, node, avoid)) {
                return true;
            }
        }
    }
    return false;
}

void topo_dfs(vector<int> digraph[], vector<int> &marks, vector<int> &order, int cur, int avoid) {
    if (marks[cur]) {
        return;
    }
    marks[cur] = 1;
    for (int nei : digraph[cur]) {
        if (nei != avoid) {
            topo_dfs(digraph, marks, order, nei, avoid);
        }
    }
    order.push_back(cur);
}

void toposort(vector<int> digraph[], vector<int> &marks, vector<int> &comp, vector<int> &order, int avoid) {
    for (int node : comp) {
        if (node != avoid && !marks[node]) {
            topo_dfs(digraph, marks, order, node, avoid);
        }
    }
    reverse(order.begin(), order.end());
}

void reset_marks(vector<int> &comp, vector<int> &marks) {
    for (int node : comp) {
        marks[node] = 0;
    }
}

bool solve_comp(vector<int> digraph[], vector<int> &comp, vector<int> &marks, vector<pair<int, int>> &res) {
    if (comp.size() <= 1) {
        return true;
    }
    if (!cyclic(digraph, marks, comp, -1)) {
        reset_marks(comp, marks);
        vector<int> order;
        toposort(digraph, marks, comp, order, -1);
        for (int i = 1; i < order.size(); i++) {
            res.emplace_back(order[i - 1], order[i]);
        }
        return true;
    }
    for (int node : comp) {
        reset_marks(comp, marks);
        if (!cyclic(digraph, marks, comp, node)) {
            reset_marks(comp, marks);
            vector<int> order;
            toposort(digraph, marks, comp, order, node);

            res.emplace_back(node, order[0]);
            for (int i = 1; i < order.size(); i++) {
                res.emplace_back(order[i - 1], order[i]);
            }
            res.emplace_back(order[order.size() - 1], node);
            return true;
        }
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    vector<int> graph[n];
    vector<int> digraph[n];
    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        a--; b--;
        graph[a].push_back(b);
        graph[b].push_back(a);
        digraph[a].push_back(b);
    }

    vector<bool> visited(n);
    vector<int> marks(n);
    vector<pair<int, int>> res;
    bool possible = true;
    for (int i = 0; i < n; i++) {
        vector<int> comp;
        if (!visited[i]) {
            dfs(graph, visited, comp, i);
        }
        possible = possible && solve_comp(digraph, comp, marks, res);
        if (!possible) {
            break;
        }
    }

    if (possible) {
        cout << res.size() << "\n";
        for (auto [from, to] : res) {
            cout << from + 1 << " " << to + 1 << "\n";
        }
    } else {
        cout << "-1\n";
    }
}