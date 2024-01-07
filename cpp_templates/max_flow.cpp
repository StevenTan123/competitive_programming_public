#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;

// capacity is an adjacency matrix storing the capacity of each directed edge, 
// network is the undirected version of the directed graph 
int bfs(int s, int t, vector<int> network[], vector<vector<int>> &capacity, vector<int>& parent) {
    fill(parent.begin(), parent.end(), -1);
    parent[s] = -2;
    queue<pair<int, int>> q;
    q.push({s, INF});
 
    while (!q.empty()) {
        int cur = q.front().first;
        int flow = q.front().second;
        q.pop();
 
        for (int next : network[cur]) {
            if (parent[next] == -1 && capacity[cur][next]) {
                parent[next] = cur;
                int new_flow = min(flow, capacity[cur][next]);
                if (next == t) {
                    return new_flow;
                }
                q.push({next, new_flow});
            }
        }
    }
    return 0;
}
 
int maxflow(int n, int s, int t, vector<int> network[], vector<vector<int>> &capacity) {
    int flow = 0;
    vector<int> parent(n);
    int new_flow;
 
    while ((new_flow = bfs(s, t, network, capacity, parent))) {
        flow += new_flow;
        int cur = t;
        while (cur != s) {
            int prev = parent[cur];
            capacity[prev][cur] -= new_flow;
            capacity[cur][prev] += new_flow;
            cur = prev;
        }
    }
    return flow;
}