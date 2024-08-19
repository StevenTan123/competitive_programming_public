#include <bits/stdc++.h>
using namespace std;

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

    Vertex *update(int ind, int add) {
        extend();
        Vertex *ret = new Vertex(left, right);
        if (left + 1 == right) {
            ret->sum += add;
        } else {
            int t = (left + right) / 2;
            if (ind < t) {
                ret->left_child = left_child->update(ind, add);
                ret->right_child = right_child;
            } else {
                ret->left_child = left_child;
                ret->right_child = right_child->update(ind, add);

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

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    vector<int> a(n);
    vector<int> sorted(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        sorted[i] = a[i];
    }
    sort(sorted.begin(), sorted.end());

    map<int, int> compress;
    for (int i = 0; i < n; i++) {
        compress[sorted[i]] = i;
    }

    vector<Vertex *> pst(n);
    for (int i = 0; i < n; i++) {
        Vertex *prev = (i > 0 ? pst[i - 1] : new Vertex(0, n));
        pst[i] = prev->update(compress[a[i]], 1);
    }

    for (int i = 0; i < m; i++) {
        int l, r, k;
        cin >> l >> r >> k;
        int ind = walk(pst[r - 1], (l > 1 ? pst[l - 2] : nullptr), k);
        cout << sorted[ind] << "\n";
    }
}