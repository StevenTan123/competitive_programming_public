#include <bits/stdc++.h>
using namespace std;

const long long INF = 4e18;

// capacity is an adjacency matrix storing the capacity of each directed edge, 
// network is the undirected version of the directed graph 
int bfs(int s, int t, vector<int> network[], vector<vector<long long>> &capacity, vector<int>& parent) {
    fill(parent.begin(), parent.end(), -1);
    parent[s] = -2;
    queue<pair<int, long long>> q;
    q.push({s, INF});
 
    while (!q.empty()) {
        int cur = q.front().first;
        long long flow = q.front().second;
        q.pop();
 
        for (int next : network[cur]) {
            if (parent[next] == -1 && capacity[cur][next]) {
                parent[next] = cur;
                long long new_flow = min(flow, capacity[cur][next]);
                if (next == t) {
                    return new_flow;
                }
                q.push({next, new_flow});
            }
        }
    }
    return 0;
}
 
long long maxflow(int n, int s, int t, vector<int> network[], vector<vector<long long>> &capacity) {
    long long flow = 0;
    vector<int> parent(n);
    long long new_flow;
 
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

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int G, C, T;
    cin >> G >> C >> T;
    long long c[C];
    for (int i = 0; i < C; i++) {
        cin >> c[i];
    }
    long long g[G];
    for (int i = 0; i < G; i++) {
        cin >> g[i];
    }
    long long t[T];
    for (int i = 0; i < T; i++) {
        cin >> t[i];
    }
    for (int i = 0; i < C; i++) {
        for (int j = 0; j < G; j++) {
            int aij;
            cin >> aij;
            c[i] -= aij * g[j];
        }
    }

    // 0: source node, 1 to C: cake nodes, C+1 to C+T: tool nodes, C+T+1: sink node
    int N = C + T + 2;
    vector<int> graph[N];
    vector<vector<long long>> capacity(N, vector<long long>(N));
    
    long long cake_total = 0;
    for (int i = 0; i < C; i++) {
        c[i] = max(c[i], 0ll);
        graph[0].push_back(i + 1);
        capacity[0][i + 1] = c[i];
        cake_total += c[i];
    }
    
    for (int i = 0; i < C; i++) {
        int ni;
        cin >> ni;
        for (int j = 0; j < ni; j++) {
            int bij;
            cin >> bij;
            graph[i + 1].push_back(C + bij);
            graph[C + bij].push_back(i + 1);
            capacity[i + 1][C + bij] = INF;
        }
    }

    for (int i = 0; i < T; i++) {
        graph[C + i + 1].push_back(N - 1);
        capacity[C + i + 1][N - 1] = t[i];
    }

    long long res = maxflow(N, 0, N - 1, graph, capacity);
    cout << cake_total - res << "\n";
}