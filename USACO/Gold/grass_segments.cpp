#include <bits/stdc++.h>
using namespace std;

struct BIT {
    int *bit;
    int len;
    BIT(int n) {
        bit = new int[n + 1];
        len = n + 1;
        for (int i = 0; i <= n; i++) {
            bit[i] = 0;
        }
    }
    ~BIT() {
        delete[] bit;
    }
    void update(int index, int add) {
        index++;
        while (index < len) {
            bit[index] += add;
            index += index & -index;
        }
    }
    int sum(int l, int r) {
        return psum(r) - (l == 0 ? 0 : psum(l - 1));
    }
    int psum(int index) {
        index++;
        int res = 0;
        while (index > 0) {
            res += bit[index];      
            index -= index & -index;
        }
        return res;
    }
};

struct Seg {
    int l, r, k, id;
    Seg() {}
    Seg(int ll, int rr, int kk, int i) : l(ll), r(rr), k(kk), id(i) {}
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N;
    vector<Seg> segs(N);
    vector<Seg> sort_by_k(N);
    map<int, int> compress;
    for (int i = 0; i < N; i++) {
        cin >> segs[i].l >> segs[i].r >> segs[i].k;
        segs[i].id = i;
        compress[segs[i].l] = 0;
        compress[segs[i].r] = 0;
        sort_by_k[i] = segs[i];
    }
    int cnt = 0;
    for (auto [coord, id] : compress) {
        compress[coord] = cnt++;
    }

    BIT left(cnt);
    BIT right(cnt);
    sort(segs.begin(), segs.end(), [](const Seg &a, const Seg &b) {
        return (a.r - a.l) > (b.r - b.l);
    });
    sort(sort_by_k.begin(), sort_by_k.end(), [](const Seg &a, const Seg &b) {
        return a.k > b.k;
    });

    int i = 0;
    int k_ind = 0;
    vector<int> res(N);
    while (i < N) {
        int j = i;
        while (j < N && segs[j].r - segs[j].l == segs[i].r - segs[i].l) {
            j++;
        }
        
        int len = segs[i].r - segs[i].l;
        while (k_ind < N && sort_by_k[k_ind].k > len) {
            Seg cur = sort_by_k[k_ind];
            auto left_bound = compress.upper_bound(cur.r - cur.k);
            auto right_bound = compress.lower_bound(cur.l + cur.k);
            right_bound = prev(right_bound);
            int sub_left = left.sum(left_bound->second, cnt - 1);
            int sub_right = right.sum(0, right_bound->second);
            res[cur.id] = i - sub_left - sub_right;
            k_ind++;
        }

        while (i < j) {
            Seg cur = segs[i];
            left.update(compress[cur.l], 1);
            right.update(compress[cur.r], 1);
            i++;
        }
    }
    while (k_ind < N) {
        Seg cur = sort_by_k[k_ind];
        auto left_bound = compress.upper_bound(cur.r - cur.k);
        auto right_bound = compress.lower_bound(cur.l + cur.k);
        right_bound = prev(right_bound);
        int sub_left = left.sum(left_bound->second, cnt - 1);
        int sub_right = right.sum(0, right_bound->second);
        res[cur.id] = i - sub_left - sub_right;
        k_ind++;
    }
    for (i = 0; i < N; i++) {
        cout << res[i] - 1 << "\n";
    }
}