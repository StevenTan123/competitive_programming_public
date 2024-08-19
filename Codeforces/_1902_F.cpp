#include <bits/stdc++.h>
using namespace std;

const int MAXLOG = 20;
const int DIM = 25;

struct LCA {
    int n, timer;
    int *depth, *tin, *tout, **up;

    LCA(int _n, vector<int> tree[]) : n(_n), timer(0) {
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        up = new int*[MAXLOG];
        for (int i = 0; i < MAXLOG; i++) {
            up[i] = new int[n];
        }
        dfs(tree, 0, -1);
        compute_up();
    }

    ~LCA() {
        delete[] depth; delete[] tin; delete[] tout;
        for (int i = 0; i < MAXLOG; i++) delete[] up[i];
        delete[] up;
    }

    void dfs(vector<int> tree[], int cur, int prev) {
        if (prev == -1) {
            depth[cur] = 0;
            up[0][cur] = 0;
        } else {
            depth[cur] = depth[prev] + 1;
            up[0][cur] = prev;
        }
        tin[cur] = timer++;
        for (int nei : tree[cur]) {
            if (nei != prev) {
                dfs(tree, nei, cur);
            }
        }
        tout[cur] = timer++;
    }

    void compute_up() {
        for (int i = 1; i < MAXLOG; i++) {
            for (int j = 0; j < n; j++) {
                up[i][j] = up[i - 1][up[i - 1][j]];
            }
        }
    }

    bool is_ancestor(int a, int b) {
        return tin[a] <= tin[b] && tout[a] >= tout[b];
    }

    int lca(int a, int b) {
        if (depth[a] > depth[b]) {
            swap(a, b);
        }
        if (is_ancestor(a, b)) {
            return a;
        }
        for (int i = MAXLOG - 1; i >= 0; i--) {
            if (!is_ancestor(up[i][a], b)) {
                a = up[i][a];
            }
        }
        return up[0][a];
    }
    
    int dist(int a, int b) {
        int c = lca(a, b);
        return (depth[a] - depth[c]) + (depth[b] - depth[c]);
    }
};

struct Basis {
    vector<int> basis;
    vector<int> orig_vecs;
    Basis() : basis(DIM, -1) {}

    int lowest_bit(int vec) {
        for (int i = 0; i < DIM; i++) {
            if (vec & (1 << i)) {
                return i;
            }
        }
        return DIM;
    }
    
    // Tries to write vec as a linear combination of existing basis vectors. If it can, 
    // it returns 0. Otherwise, it returns the vector that should be added to the basis. 
    int check_vector(int vec) {
        for (int i = 0; i < DIM; i++) {
            if (vec & (1 << i)) {
                if (basis[i] == -1) {
                    return vec;
                } else {
                    vec ^= basis[i];
                }
            }
        }
        return 0;
    }

    void add_vector(int vec, int tree_ind) {
        int add = check_vector(vec);
        if (add != 0) {
            basis[lowest_bit(add)] = add;
            orig_vecs.push_back(tree_ind);
        }
    }
    
    bool spans(int vec) {
        return check_vector(vec) == 0;
    }
};

void gen_bases(vector<int> tree[], int a[], Basis bases[], int cur, int prev) {
    for (int nei : tree[cur]) {
        if (nei != prev) {
            bases[nei].add_vector(a[nei], nei);
            for (int ind : bases[cur].orig_vecs) {
                bases[nei].add_vector(a[ind], ind);
            }
            gen_bases(tree, a, bases, nei, cur);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    vector<int> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        tree[u].push_back(v);
        tree[v].push_back(u);
    }

    Basis bases[n];
    bases[0].add_vector(a[0], 0);
    gen_bases(tree, a, bases, 0, -1);

    int q;
    cin >> q;
    LCA lca_struct(n, tree);
    for (int i = 0; i < q; i++) {
        int x, y, k;
        cin >> x >> y >> k;
        x--; y--;

        int lca = lca_struct.lca(x, y);
        Basis combined;
        for (int ind : bases[x].orig_vecs) {
            if (lca_struct.depth[ind] < lca_struct.depth[lca]) {
                break;
            }
            combined.add_vector(a[ind], ind);
        }
        for (int ind : bases[y].orig_vecs) {
            if (lca_struct.depth[ind] < lca_struct.depth[lca]) {
                break;
            }
            combined.add_vector(a[ind], ind);
        }
        if (combined.spans(k)) {
            cout << "YES\n";
        } else {
            cout << "NO\n";
        }
    }
}