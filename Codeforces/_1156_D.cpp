#include <bits/stdc++.h>
using namespace std;

int dfs(vector<int> graph[], vector<int> &comps, int comp, int cur, int prev) {
    comps[cur] = comp;
    int size = 1;
    for (int nei : graph[cur]) {
        if (nei != prev) {
            size += dfs(graph, comps, comp, nei, cur);
        }
    }
    return size;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    vector<int> zero_graph[n];
    vector<int> one_graph[n];
    for (int i = 0; i < n - 1; i++) {
        int x, y, c;
        cin >> x >> y >> c;
        x--; y--;
        if (c == 0) {
            zero_graph[x].push_back(y);
            zero_graph[y].push_back(x);
        } else {
            one_graph[x].push_back(y);
            one_graph[y].push_back(x); 
        }
    }

    vector<int> comps_zero(n, -1);
    vector<int> zero_sizes;
    int comp = 0;
    for (int i = 0; i < n; i++) {
        if (comps_zero[i] == -1) {
            int size = dfs(zero_graph, comps_zero, comp, i, -1);
            zero_sizes.push_back(size);
            comp++;
        }
    }

    vector<int> comps_one(n, -1);
    vector<int> one_sizes;
    comp = 0;
    for (int i = 0; i < n; i++) {
        if (comps_one[i] == -1) {
            int size = dfs(one_graph, comps_one, comp, i, -1);
            one_sizes.push_back(size);
            comp++;
        }
    }

    long long res = 0;
    for (int i = 0; i < n; i++) {
        res += (long long) (zero_sizes[comps_zero[i]] - 1) * (one_sizes[comps_one[i]] - 1);
        res += zero_sizes[comps_zero[i]] + one_sizes[comps_one[i]] - 2;
    }
    cout << res << endl;
}