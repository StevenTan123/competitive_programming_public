#include <bits/stdc++.h>
using namespace std;

int cnt;
int n, k;

void dfs_sizes(vector<int> tree[], vector<int> &sizes, int cur, int prev) {
    sizes[cur] = 1;
    for (int nei : tree[cur]) {
        if (nei != prev) {
            dfs_sizes(tree, sizes, nei, cur);
            sizes[cur] += sizes[nei];
        }
    }
}

int dfs_cut(vector<int> tree[], vector<int> &sizes, int x, int cur, int prev) {
    int sub = 0;
    for (int nei : tree[cur]) {
        if (nei != prev) {
            sub += dfs_cut(tree, sizes, x, nei, cur);
        }
    }
    if (cur != 0 && sizes[cur] - sub >= x && cnt < k) {
        sub = sizes[cur];
        cnt++;
    }
    if (cur == 0 && sizes[cur] - sub < x) {
        cnt = -1;
    }
    return sub;
}

void solve() {
    cin >> n >> k;
    vector<int> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        tree[u].push_back(v);
        tree[v].push_back(u);
    }

    vector<int> sizes(n);
    dfs_sizes(tree, sizes, 0, -1);

    int l = 1;
    int r = n;
    int res = -1;
    while (l <= r) {
        int m = (l + r) / 2;
        cnt = 0;
        dfs_cut(tree, sizes, m, 0, -1);
        if (cnt >= k) {
            res = m;
            l = m + 1;
        } else {
            r = m - 1;
        }
    }
    cout << res << "\n";
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