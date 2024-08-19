#include <bits/stdc++.h>
using namespace std;

void dfs(vector<int> tree[], vector<int> &par, vector<int> &depths, int cur, int prev, int depth) {
    par[cur] = prev;
    depths[cur] = depth;
    for (int nei : tree[cur]) {
        if (nei != prev) {
            dfs(tree, par, depths, nei, cur, depth + 1);
        }
    }
}

void solve() {
    int n, q;
    cin >> n >> q;
    vector<bool> c(n);
    for (int i = 0; i < n; i++) {
        int ci;
        cin >> ci;
        c[i] = ci;
    }
    vector<int> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int x, y;
        cin >> x >> y;
        x--; y--;
        tree[x].push_back(y);
        tree[y].push_back(x);
    }
    vector<int> par(n);
    vector<int> depths(n);
    dfs(tree, par, depths, 0, -1, 0);

    vector<int> child_cnts(n);
    for (int i = 0; i < n; i++) {
        if (c[i] && i != 0) {
            child_cnts[par[i]]++;
        }
    }
    vector<int> cnts(4);
    set<pair<int, int>> depth_set;
    for (int i = 0; i < n; i++) {
        if (c[i]) {
            cnts[min(3, child_cnts[i])]++;
            depth_set.emplace(depths[i], i);
        }
    }

    for (int i = 0; i < q; i++) {
        int u;
        cin >> u;
        u--;
        if (c[u]) {
            c[u] = 0;
            if (u != 0) {
                if (c[par[u]]) cnts[min(3, child_cnts[par[u]])]--;
                child_cnts[par[u]]--;
                if (c[par[u]]) cnts[min(3, child_cnts[par[u]])]++;
            }
            cnts[min(3, child_cnts[u])]--;
            depth_set.erase({depths[u], u});
        } else {
            c[u] = 1;
            if (u != 0) {
                if (c[par[u]]) cnts[min(3, child_cnts[par[u]])]--;
                child_cnts[par[u]]++;
                if (c[par[u]]) cnts[min(3, child_cnts[par[u]])]++;
            }
            cnts[min(3, child_cnts[u])]++;
            depth_set.emplace(depths[u], u);
        }
        if (cnts[3] == 0) {
            if (depth_set.size() == 0) {
                cout << "No\n";
            } else {
                int root = depth_set.begin()->second;
                if (child_cnts[root] == 0) {
                    cout << (cnts[0] == 1 && cnts[1] == 0 && cnts[2] == 0 ? "Yes\n" : "No\n"); 
                } else if (child_cnts[root] == 1) {
                    cout << (cnts[2] == 0 && cnts[0] == 1 ? "Yes\n" : "No\n");
                } else {
                    cout << (cnts[2] == 1 && cnts[0] == 2 ? "Yes\n" : "No\n");
                }
            }
        } else {
            cout << "No\n";
        }
    }
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