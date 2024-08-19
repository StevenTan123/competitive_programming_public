#include <bits/stdc++.h>
using namespace std;

void dfs(vector<int> tree[], vector<double> &ev, int cur) {
    if (ev[cur] >= 0) {
        return;
    }
    ev[cur] = 0;
    for (int nei : tree[cur]) {
        dfs(tree, ev, nei);
        ev[cur] += (ev[nei] + 1) / tree[cur].size(); 
    }
}

void solve() {
    int n, s, e;
    cin >> n >> s >> e;
    s--; e--;

    vector<int> tree[n];
    int par[n];
    par[0] = -1;
    for (int i = 1; i < n; i++) {
        cin >> par[i];
        par[i]--;
        tree[par[i]].push_back(i);
    }
    tree[s].push_back(e);

    int cur = s;
    vector<int> loop;
    bool found = false;
    while (cur != -1) {
        loop.push_back(cur);
        if (cur == e) {
            found = true;
            break;
        }
        cur = par[cur];
    }

    vector<double> ev(n, -1);
    if (found) {
        double p_stay = 1;
        double ev_leave = 0;
        int cnt = 1;
        for (int i = (int) loop.size() - 1; i >= 0; i--) {
            int node = loop[i];
            int prev = (i == 0 ? e : loop[i - 1]);
            for (int nei : tree[node]) {
                if (nei != prev) {
                    dfs(tree, ev, nei);
                    ev_leave += p_stay / tree[node].size() * (ev[nei] + cnt);
                }
            }
            p_stay /= tree[node].size();
            cnt++;
        }
        
        ev[e] = (p_stay * loop.size() + ev_leave) / (1 - p_stay);
        for (int i = 0; i < (int) loop.size() - 1; i++) {
            int node = loop[i];
            int prev = (i == 0 ? e : loop[i - 1]);
            double c_ev_leave = 0;
            for (int nei : tree[node]) {
                if (nei != prev) {
                    c_ev_leave += ev[nei] / tree[node].size();
                }
            }
            ev[node] = (ev[prev] + 1) / tree[node].size() + ev_leave;
        }
    }

    dfs(tree, ev, 0);
    cout << fixed << setprecision(10) << ev[0] << "\n";
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