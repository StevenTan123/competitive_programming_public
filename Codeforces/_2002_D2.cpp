#include <bits/stdc++.h>
using namespace std;

struct BoolArr {
    vector<int> arr;
    int cnt;
    BoolArr(int n) : arr(n), cnt(0) {}
    void set(int ind, int val) {
        cnt += val - arr[ind];
        arr[ind] = val;
    } 
};

void dfs(vector<vector<int>> &graph, vector<int> &sizes, int cur) {
    sizes[cur] = 1;
    for (int child : graph[cur]) {
        dfs(graph, sizes, child);
        sizes[cur] += sizes[child];
    }
}

void check_before_child(int n, int cur, vector<int> &p, vector<int> &loc, vector<set<int>> &csets, BoolArr &before_child) {
    if (csets[cur].size() > 0) {
        if (loc[cur] + 1 < n && csets[cur].count(p[loc[cur] + 1])) {
            before_child.set(cur, 1);
        } else {
            before_child.set(cur, 0);
        }
    } else {
        before_child.set(cur, 1);
    }
}

void check_space_after(int cur, vector<int> &loc, vector<int> &pars, vector<int> &sizes, vector<set<pair<int, int>>> &loc_sets, BoolArr &spaced) {
    if (pars[cur] != -1) {
        auto it = loc_sets[pars[cur]].upper_bound({loc[cur], cur});
        if (it == loc_sets[pars[cur]].end() || it->first - loc[cur] == sizes[cur]) {
            spaced.set(cur, 1);
        } else {
            spaced.set(cur, 0);
        }
    } else {
        spaced.set(cur, 1);
    }
}

void solve() {
    int n, q;
    cin >> n >> q;
    vector<int> pars(n);
    vector<vector<int>> graph(n);
    pars[0] = -1;
    for (int i = 1; i < n; i++) {
        cin >> pars[i];
        pars[i]--;
        graph[pars[i]].push_back(i);
    }
    vector<int> p(n);
    vector<int> loc(n);
    for (int i = 0; i < n; i++) {
        cin >> p[i];
        p[i]--;
        loc[p[i]] = i;
    }

    vector<int> sizes(n);
    dfs(graph, sizes, 0);
    vector<set<int>> csets(n);
    vector<set<pair<int, int>>> loc_sets(n);
    for (int i = 1; i < n; i++) {
        csets[pars[i]].insert(i);
        loc_sets[pars[i]].emplace(loc[i], i);
    }

    BoolArr before_child(n);
    for (int i = 0; i < n; i++) {
        check_before_child(n, i, p, loc, csets, before_child);
    }

    BoolArr spaced(n);
    for (int i = 0; i < n; i++) {
        check_space_after(i, loc, pars, sizes, loc_sets, spaced);
    }

    for (int i = 0; i < q; i++) {
        int x, y;
        cin >> x >> y;
        x--; y--;
        int xx = p[x];
        int yy = p[y];

        vector<int> check {xx, yy};
        if (pars[xx] != -1) {
            auto it = loc_sets[pars[xx]].lower_bound({loc[xx], xx});
            if (it != loc_sets[pars[xx]].begin()) {
                check.push_back(std::prev(it)->second);
            }
            loc_sets[pars[xx]].erase({loc[xx], xx});
        }
        if (pars[yy] != -1) {
            auto it = loc_sets[pars[yy]].lower_bound({loc[yy], yy});
            if (it != loc_sets[pars[yy]].begin()) {
                check.push_back(std::prev(it)->second);
            }
            loc_sets[pars[yy]].erase({loc[yy], yy});
        }

        swap(p[x], p[y]);
        loc[p[x]] = x;
        loc[p[y]] = y;
        if (pars[xx] != -1) {
            loc_sets[pars[xx]].emplace(loc[xx], xx);
            check.push_back(pars[xx]);
            
            auto it = loc_sets[pars[xx]].lower_bound({loc[xx], xx});
            if (it != loc_sets[pars[xx]].begin()) {
                check.push_back(std::prev(it)->second);
            }
        }
        if (pars[yy] != -1) {
            loc_sets[pars[yy]].emplace(loc[yy], yy);
            check.push_back(pars[yy]);

            auto it = loc_sets[pars[yy]].lower_bound({loc[yy], yy});
            if (it != loc_sets[pars[yy]].begin()) {
                check.push_back(std::prev(it)->second);
            }
        }

        for (int node : check) {
            check_before_child(n, node, p, loc, csets, before_child);
            check_space_after(node, loc, pars, sizes, loc_sets, spaced);
        }

        if (before_child.cnt == n && spaced.cnt == n) {
            cout << "YES\n";
        } else {
            cout << "NO\n";
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