#include <bits/stdc++.h>
using namespace std;

const int MAXN = 200005;
map<long long, bool> comp;

struct Edge {
    int v, l;
    Edge() {}
    Edge(int vv, int ll) {
        v = vv;
        l = ll;
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

// Returns true if best path starting at v lexographically smaller than best path starting at u
bool comp_lexo(int v, int u, Edge next[]) {
    long long hash = (long long) v * MAXN + u;
    if (comp.count(hash)) {
        return comp[hash];
    }

    bool ret = false;
    if (v == u || next[v].v == v) {
        ret = false;
    } else if (next[v].l < next[u].l) {
        ret = true;
    } else if (next[v].l > next[u].l) {
        ret = false;
    } else {
        ret = comp_lexo(next[v].v, next[u].v, next);
    }

    long long rev_hash = (long long) u * MAXN + v;
    comp[hash] = ret;
    comp[rev_hash] = !ret;
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, M;
    cin >> N >> M;
    
    vector<Edge> DAG[N];
    int prevl = -1;
    bool all_same_labels = true;
    for (int i = 0; i < M; i++) {
        int a, b, l;
        cin >> a >> b >> l;
        DAG[--a].push_back(Edge(--b, l));
        if (prevl == -1) {
            prevl = l;
        } else if (l != prevl) {
            all_same_labels = false;
        }
    }

    bool visited[N] = {0};
    vector<int> order;
    for (int i = 0; i < N; i++) {
        if (!visited[i]) {
            dfs_topo(DAG, order, visited, i);
        }
    }
    reverse(order.begin(), order.end());

    // dp[i] = longest path starting from i
    // next[i] = the first edge of the longest path from i
    // tot[i] = total sum of edge values of lexographically smallest longest path starting from i
    int dp[N];
    Edge next[N];
    long long tot[N];
    for (int i = N - 1; i >= 0; i--) {
        int cur = order[i];
        if (DAG[cur].empty()) {
            dp[cur] = 0;
            next[cur] = Edge(cur, 0);
            tot[cur] = 0;
        } else {
            int longest = 0;
            Edge best(-1, -1);
            long long edge_tot = 0;
            for (Edge e : DAG[cur]) {
                if (dp[e.v] + 1 > longest) {
                    longest = dp[e.v] + 1;
                    best = e;
                    edge_tot = e.l + tot[e.v];
                } else if (!all_same_labels && dp[e.v] + 1 == longest) {
                    if (e.l < best.l) {
                        best = e;
                        edge_tot = e.l + tot[e.v];
                    } else if (e.l == best.l) {
                        if (comp_lexo(e.v, best.v, next)) {
                            best = e;
                            edge_tot = e.l + tot[e.v];
                        }
                    }
                }
            }
            dp[cur] = longest;
            next[cur] = best;
            tot[cur] = edge_tot;
        }
    }

    for (int i = 0; i < N; i++) {
        cout << dp[i] << " " << tot[i] << "\n";
    }
}