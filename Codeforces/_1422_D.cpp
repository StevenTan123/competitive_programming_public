#include <bits/stdc++.h>
using namespace std;

void build_graph(vector<tuple<int, int, int>> &points, vector<pair<int, int>> graph[]) {
    for (int i = 0; i < points.size(); i++) {
        int cur = get<2>(points[i]);
        if (i > 0) {
            int prev = get<2>(points[i - 1]);
            int dist = min(abs(get<0>(points[i]) - get<0>(points[i - 1])), abs(get<1>(points[i]) - get<1>(points[i - 1])));
            graph[cur].push_back({prev, dist});
        }
        if (i < points.size() - 1) {
            int next = get<2>(points[i + 1]);
            int dist = min(abs(get<0>(points[i]) - get<0>(points[i + 1])), abs(get<1>(points[i]) - get<1>(points[i + 1])));
            graph[cur].push_back({next, dist});
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    pair<int, int> s, f;
    cin >> s.first >> s.second >> f.first >> f.second;
    pair<int, int> points[m];
    vector<tuple<int, int, int>> by_x;
    vector<tuple<int, int, int>> by_y;
    for (int i = 0; i < m; i++) {
        cin >> points[i].first >> points[i].second;
        by_x.push_back({points[i].first, points[i].second, i});
        by_y.push_back({points[i].second, points[i].first, i});
    }
    sort(by_x.begin(), by_x.end());
    sort(by_y.begin(), by_y.end());

    vector<pair<int, int>> graph[m];
    build_graph(by_x, graph);
    build_graph(by_y, graph);

    priority_queue<pair<int, int>> pq;
    for (int i = 0; i < m; i++) {
        int dist = min(abs(s.first - points[i].first), abs(s.second - points[i].second));
        pq.push({-dist, i});
    }
    vector<int> dists(m, INT_MAX);
    int res = abs(f.first - s.first) + abs(f.second - s.second);
    while (!pq.empty()) {
        pair<int, int> cur = pq.top();
        pq.pop();
        int dist = -cur.first;
        int ind = cur.second;
        if (dists[ind] != INT_MAX) {
            continue;
        }
        dists[ind] = dist;
        res = min(res, dist + abs(f.first - points[ind].first) + abs(f.second - points[ind].second));
        for (pair<int, int> nei : graph[ind]) {
            pq.push({-(dist + nei.second), nei.first});
        }
    }
    cout << res << endl;
}