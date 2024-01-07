#include <bits/stdc++.h>
using namespace std;

struct BIT {
    int len;
    int *bit;
    BIT(int n) {
        bit = new int[n + 1];
        len = n + 1;
        for (int i = 0; i <= n; i++) {
            bit[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            update(i, 0);
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
        if (r < l) {
            return 0;
        }
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

void solve() {
    int n;
    cin >> n;
    int a[n];
    int dists[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i]--;
        dists[i] = a[i] - i;
        if (dists[i] < 0) {
            dists[i] += n;
        }
    }
    
    BIT marked(2 * n);
    int res[n];
    for (int i = 0; i < n; i++) {
        if (i + dists[i] < n) {
            marked.update(i + dists[i] + n, 1);
        }
    }
    for (int i = n - 1; i >= 0; i--) {
        res[a[i]] = dists[i] - marked.sum(i, i + dists[i] - 1);
        marked.update(i + dists[i], 1);
    }

    for (int i = 0; i < n; i++) {
        cout << res[i] << " ";
    }
    cout << endl;
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