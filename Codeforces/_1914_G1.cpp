#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

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

int find_rb(int c[], pair<int, int> inds[], int start) {
    int r = start;
    for (int i = start; i <= r; i++) {
        r = max(r, inds[c[i]].second);
    }
    return r;
}

int count_starts(int c[], pair<int, int> inds[], BIT bit, int l, int r) {
    bit.update(l, 1);
    bit.update(inds[c[l]].second, 1);
    int count = 2;
    for (int i = l; i <= r; i++) {
        for (int j = l; j <= r; j++) {
            if (bit.sum(j, j) == 0 && bit.sum(j, inds[c[j]].second) > 0) {
                bit.update(j, 1);
                bit.update(inds[c[j]].second, 1);
                count += 2;
            }
        }
    }
    return count;
}

void solve() {
    int n;
    cin >> n;
    int c[2 * n];
    pair<int, int> inds[n];
    for (int i = 0; i < n; i++) {
        inds[i] = {-1, -1};
    }
    for (int i = 0; i < 2 * n; i++) {
        cin >> c[i];
        c[i]--;
        if (inds[c[i]].first == -1) {
            inds[c[i]].first = i;
        } else {
            inds[c[i]].second = i;
        }
    }

    int rb[2 * n];
    BIT bit(2 * n);
    int count = 0;
    int ind = 0;
    long long prod = 1;
    while (ind < 2 * n) {
        rb[ind] = find_rb(c, inds, ind);
        for (int i = ind + 1; i <= rb[ind]; i++) {
            rb[i] = rb[ind];
        }
        
        int cur_ways = count_starts(c, inds, bit, ind, rb[ind]);
        prod *= cur_ways;
        prod %= MOD;

        ind = rb[ind] + 1;
        count++;
    }
    cout << count << " " << prod << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}