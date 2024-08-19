#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;

    int c[n];
    for (int i = 0; i < n; i++) {
        cin >> c[i];
    }
    int a[n][m];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
        }
    }

    int N = n + n * m;
    vector<pair<int, int>> graph[N];
    for (int i = 0; i < m; i++) {
        pair<int, int> sorted[n];
        for (int j = 0; j < n; j++) {
            sorted[j] = {a[j][i], j};
        }
        sort(sorted, sorted + n);

        for (int j = 0; j < n; j++) {
            int vnode = (i + 1) * n + j;
            graph[sorted[j].second].emplace_back(vnode, 0);
            graph[vnode].emplace_back(sorted[j].second, c[sorted[j].second]);
            if (j > 0) {
                graph[vnode].emplace_back(vnode - 1, sorted[j].first - sorted[j - 1].first);
                graph[vnode - 1].emplace_back(vnode, 0);
            }
        }
    }

    vector<long long> dists(N, LONG_LONG_MAX);
    priority_queue<pair<long long, int>> pq;
    pq.emplace(0, 0);
    while (!pq.empty()) {
        auto [dist, cur] = pq.top();
        dist *= -1;
        pq.pop();
        if (dist >= dists[cur]) {
            continue;
        }
        dists[cur] = dist;

        for (auto [to, w] : graph[cur]) {
            pq.emplace(-(dist + w), to);
        }
    }
    cout << dists[n - 1] << "\n";
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