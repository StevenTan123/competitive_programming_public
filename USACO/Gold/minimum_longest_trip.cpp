#include <bits/stdc++.h>
using namespace std;

const int MAXN = 200005;

struct Edge {
    int v, l;
    Edge() {}
    Edge(int vv, int ll) {
        v = vv;
        l = ll;
    }
};

// dp[i] = longest path starting from i
// next_edge[i] = the first edge of the longest path from i
// tot[i] = total sum of edge values of lexographically smallest longest path starting from i
int dp[MAXN];
long long tot[MAXN];
Edge next_edge[MAXN];

// sort_inds[i] is a map that maps nodes with longest path i to their index in the sorted list
map<int, int> sort_inds[MAXN];

struct Node {
    int v;
    Node() {}
    Node(int vv) {
        v = vv;
    }
    bool operator<(const Node &o) const {
        if (next_edge[v].v == v) {
            return false;
        }
        if (dp[v] == 0) {
            return false;
        }
        if (next_edge[v].l < next_edge[o.v].l) {
            return true;
        } else if (next_edge[v].l > next_edge[o.v].l) {
            return false;
        }
        int vv = next_edge[v].v;
        int ov = next_edge[o.v].v;
        int ind1 = sort_inds[dp[v] - 1][vv];
        int ind2 = sort_inds[dp[v] - 1][ov];
        return ind1 < ind2;
    }
};

void dfs_topo(vector<Edge> DAG[], vector<int> &order, bool visited[], int cur) {
    visited[cur] = true;
    for (Edge e : DAG[cur]) {
        if (!visited[e.v]) {
            dfs_topo(DAG, order, visited, e.v);
        }
    }
    order.push_back(cur);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, M;
    cin >> N >> M;
    
    vector<Edge> DAG[N];
    for (int i = 0; i < M; i++) {
        int a, b, l;
        cin >> a >> b >> l;
        DAG[--a].push_back(Edge(--b, l));
    }

    bool visited[N] = {0};
    vector<int> order;
    for (int i = 0; i < N; i++) {
        if (!visited[i]) {
            dfs_topo(DAG, order, visited, i);
        }
    }
    reverse(order.begin(), order.end());

    pair<int, int> sort_len[N];
    for (int i = N - 1; i >= 0; i--) {
        int cur = order[i];
        dp[cur] = 0;
        for (Edge e : DAG[cur]) {
            dp[cur] = max(dp[cur], dp[e.v] + 1);
        }
        sort_len[i] = {dp[cur], cur};
    }
    sort(sort_len, sort_len + N);

    // sorted[i] contains all nodes with longest path i, sorted lexographically
    vector<Node> sorted[MAXN];
    for (int i = 0; i < N; i++) {
        int len = sort_len[i].first;
        int cur = sort_len[i].second;
        sorted[len].push_back(Node(cur));

        if (i > 0 && len > sort_len[i - 1].first) {
            sort(sorted[len - 1].begin(), sorted[len - 1].end());
            for (int j = 0; j < sorted[len - 1].size(); j++) {
                sort_inds[len - 1][sorted[len - 1][j].v] = j;
            }
        }

        Edge best(cur, 0);
        long long edge_tot = 0;
        for (Edge e : DAG[cur]) {
            if (dp[e.v] + 1 == dp[cur]) {
                if (best.v == cur) {
                    best = e;
                    edge_tot = e.l + tot[e.v];
                } else if (e.l < best.l) {
                    best = e;
                    edge_tot = e.l + tot[e.v];
                } else if (e.l == best.l) {
                    int ind1 = sort_inds[dp[e.v]][e.v];
                    int ind2 = sort_inds[dp[e.v]][best.v];
                    if (ind1 < ind2) {
                        best = e;
                        edge_tot = e.l + tot[e.v];
                    }
                }
            }
        }
        next_edge[cur] = best;
        tot[cur] = edge_tot;
    }

    for (int i = 0; i < N; i++) {
        cout << dp[i] << " " << tot[i] << "\n";
    }
}