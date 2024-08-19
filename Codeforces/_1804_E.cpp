#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    vector<vector<bool>> graph(n, vector<bool>(n));
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        graph[--u][--v] = 1;
        graph[v][u] = 1; 
    }

    // dp[i][bitmask] = possible to have path starting with smallest node in bitmask, visiting all nodes
    // in bitmask, ending on i.
    vector<vector<bool>> dp(n, vector<bool>(1 << n));
    vector<vector<pair<int, int>>> prev(n, vector<pair<int, int>>(1 << n));
    for (int i = 0; i < n; i++) {
        dp[i][1 << i] = true;
        prev[i][1 << i] = {-1, -1};
    }
    for (int j = 0; j < (1 << n); j++) {
        for (int i = 0; i < n; i++) {
            if (dp[i][j]) {
                bool set_bit = false;
                for (int k = 0; k < n; k++) {
                    if (j & (1 << k)) {
                        set_bit = true;
                    }
                    if (set_bit) {
                        int new_bitmask = j | (1 << k);
                        if (new_bitmask != j && graph[i][k]) {
                            dp[k][new_bitmask] = true;
                            prev[k][new_bitmask] = {i, j};
                        }
                    }
                }
            }
        }
    }

    for (int i = 1; i < (1 << n); i++) {
        int first = -1;
        for (int j = 0; j < n; j++) {
            if (i & (1 << j)) {
                first = j;
                break;
            }
        }
        for (int j = first + 1; j < n; j++) {
            if (dp[j][i] && graph[first][j]) {
                vector<bool> cycle(n);
                vector<int> a(n);
                int cur = j;
                int bitmask = i;
                while (cur != first) {
                    cycle[cur] = true;
                    a[cur] = prev[cur][bitmask].first;
                    bitmask = prev[cur][bitmask].second;
                    cur = a[cur];
                }
                cycle[cur] = true;
                a[cur] = j;

                bool works = true;
                for (int k = 0; k < n; k++) {
                    if (!cycle[k]) {
                        int cycle_nei = -1;
                        for (int l = 0; l < n; l++) {
                            if (graph[k][l] && cycle[l]) {
                                cycle_nei = l;
                                break;
                            }
                        }
                        if (cycle_nei == -1) {
                            works = false;
                            break;
                        }
                        a[k] = cycle_nei;
                    }
                }
                
                if (works) {
                    cout << "Yes\n";
                    for (int k = 0; k < n; k++) {
                        cout << a[k] + 1 << " ";
                    }
                    cout << "\n";
                    return 0;
                }
            }
        }
    }
    cout << "No\n";
}