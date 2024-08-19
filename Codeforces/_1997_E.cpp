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

    int walk(int x) {
        extend();
        if (left + 1 == right) {
            if (x > sum) {
                return right;
            } else {
                return left;
            }
        } else {
            if (left_child->sum < x) {
                return right_child->walk(x - left_child->sum);
            } else {
                return left_child->walk(x);
            }
        }
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

int main() {
    int n, q;
    cin >> n >> q;
    vector<int> a(n);
    vector<pair<int, int>> sorted(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        sorted[i] = {a[i], i};
    }
    sort(sorted.begin(), sorted.end());

    vector<Vertex *> pst(n + 1);
    pst[n] = new Vertex(0, n);
    for (int i = n - 1; i >= 0; i--) {
        pst[i] = pst[i + 1]->update(sorted[i].second, 1);
    }

    vector<int> which(n + 1);
    int pst_p = 0;
    for (int i = 0; i <= n; i++) {
        while (pst_p < n && sorted[pst_p].first < i) {
            pst_p++;
        }
        which[i] = pst_p;
    }

    vector<vector<pair<int, int>>> queries(n + 1);
    for (int i = 0; i < q; i++) {
        int ind, k;
        cin >> ind >> k;
        queries[k].emplace_back(--ind, i);
    }
    for (int k = 1; k <= n; k++) {
        if (queries[k].size() > 1) {
            sort(queries[k].begin(), queries[k].end());
        }
    }

    vector<bool> res(q);
    for (int k = 1; k <= n; k++) {
        int level = 1;
        int ind = 0;
        int p = 0;
        while (ind < n) {
            int find_cnt = k + pst[which[level]]->get_sum(0, ind);
            int next = pst[which[level]]->walk(find_cnt);

            while (p < queries[k].size() && queries[k][p].first <= next) {    
                res[queries[k][p].second] = (level <= a[queries[k][p].first]);
                p++;
            }

            ind = next + 1;
            level++;
        }
    }

    for (int i = 0; i < q; i++) {
        cout << (res[i] ? "YES\n" : "NO\n");
    }
}