#include <bits/stdc++.h>
using namespace std;

const int MAXC = 1000000005;

struct Vertex {
    int left, right;
    int sum = 0;
    bool lazy = false;
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
        if (lazy) {
            left_child->lazy = !left_child->lazy;
            left_child->sum = left_child->right - left_child->left - left_child->sum;
            right_child->lazy = !right_child->lazy;
            right_child->sum = right_child->right - right_child->left - right_child->sum;
            lazy = false;
        }
    }

    void toggle(int lq, int rq) {
        if (lq <= left && right <= rq) {
            lazy = !lazy;
            sum = right - left - sum;
        } else if (max(left, lq) >= min(right, rq)) {
            return;
        } else {
            extend();
            left_child->toggle(lq, rq);
            right_child->toggle(lq, rq);
            sum = left_child->sum + right_child->sum;
        }
    }

    int get_sum(int lq, int rq) {
        if (lq <= left && right <= rq) {
            return sum;
        }
        if (max(left, lq) >= min(right, rq)) {
            return 0;
        }
        extend();
        return left_child->get_sum(lq, rq) + right_child->get_sum(lq, rq);
    }
};

struct Seg {
    int x, lower, upper, ID;
    Seg(int xx, int l, int u, int i) {
        x = xx;
        lower = l;
        upper = u;
        ID = i;
    }
    bool operator<(const Seg &o) const {
        if (x < o.x) {
            return true;
        }
        if (ID < o.ID) {
            return true;
        }
        return false;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;

    set<Seg> sweep;
    for (int i = 0; i < n; i++) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        sweep.insert(Seg(x1, y1, y2, i));
        sweep.insert(Seg(x2, y1, y2, i));
    }

    Vertex st(0, MAXC);
    long long area = 0;
    for (auto it = sweep.begin(); it != sweep.end(); it++) {
        if (it != sweep.begin() && prev(it, 1)->x < it->x) {
            area += (long long) st.get_sum(0, MAXC) * (it->x - prev(it, 1)->x);
        }
        st.toggle(it->lower, it->upper);
    }
    cout << area << endl;
}