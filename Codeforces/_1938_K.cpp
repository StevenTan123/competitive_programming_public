#include <bits/stdc++.h>
using namespace std;

const int MAXLOG = 18;

struct BinLift {
    int n, root, timer;
    int *depth, *tin, *tout, **up;

    BinLift(int _n, int _root, vector<vector<int>> &tree) : n(_n), root(_root), timer(0) {
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        up = new int*[MAXLOG];
        for (int i = 0; i < MAXLOG; i++) {
            up[i] = new int[n];
        }
        dfs(tree, root, -1);
        compute_up();
    }

    void dfs(vector<vector<int>> &tree, int cur, int prev) {
        if (prev == -1) {
            depth[cur] = 0;
            up[0][cur] = root;
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

    // Is a an ancestor of b
    bool is_ancestor(int a, int b) {
        return tin[a] <= tin[b] && tout[a] >= tout[b];
    }

    // Finds which child of b that a is a descendant of
    int find_dir(int a, int b) {
        for (int i = MAXLOG - 1; i >= 0; i--) {
            if (!is_ancestor(up[i][a], b)) {
                a = up[i][a];
            }
        }
        return a;
    }
};

struct Vertex {
    int left, right;
    int sum = 0;
    Vertex *left_child = nullptr, *right_child = nullptr;

    Vertex(int lb, int rb) {
        left = lb;
        right = rb;
    }

    void extend() {
        if (!left_child && left + 1 < right) {
            int t = (left + right) / 2;
            left_child = new Vertex(left, t);
            right_child = new Vertex(t, right);
        }
    }

    Vertex *update(int ind, int new_val) {
        extend();
        Vertex *ret = new Vertex(left, right);
        if (left + 1 == right) {
            ret->sum = new_val;
        } else {
            int t = (left + right) / 2;
            if (ind < t) {
                ret->left_child = left_child->update(ind, new_val);
                ret->right_child = right_child;
            } else {
                ret->left_child = left_child;
                ret->right_child = right_child->update(ind, new_val);

            }
            ret->sum = ret->left_child->sum + ret->right_child->sum;
        }
        return ret;
    }

    int get_sum(int lq, int rq) {
        if (lq <= left && right <= rq)
            return sum;
        if (max(left, lq) >= min(right, rq))
            return 0;
        extend();
        return left_child->get_sum(lq, rq) + right_child->get_sum(lq, rq);
    }
};

int walk(Vertex *one, Vertex *two, int x) {
    if (one->left + 1 == one->right) {
        return one->left;
    }
    one->extend();
    int left = one->left_child->sum;
    if (two) {
        two->extend();
        left -= two->left_child->sum;
    }
    if (left >= x) {
        return walk(one->left_child, (two ? two->left_child : nullptr), x);
    } else {
        return walk(one->right_child, (two ? two->right_child : nullptr), x - left);
    }
}

void dfs_sizes(vector<vector<int>> &tree, vector<int> &sizes, int cur) {
    sizes[cur] = 1;
    for (int child : tree[cur]) {
        dfs_sizes(tree, sizes, child);
        sizes[cur] += sizes[child];
    }
}

void dfs_pst(vector<vector<int>> &tree, vector<int> &sizes, vector<Vertex *> &pst, int cur, int prev) {
    if (prev != -1) {
        pst[cur] = pst[prev]->update(prev, sizes[prev] - sizes[cur]);
    }
    pst[cur] = pst[cur]->update(cur, sizes[cur]);
    for (int child : tree[cur]) {
        dfs_pst(tree, sizes, pst, child, cur);
    }
}

Vertex *dfs_mark(vector<vector<int>> &tree, Vertex *pst, int cur) {
    pst = pst->update(cur, 1);
    for (int child : tree[cur]) {
        pst = dfs_mark(tree, pst, child);
    }
    return pst;
}

void dfs_pst2(vector<vector<int>> &tree, vector<int> &sizes, vector<Vertex *> &pst, int cur) {
    if (tree[cur].size() == 0) {
        pst[cur] = new Vertex(0, tree.size());
        pst[cur] = pst[cur]->update(cur, 1);
    } else {
        int max_child = -1;
        for (int child : tree[cur]) {
            dfs_pst2(tree, sizes, pst, child);
            if (max_child == -1 || sizes[child] > sizes[max_child]) {
                max_child = child;
            }
        }
        pst[cur] = pst[max_child]->update(cur, 1);

        for (int child : tree[cur]) {
            if (child != max_child) {
                pst[cur] = dfs_mark(tree, pst[cur], child);
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, q;
    cin >> n >> q;
    vector<vector<int>> tree(n);
    int root = -1;
    for (int i = 0; i < n; i++) {
        int p;
        cin >> p;
        if (p > 0) {
            tree[p - 1].push_back(i);
        } else {
            root = i;
        }
    }

    vector<int> sizes(n);
    dfs_sizes(tree, sizes, root);

    vector<Vertex *> pst(n);
    pst[root] = new Vertex(0, tree.size());
    pst[root] = pst[root]->update(root, n);
    dfs_pst(tree, sizes, pst, root, -1);

    vector<Vertex *> pst2(n);
    dfs_pst2(tree, sizes, pst2, root);

    BinLift bl(n, root, tree);

    for (int i = 0; i < q; i++) {
        long long k;
        cin >> k;

        int x = (k - 1) / n;
        k -= (long long) x * n;
        
        int y = walk(pst[x], nullptr, k);
        k -= pst[x]->get_sum(0, y);
    
        int child = (x == y ? -1 : bl.find_dir(x, y));
        int z = walk(pst2[y], (child == -1 ? nullptr : pst2[child]), k);

        long long res = (long long) n * n * x + (long long) n * y + z;
        cout << res << "\n";
    }
}