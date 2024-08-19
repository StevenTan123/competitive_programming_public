#include <bits/stdc++.h>
using namespace std;

void dfs(vector<int> tree[], vector<bool> &marked, int dp0[][2], int dp1[][2], int cur, int prev) {
    dp0[cur][0] = 0; dp0[cur][1] = 1;
    dp1[cur][0] = -1; dp1[cur][1] = -1;
    vector<int> diffs0;
    vector<int> diffs1;
    for (int nei : tree[cur]) {
        if (nei != prev) {
            dfs(tree, marked, dp0, dp1, nei, cur);
            dp0[cur][0] += max(dp0[nei][0], dp0[nei][1]);
            dp0[cur][1] += dp0[nei][0];

            if (dp1[nei][0] != -1) {
                diffs1.push_back(dp1[nei][0] - dp0[nei][0]);
            }
            if (dp1[nei][1] != -1) {
                diffs0.push_back(max(dp1[nei][0], dp1[nei][1]) - max(dp0[nei][0], dp0[nei][1]));
            }
        }
    }
    
    if (marked[cur]) {
        dp1[cur][1] = max(dp1[cur][1], dp0[cur][1]);
    }
    for (int diff : diffs0) {
        dp1[cur][0] = max(dp1[cur][0], dp0[cur][0] + diff);
    }
    for (int diff : diffs1) {
        dp1[cur][1] = max(dp1[cur][1], dp0[cur][1] + diff);
    }
} 

void solve() {
    int n;
    cin >> n;
    vector<int> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        tree[u - 1].push_back(v - 1);
        tree[v - 1].push_back(u - 1);
    }

    if (n == 2) {
        cout << "2\n";
    } else {
        // dp0[i][0/1] = max independent set in subtree i including/excluding node i
        int dp0[n][2];
        // dp1[i][0/1] = max indpendent set in subtree i including/excluding node i, and
        // including a parent of a leaf.
        int dp1[n][2];
        vector<bool> marked(n);
        for (int i = 0; i < n; i++) {
            for (int nei : tree[i]) {
                if (tree[nei].size() == 1) {
                    marked[i] = 1;
                }
            }
        }
        dfs(tree, marked, dp0, dp1, 0, -1);
        cout << max(max(dp0[0][0], dp0[0][1]), max(dp1[0][0], dp1[0][1]) + 1) << "\n";
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