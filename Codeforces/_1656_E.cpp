#include <bits/stdc++.h>
using namespace std;

void dfs(vector<int> tree[], vector<int> &colors, int cur, int prev, int col) {
    colors[cur] = col;
    for (int nei : tree[cur]) {
        if (nei != prev) {
            dfs(tree, colors, nei, cur, 1 - col);
        }
    }
}

void solve() {
    int n;
    cin >> n;
    vector<int> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        tree[u].push_back(v);
        tree[v].push_back(u);
    }
    vector<int> colors(n);
    dfs(tree, colors, 0, -1, 0);
    for (int i = 0; i < n; i++) {
        if (colors[i] == 0) {
            cout << tree[i].size() << " ";
        } else {
            cout << -(int) tree[i].size() << " ";
        }
    }
    cout << "\n";
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