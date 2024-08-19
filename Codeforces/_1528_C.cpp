#include <bits/stdc++.h>
using namespace std;

int cur_time;

struct CliqueSet {
    set<tuple<int, int, bool>> intervals;
    int size;
    CliqueSet() : size(0) {};
    void add(int l, int r) {
        auto after = intervals.lower_bound({l, 0, 0});
        int after_r = (after == intervals.end() ? INT_MAX : get<1>(*after));
        if (after_r < r) {
            intervals.emplace(l, r, 0);
            return;
        }
        if (after != intervals.begin()) {
            int before_r = get<1>(*prev(after));
            if (before_r > r) {
                if (before_r > after_r) {
                    size++;
                    intervals.emplace(l, r, 1);
                    return;
                } else {
                    intervals.emplace(l, r, 0);
                    return;
                }
            }
        }
        size++;
        intervals.emplace(l, r, 1);
    }
    void erase(int l, int r) {
        auto it = intervals.lower_bound({l, 0, 0});
        size -= get<2>(*it);
        intervals.erase(it);
    }
};

void dfs_time(vector<int> tree[], int tin[], int tout[], int cur) {
    tin[cur] = cur_time++;
    for (int nei : tree[cur]) {
        dfs_time(tree, tin, tout, nei);
    }
    tout[cur] = cur_time++;
}

int dfs(vector<int> tree[], int tin[], int tout[], CliqueSet &cset, int cur) {
    cset.add(tin[cur], tout[cur]);
    int res = cset.size;
    for (int nei : tree[cur]) {
        res = max(res, dfs(tree, tin, tout, cset, nei));
    }
    cset.erase(tin[cur], tout[cur]);
    return res;
}

void solve() {
    int n;
    cin >> n;
    vector<int> tree1[n];
    vector<int> tree2[n];
    for (int i = 1; i < n; i++) {
        int pi;
        cin >> pi;
        tree1[pi - 1].push_back(i);
    }
    for (int i = 1; i < n; i++) {
        int pi;
        cin >> pi;
        tree2[pi - 1].push_back(i);
    }

    int tin[n];
    int tout[n];
    cur_time = 0;
    dfs_time(tree2, tin, tout, 0);

    CliqueSet cset;
    int res = dfs(tree1, tin, tout, cset, 0);
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