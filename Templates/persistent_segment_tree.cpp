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

    int get_sum(int lq, int rq) {
        if (lq <= left && right <= rq)
            return sum;
        if (max(left, lq) >= min(right, rq))
            return 0;
        extend();
        return left_child->get_sum(lq, rq) + right_child->get_sum(lq, rq);
    }
};
