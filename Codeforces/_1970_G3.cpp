#include <bits/stdc++.h>
using namespace std;

const int MAXV = 50001;
bitset<MAXV> dp0;
bitset<MAXV> dp1;

int cnt = 0;

int dfs(vector<int> graph[], vector<bool> &visited, vector<int> &sizes, vector<int> &tin, set<int> &breaks, int cur, int prev) {
    visited[cur] = true;
    tin[cur] = cnt++;
    sizes[cur] = 1;
    int min_back = INT_MAX;
    for (int nei : graph[cur]) {
        if (visited[nei]) {
            if (nei != prev) {
                min_back = min(min_back, tin[nei]);
            }
        } else {
            int min_back_next = dfs(graph, visited, sizes, tin, breaks, nei, cur);
            if (min_back_next > tin[cur]) {
                breaks.insert(sizes[nei]);
            }
            min_back = min(min_back, min_back_next);
            sizes[cur] += sizes[nei];   
        }
    }
    return min_back;
}

void solve() {
    int n, m, c;
    cin >> n >> m >> c;
    vector<int> graph[n];
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        graph[u - 1].push_back(v - 1);
        graph[v - 1].push_back(u - 1);
    }

    cnt = 0;
    dp0.reset();
    dp1.reset();

    vector<bool> visited(n, false);
    vector<int> sizes(n);
    vector<int> tin(n);
    vector<set<int>> breaks;
    vector<int> comp_sizes;
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            set<int> cur_breaks;
            dfs(graph, visited, sizes, tin, cur_breaks, i, -1);
            breaks.push_back(cur_breaks);
            comp_sizes.push_back(sizes[i]);
        }
    }

    dp0[0] = 1;
    for (int i = 0; i < comp_sizes.size(); i++) {
        dp1 = dp1 | (dp1 << comp_sizes[i]);
        for (int split : breaks[i]) {
            dp1 = dp1 | (dp0 << split);
            dp1 = dp1 | (dp0 << (comp_sizes[i] - split));
        }
        dp0 = dp0 | (dp0 << comp_sizes[i]);
    }

    long long min_fund = LONG_LONG_MAX;
    for (int i = 1; i < min(MAXV, n); i++) {
        if (dp0[i] || dp1[i]) {
            min_fund = min(min_fund, (long long) i * i + (long long) (n - i) * (n - i) + c * ((long long) comp_sizes.size() - 1));
        }
    }
    cout << (min_fund == LONG_LONG_MAX ? -1 : min_fund) << "\n";
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