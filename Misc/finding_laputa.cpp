#include <bits/stdc++.h>
using namespace std;

const int MAXD = 34;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    int v = (1 << n);
    vector<pair<int, int>> graph[v];
    for (int i = 0; i < n * (1 << (n - 1)); i++) {
        int a, b, w;
        cin >> a >> b >> w;
        graph[a].emplace_back(b, w);
        graph[b].emplace_back(a, w);
    }

    vector<pair<int, int>> graph_added[v];
    for (int i = 0; i < v; i++) {
        vector<int> zeros;
        int cur = i;
        for (int j = 0; j < n; j++) {
            if (!(cur & 1)) {
                zeros.push_back(j);
            }
            cur >>= 1;
        }
        for (int j = 0; j < zeros.size(); j++) {
            for (int k = j + 1; k < zeros.size(); k++) {
                int to = i | (1 << zeros[j]) | (1 << zeros[k]);
                graph_added[i].emplace_back(to, 4);
                graph_added[to].emplace_back(i, 4);
            }
        }
    }

    vector<int> dists(v, MAXD);
    vector<int> edges(v, 0);
    dists[0] = 0;
    edges[0] = 0;
    queue<int> steps[MAXD + 1];
    steps[0].push(0);
    int d = 0;
    while (true) {
        int cur = -1;
        while (d <= MAXD) {
            if (steps[d].empty()) {
                d++;
            } else {
                cur = steps[d].front();
                steps[d].pop();
                break;
            }
        }
        if (cur == -1) {
            break;
        }
        if (d > dists[cur]) {
            continue;
        }
        for (auto [to, w] : graph[cur]) {
            if (d + w < dists[to]) {
                dists[to] = d + w;
                edges[to] = edges[cur];
                steps[d + w].push(to);
            } else if (d + w == dists[to] && edges[cur] < edges[to]) {
                edges[to] = edges[cur];
            }
        }
        for (auto [to, w] : graph_added[cur]) {
            if (d + w < dists[to]) {
                dists[to] = d + w;
                edges[to] = edges[cur] + 1;
                steps[d + w].push(to);
            } else if (d + w == dists[to] && edges[cur] + 1 < edges[to]) {
                edges[to] = edges[cur] + 1;
            }
        }
    }
    cout << dists[v - 1] << "\n";
    cout << edges[v - 1] << "\n";
}