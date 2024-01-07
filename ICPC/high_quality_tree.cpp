#include <bits/stdc++.h>
using namespace std;

const int MAXN = 200005;
int res = 0;

void dfs_tree(vector<int> tree[], int BST[][2], int cur, int prev) {
    int p = 0;
    for (int nei : tree[cur]) {
        if (nei != prev) {
            BST[cur][p] = nei;
            dfs_tree(tree, BST, nei, cur);
            p++;
        }
    }
    for (int i = p; i < 2; i++) {
        BST[cur][i] = -1;
    }
}

deque<int>* dfs(int BST[][2], int cur) {
    if (cur == -1) {
        return new deque<int>;
    }
    deque<int>* l = dfs(BST, BST[cur][0]);
    deque<int>* r = dfs(BST, BST[cur][1]);
    if (l->size() > r->size()) {
        swap(l, r);
    }

    int rem = 0;
    int nrem = r->size() - l->size() - 1;
    for (int i = 0; i < nrem; i++) {
        if (i == nrem - 1) {
            rem = r->front();
        }
        r->pop_front();
    }
    res += rem;
    auto itr = r->begin();
    if (r->size() == l->size() + 1) {
        (*itr) -= rem;
        itr++;
    }
    auto itl = l->begin();
    for (; itl != l->end(); itl++) {
        (*itr) += (*itl) - rem;
        itr++;
    }
    delete l;
    int last = 0;
    if (r->size() > 0) {
        last = r->back();
    }
    r->push_back(last + 1);
    return r;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    vector<int> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        tree[--u].push_back(--v);
        tree[v].push_back(u);
    }

    int BST[n][2];
    dfs_tree(tree, BST, 0, -1);

    res = 0;
    deque<int>* last = dfs(BST, 0);
    delete last;
    cout << res << endl;
}