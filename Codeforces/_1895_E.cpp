#include <bits/stdc++.h>
using namespace std;

bool comp_tuple(tuple<int, int, int> &left, tuple<int, int, int> &right) {
    return get<1>(left) < get<1>(right);
}

void calc_edges(tuple<int, int, int> a[], tuple<int, int, int> b[], int n, int m, int edges[]) {
    sort(a, a + n, comp_tuple);
    sort(b, b + m);

    int max_def[m];
    int max_def_val = 0;
    max_def[m - 1] = get<2>(b[m - 1]);
    max_def_val = get<1>(b[m - 1]);
    for (int i = m - 2; i >= 0; i--) {
        max_def[i] = max_def[i + 1];
        if (get<1>(b[i]) > max_def_val) {
            max_def_val = get<1>(b[i]);
            max_def[i] = get<2>(b[i]);
        }
    }

    int r = 0;
    for (int i = 0; i < n; i++) {
        while (r < m && get<0>(b[r]) <= get<1>(a[i])) {
            r++;
        }
        if (r < m) {
            edges[get<2>(a[i])] = max_def[r]; 
        } else {
            edges[get<2>(a[i])] = -1;
        }
    }
}

void dfs(int edges_n[], int edges_m[], int win_n[], int win_m[], int cur, int which) {
    if (which == 0) {
        if (win_n[cur] != 0) {
            if (win_n[cur] == -1) {
                win_n[cur] = 2;
            }
            return;
        }
        if (edges_n[cur] == -1) {
            win_n[cur] = 1;
            return;
        }
        win_n[cur] = -1;
        dfs(edges_n, edges_m, win_n, win_m, edges_n[cur], 1);
        win_n[cur] = win_m[edges_n[cur]];
    } else {
        if (win_m[cur] != 0) {
            if (win_m[cur] == -1) {
                win_m[cur] = 2;
            }
            return;
        }
        if (edges_m[cur] == -1) {
            win_m[cur] = 3;
            return;
        }
        win_m[cur] = -1;
        dfs(edges_n, edges_m, win_n, win_m, edges_m[cur], 0);
        win_m[cur] = win_n[edges_m[cur]];
    }
}

void solve() {
    int n;
    cin >> n;
    tuple<int, int, int> a[n];
    int edges_n[n];
    for (int i = 0; i < n; i++) {
        cin >> get<0>(a[i]);
    }
    for (int i = 0; i < n; i++) {
        cin >> get<1>(a[i]);
        get<2>(a[i]) = i;
    }

    int m;
    cin >> m;
    tuple<int, int, int> b[m];
    int edges_m[m];
    for (int i = 0; i < m; i++) {
        cin >> get<0>(b[i]);
    }
    for (int i = 0; i < m; i++) {
        cin >> get<1>(b[i]);
        get<2>(b[i]) = i;
    }

    calc_edges(a, b, n, m, edges_n);
    calc_edges(b, a, m, n, edges_m);

    // 1 is win, 2 is draw, 3 loss (all for Monocarp).
    int win_n[n] = {0};
    int win_m[m] = {0};
    int res[3] = {0};
    for (int i = 0; i < n; i++) {
        dfs(edges_n, edges_m, win_n, win_m, i, 0);
        res[win_n[i] - 1]++;
    }
    cout << res[0] << " " << res[1] << " " << res[2] << endl;
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