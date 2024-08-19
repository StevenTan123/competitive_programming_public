#include <bits/stdc++.h>
using namespace std;

long long MOD;

void dfs_down(vector<int> tree[], long long dp_down[], int cur, int prev) {
    dp_down[cur] = 1;
    for (int nei : tree[cur]) {
        if (nei != prev) {
            dfs_down(tree, dp_down, nei, cur);
            dp_down[cur] *= dp_down[nei] + 1;
            dp_down[cur] %= MOD;
        }
    }
}

void dfs_up(vector<int> tree[], long long dp_up[], long long dp_down[], int cur, int prev) {
    if (prev == -1) {
        dp_up[cur] = 0;
    }

    vector<int> children;
    for (int nei : tree[cur]) {
        if (nei != prev) {
            children.push_back(nei);
        }
    }

    long long forw[children.size()];
    for (int i = 0; i < children.size(); i++) {
        forw[i] = (i > 0 ? forw[i - 1] : 1) * (dp_down[children[i]] + 1) % MOD;
    }
    long long backw[children.size()];
    for (int i = children.size() - 1; i >= 0; i--) {
        backw[i] = (i < children.size() - 1 ? backw[i + 1] : 1) * (dp_down[children[i]] + 1) % MOD;
    }

    for (int i = 0; i < children.size(); i++) {
        long long prod = (i > 0 ? forw[i - 1] : 1) * (i < children.size() - 1 ? backw[i + 1] : 1) % MOD;
        dp_up[children[i]] = (dp_up[cur] + 1) * prod % MOD;
        dfs_up(tree, dp_up, dp_down, children[i], cur);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N;
    cin >> N >> MOD;
    vector<int> tree[N];
    for (int i = 0; i < N - 1; i++) {
        int x, y;
        cin >> x >> y;
        tree[--x].push_back(--y);
        tree[y].push_back(x);
    }

    long long dp_up[N];
    long long dp_down[N];
    dfs_down(tree, dp_down, 0, -1);
    dfs_up(tree, dp_up, dp_down, 0, -1);
    for (int i = 0; i < N; i++) {
        long long ways = dp_down[i] * (dp_up[i] + 1);
        ways = (ways + MOD) % MOD;
        cout << ways << endl;
    }
}