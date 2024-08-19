#include <bits/stdc++.h>
using namespace std;

struct BoolArr {
    vector<int> arr;
    int cnt;
    BoolArr(int n) : arr(n), cnt(0) {}
    void set(int ind, int val) {
        cnt += val - arr[ind];
        arr[ind] = val;
    } 
};

void solve() {
    int n, q;
    cin >> n >> q;
    vector<int> pars(n);
    vector<int> exp_space(n);
    exp_space[0] = n / 2;
    pars[0] = -1;
    for (int i = 1; i < n; i++) {
        cin >> pars[i];
        pars[i]--;
        exp_space[i] = exp_space[pars[i]] / 2;
    }
    vector<int> p(n);
    vector<int> loc(n);
    for (int i = 0; i < n; i++) {
        cin >> p[i];
        p[i]--;
        loc[p[i]] = i;
    }

    BoolArr before_child(n);
    for (int i = 0; i < n; i++) {
        if (i * 2 + 2 < n) {
            if (loc[i] + 1 == loc[i * 2 + 1] || loc[i] + 1 == loc[i * 2 + 2]) {
                before_child.set(i, 1);
            }
        } else {
            before_child.set(i, 1);
        }
    }

    BoolArr spaced(n);
    for (int i = 0; i < n; i++) {
        int c1 = i * 2 + 1;
        int c2 = i * 2 + 2;
        if (c2 < n) {
            if (abs(loc[c1] - loc[c2]) == exp_space[i]) {
                spaced.set(i, 1);
            }
        } else {
            spaced.set(i, 1);
        }
    }

    for (int i = 0; i < q; i++) {
        int x, y;
        cin >> x >> y;
        x--; y--;
        int xx = p[x];
        int yy = p[y];

        swap(p[x], p[y]);
        loc[p[x]] = x;
        loc[p[y]] = y;

        if (xx * 2 + 2 < n) {
            if (loc[xx] + 1 == loc[xx * 2 + 1] || loc[xx] + 1 == loc[xx * 2 + 2]) {
                before_child.set(xx, 1);
            } else {
                before_child.set(xx, 0);
            }
        }
        if (yy * 2 + 2 < n) {
            if (loc[yy] + 1 == loc[yy * 2 + 1] || loc[yy] + 1 == loc[yy * 2 + 2]) {
                before_child.set(yy, 1);
            } else {
                before_child.set(yy, 0);
            }
        }

        if (xx > 0) {
            int par = (xx - 1) / 2;
            int c1 = par * 2 + 1;
            int c2 = par * 2 + 2;
            if (abs(loc[c1] - loc[c2]) == exp_space[par]) {
                spaced.set(par, 1);
            } else {
                spaced.set(par, 0);
            }
            if (loc[par] + 1 == loc[c1] || loc[par] + 1 == loc[c2]) {
                before_child.set(par, 1);
            } else {
                before_child.set(par, 0);
            }
        }
        if (yy > 0) {
            int par = (yy - 1) / 2;
            int c1 = par * 2 + 1;
            int c2 = par * 2 + 2;
            if (abs(loc[c1] - loc[c2]) == exp_space[par]) {
                spaced.set(par, 1);
            } else {
                spaced.set(par, 0);
            }
            if (loc[par] + 1 == loc[c1] || loc[par] + 1 == loc[c2]) {
                before_child.set(par, 1);
            } else {
                before_child.set(par, 0);
            }
        }

        if (before_child.cnt == n && spaced.cnt == n) {
            cout << "YES\n";
        } else {
            cout << "NO\n";
        }
    }
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