#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, M, C, R, K;
    cin >> N >> M >> C >> R >> K;
    vector<pair<int, int>> graph[N]; 
    for (int i = 0; i < M; i++) {
        int u, v, l;
        cin >> u >> v >> l;
        u--; v--;
        graph[u].push_back({v, l});
        graph[v].push_back({u, l});
    }
    
    set<int> vis[N]; 
    priority_queue<tuple<int, int, int>> pq;
    for (int i = 0; i < C; i++) {
        pq.push({0, i, i});
    }
    while (pq.size() > 0) {
        auto [dist, cur, start] = pq.top();
        pq.pop();
        dist *= -1;
        if (dist > R || vis[cur].size() >= K || vis[cur].count(start)) {
            continue;
        }
        vis[cur].insert(start);
        
        for (auto [nei, l] : graph[cur]) {
            pq.push({-(dist + l), nei, start});
        }
    }

    vector<int> res;
    for (int i = C; i < N; i++) {
        if (vis[i].size() >= K) {
            res.push_back(i);
        }
    }
    cout << res.size() << "\n"; 
    for (int dest : res) {
        cout << dest + 1 << "\n";
    }
}