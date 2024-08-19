#include <bits/stdc++.h>
using namespace std;

const int MAXN = 100005;
const int K = 21;
int log_two[MAXN];

struct SparseTable {
    int N;
    int **mx;
    SparseTable(int n, int *a) : N(n) {
        mx = new int*[N];
        for (int i = 0; i < N; i++) {
            mx[i] = new int[K + 1];
            mx[i][0] = a[i];
        }
        for (int j = 1; j <= K; j++) {
            for(int i = 0; i + (1 << j) <= N; i++) {
                mx[i][j] = max(mx[i][j - 1], mx[i + (1 << (j - 1))][j - 1]);
            }
        }
    }
    ~SparseTable() {
        for (int i = 0; i < N; i++) delete[] mx[i];
        delete[] mx;
    }
    int rmq(int l, int r) {
        int j = log_two[r - l + 1];
        return max(mx[l][j], mx[r - (1 << j) + 1][j]);
    }
};

int find(vector<int> &par, int node) {
    if (par[node] == node) {
        return node;
    }
    par[node] = find(par, par[node]);
    return par[node];
}

void merge(vector<int> &par, vector<int> &sizes, vector<set<int>> &sets, vector<int> &first_connect, int edge, int a, int b) {
    a = find(par, a);
    b = find(par, b);
    if (a != b) {
        if (sizes[a] > sizes[b]) {
            swap(a, b);
        }
        for (int node : sets[a]) {
            if (sets[b].count(node + 1)) {
                first_connect[node] = edge;
            }
            if (sets[b].count(node - 1)) {
                first_connect[node - 1] = edge;
            }
        }
        for (int node : sets[a]) {
            sets[b].insert(node);
        }
        par[a] = b;
        sizes[b] += sizes[a];
    }
}

void solve() {
    int n, m, q;
    cin >> n >> m >> q;

    vector<int> par(n);
    // sets[i] stores all nodes in component i.
    vector<set<int>> sets(n);
    for (int i = 0; i < n; i++) {
        par[i] = i;
        sets[i].insert(i);
    }
    vector<int> sizes(n, 1);
    vector<int> first_connect(n);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        merge(par, sizes, sets, first_connect, i, u, v);
    }

    SparseTable st(n, first_connect.data());
    for (int i = 0; i < q; i++) {
        int l, r;
        cin >> l >> r;
        l--; r--;
        if (l == r) {
            cout << "0 ";
        } else {
            int max_edge = st.rmq(l, r - 1);
            cout << max_edge + 1 << " ";
        }
    }
    cout << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    log_two[1] = 0;
    for (int i = 2; i < MAXN; i++) {
        log_two[i] = log_two[i / 2] + 1;
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}