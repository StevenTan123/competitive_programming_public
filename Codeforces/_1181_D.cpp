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

// finds smallest index i where bit.psum(i) >= val
int bsearch(int n, BIT bit, int val) {
    int l = 0;
    int r = n - 1;
    int res = -1;
    while (l <= r) {
        int m = (l + r) / 2;
        if (bit.psum(m) >= val) {
            res = m;
            r = m - 1;
        } else {
            l = m + 1;
        }
    }
    return res;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, m, q;
    cin >> n >> m >> q;
    int cnt[m] = {0};
    for (int i = 0; i < n; i++) {
        int city;
        cin >> city;
        cnt[city - 1]++;
    }
    pair<long long, int> qs[q];
    for (int i = 0; i < q; i++) {
        long long qy;
        cin >> qy;
        qs[i] = {qy - n, i};
    }
    sort(qs, qs + q);

    pair<int, int> sorted[m];
    for (int i = 0; i < m; i++) {
        sorted[i] = {cnt[i], i};
    }
    sort(sorted, sorted + m);
    
    BIT bit(m);
    int p = 0;
    long long years = 0;
    int res[q];
    for (int i = 0; i <= m; i++) {
        if (i == m || (i > 0 && sorted[i].first > sorted[i - 1].first)) {
            long long pyears = years;
            if (i == m) {
                years = LONG_LONG_MAX;
            } else {
                years += (long long) i * (sorted[i].first - sorted[i - 1].first);
            }
            while (p < q && qs[p].first <= years) {
                int val = (qs[p].first - pyears - 1) % i + 1;
                int ind = bsearch(m, bit, val);
                res[qs[p].second] = ind + 1;
                p++;
            }
        }
        if (i < m) {
            bit.update(sorted[i].second, 1);
        }
    }

    for (int i = 0; i < q; i++) {
        cout << res[i] << "\n";
    }
}