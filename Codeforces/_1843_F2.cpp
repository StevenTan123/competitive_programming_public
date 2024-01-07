#include <bits/stdc++.h>
using namespace std;

struct info {
    int sum, minv, maxv, minp, mins, maxp, maxs;

    info(int val = 0) {
        sum = val;
        minv = minp = mins = min(val, 0);
        maxv = maxp = maxs = max(val, 0);
    }
};

info combine(info &a, info &b) {
    info res;
    res.sum = a.sum + b.sum;
    res.minp = min(a.minp, a.sum + b.minp);
    res.maxp = max(a.maxp, a.sum + b.maxp);
    res.mins = min(b.mins, a.mins + b.sum);
    res.maxs = max(b.maxs, a.maxs + b.sum);
    res.minv = min({a.minv, b.minv, a.mins + b.minp});
    res.maxv = max({a.maxv, b.maxv, a.maxs + b.maxp});
    return res;
}

void solve() {
    int n;
    cin >> n;
    vector<int> pars, vals;
    vector<vector<int>> queries;
    for (int i = 0; i < n; i++) {
        char type;
        cin >> type;
        if (type == '+') {
            int par, val;
            cin >> par >> val;
            pars.push_back(par - 1);
            vals.push_back(val);
        }
        else {
            vector<int> query;
            int u, v, k;
            cin >> u >> v >> k;
            query.push_back(u - 1);
            query.push_back(v - 1);
            query.push_back(k);
            queries.push_back(query);
        }
    }
    int m = pars.size() + 1;
    int jumps = 18;
    int ancestor[m][jumps];
    info infos[m][jumps];
    int depths[m];
    for (int i = 0; i < m; i++) {
        if (i == 0) {
            ancestor[i][0] = -1;
            infos[i][0] = info(1);
        } else {
            ancestor[i][0] = pars[i - 1];
            infos[i][0] = info(vals[i - 1]);
            depths[i] = depths[pars[i - 1]] + 1;
        }
        for (int j = 1; j < jumps; j++) {
            if (i == 0 || ancestor[i][j - 1] == -1) {
                ancestor[i][j] = -1;
            } else {
                ancestor[i][j] = ancestor[ancestor[i][j - 1]][j - 1];
                if (ancestor[i][j] > -1) {
                    infos[i][j] = combine(infos[i][j - 1], infos[ancestor[i][j - 1]][j - 1]);
                }
            }
        }
    }
    for (vector<int> query : queries) {
        int u = query[0];
        int v = query[1];
        if (depths[u] < depths[v]) {
            swap(u, v);
        }
        info ans1;
        for (int i = jumps - 1; i >= 0; i--) {
            if (ancestor[u][i] > -1 && depths[ancestor[u][i]] >= depths[v]) {
                ans1 = combine(ans1, infos[u][i]);
                u = ancestor[u][i];
            }
        }
        info ans;
        if (u == v) {
            ans = combine(ans1, infos[u][0]);
        } else {
            info ans2;
            for (int i = jumps - 1; i >= 0; i--) {
                if (ancestor[u][i] != ancestor[v][i]) {
                    ans1 = combine(ans1, infos[u][i]);
                    ans2 = combine(ans2, infos[v][i]);
                    u = ancestor[u][i];
                    v = ancestor[v][i];
                }
            }
            ans1 = combine(ans1, infos[u][0]);
            ans1 = combine(ans1, infos[ancestor[u][0]][0]);
            ans2 = combine(ans2, infos[v][0]);
            swap(ans2.minp, ans2.mins);
            swap(ans2.maxp, ans2.maxs);
            ans = combine(ans1, ans2);
        }
        if (query[2] >= ans.minv && query[2] <= ans.maxv) {
            cout << "YES" << "\n";
        } else {
            cout << "NO" << "\n";
        }
    }
}

int main() {
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}