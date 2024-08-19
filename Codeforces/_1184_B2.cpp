#include <bits/stdc++.h>
using namespace std;

struct Kuhn {
    int n, k;
    vector<int> mt;
    vector<bool> used;

    Kuhn(int _n, int _k, vector<int> graph[]) : n(_n), k(_k) {
        mt.assign(k, -1);
        for (int v = 0; v < n; ++v) {
            used.assign(n, false);
            try_kuhn(graph, v);
        }
    }

    bool try_kuhn(vector<int> graph[], int v) {
        if (used[v])
            return false;
        used[v] = true;
        for (int to : graph[v]) {
            if (mt[to] == -1 || try_kuhn(graph, mt[to])) {
                mt[to] = v;
                return true;
            }
        }
        return false;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    vector<int> graph[n];
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    int s, b, k, h;
    cin >> s >> b >> k >> h;
    tuple<int, int, int> spaceships[s];
    for (int i = 0; i < s; i++) {
        int x, a, f;
        cin >> x >> a >> f;
        spaceships[i] = {x - 1, a, f};
    }
    pair<int, int> bases[b];
    for (int i = 0; i < b; i++) {
        int x, d;
        cin >> x >> d;
        bases[i] = {x - 1, d};
    }

    vector<int> bipartite[s];
    for (int i = 0; i < s; i++) {
        auto [x, attack, fuel] = spaceships[i];
        vector<int> dists(n, INT_MAX);
        deque<int> BFS;
        BFS.push_back(x);
        dists[x] = 0;
        while (!BFS.empty()) {
            int cur = BFS.front();
            BFS.pop_front();
            for (int nei : graph[cur]) {
                if (dists[cur] + 1 < dists[nei]) {
                    dists[nei] = dists[cur] + 1;
                    BFS.push_back(nei);
                }
            }
        }

        for (int j = 0; j < b; j++) {
            auto [base, defense] = bases[j];
            if (fuel >= dists[base] && attack >= defense) {
                bipartite[i].push_back(j);
            }
        }
    }

    Kuhn match(s, b, bipartite);
    int cnt = 0;
    for (int i = 0; i < b; i++) {
        if (match.mt[i] != -1) {
            cnt++;
        }
    }
    long long res = min((long long) s * h, (long long) k * cnt);
    cout << res << endl;
}