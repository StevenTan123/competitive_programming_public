#include <bits/stdc++.h>
using namespace std;

const int ALPH = 26;
const int ALPH2 = ALPH * ALPH;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string s;
    cin >> s;
    int n = s.length();
    int N = (int) s.length() - 1 + ALPH2;
    vector<pair<int, bool>> graph[N];
    for (int i = 0; i < (int) s.length() - 1; i++) {
        if (i < (int) s.length() - 2) {
            graph[i].emplace_back(i + 1, 1);
            graph[i + 1].emplace_back(i, 1);
        }
        int dummy = n - 1 + ALPH * (s[i] - 'a') + (s[i + 1] - 'a');
        graph[i].emplace_back(dummy, 1);
        graph[dummy].emplace_back(i, 0);
    }   

    vector<vector<int>> dists;
    for (int i = 0; i < ALPH2; i++) {
        vector<int> cdists(N, INT_MAX);
        deque<pair<int, int>> bfs;
        bfs.emplace_back(n - 1 + i, 0);
        while (!bfs.empty()) {
            auto [cur, dist] = bfs.front();
            bfs.pop_front();
            if (cdists[cur] != INT_MAX) {
                continue;
            }
            cdists[cur] = dist;
            for (auto [nei, weight] : graph[cur]) {
                if (weight) {
                    bfs.emplace_back(nei, dist + 1);
                } else {
                    bfs.emplace_front(nei, dist);
                }
            }
        }
        dists.push_back(move(cdists));
    }

    int m;
    cin >> m;
    for (int i = 0; i < m; i++) {
        int f, t;
        cin >> f >> t;
        f--; t--;
        int dist = abs(f - t);
        for (int j = 0; j < ALPH2; j++) {
            if (dists[j][f] == INT_MAX || dists[j][t] == INT_MAX) {
                continue;
            }
            int cdist = dists[j][f] + dists[j][t] + 1;
            dist = min(dist, cdist);
        }
        cout << dist << "\n";
    }
}