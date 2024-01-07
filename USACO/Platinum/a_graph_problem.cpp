#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;
const int MAXN = 200005;
long long pow10[MAXN];
int p;

pair<int, long long> merge_hashes(pair<int, long long> a, pair<int, long long> b) {
    return {a.first + b.first, (a.second * pow10[b.first] + b.second) % MOD};
}

int find(int par[], pair<int, long long> add[], int v) {
    if (par[v] == v) {
        return v;
    }
    int root = find(par, add, par[v]);
    add[v] = merge_hashes(add[v], add[par[v]]);
    par[v] = root;
    return root;
}

void merge(int par[], pair<int, long long> add[], int u, int v, int e) {
    int rootu = find(par, add, u);
    int rootv = find(par, add, v);
    if (rootu == rootv) {
        return;
    }
    pair<int, long long> addv = add[v];
    pair<int, long long> addu = add[u];
    add[rootu] = merge_hashes(add[rootu], {1, e});
    add[rootu] = merge_hashes(add[rootu], addv);
    add[rootv] = merge_hashes(add[rootv], {1, e});
    add[rootv] = merge_hashes(add[rootv], addu);
    par[rootu] = p;
    par[rootv] = p;
    p++;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    pow10[0] = 1;
    for (int i = 1; i < MAXN; i++) {
        pow10[i] = pow10[i - 1] * 10 % MOD;
    }

    int N, M;
    cin >> N >> M;
    int par[2 * N];
    // add[i] stores {# edges, hash of edges} of node in DSU
    pair<int, long long> add[2 * N];
    for (int i = 0; i < 2 * N; i++) {
        par[i] = i;
    }
    
    p = N;
    for (int i = 1; i <= M; i++) {
        int u, v;
        cin >> u >> v; u--; v--;
        merge(par, add, u, v, i);
    }

    for (int i = 0; i < N; i++) {
        find(par, add, i);
        cout << add[i].second << endl;
    }
}
