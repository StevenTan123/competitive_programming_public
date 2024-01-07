#include <bits/stdc++.h>
using namespace std;
 
int find(int par[], int node) {
    if (par[node] == node) {
        return node;
    }
    par[node] = find(par, par[node]);
    return par[node];
}
 
void merge(int par[], int val[], int l[], int r[], int a, int b) {
    a = find(par, a);
    b = find(par, b);
    if (a != b) {
        if (r[a] - l[a] > r[b] - l[b]) {
            swap(a, b);
        }
        par[a] = b;
        val[b] = min(val[a], val[b]);
        l[b] = min(l[b], l[a]);
        r[b] = max(r[b], r[a]);
    }
}
 
void solve() {
    int n;
    cin >> n;
    int a[n];
    pair<int, int> sorted[n];
    int par[n];
    int val[n];
    int l[n];
    int r[n];
    int zero_count = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        if (a[i] == 0) {
            zero_count++;
        }
        sorted[i] = {a[i], i};
        par[i] = i;
        val[i] = a[i];
        l[i] = i;
        r[i] = i;
    }
    sort(sorted, sorted + n);
 
    for (int i = n - 1; i >= 0; i--) {
        int cur = find(par, sorted[i].second);
        if (l[cur] > 0 && abs(val[find(par, l[cur] - 1)] - val[cur]) <= 1) {
            merge(par, val, l, r, cur, l[cur] - 1);
        } else if (r[cur] < n - 1 && abs(val[find(par, r[cur] + 1)] - val[cur]) <= 1) {
            merge(par, val, l, r, cur, r[cur] + 1);
        }
    }
 
    int prev = -1;
    bool possible = (zero_count == 1);
    for (int i = 0; i < n; i++) {
        int cur = find(par, i);
        if (prev == -1) {
            prev = cur;
        } else if (cur != prev) {
            possible = false;
            break;
        }
    }
    if (possible) {
        if (val[find(par, 0)] != 0) {
            possible = false;
        }
    }
    cout << (possible ? "YES\n" : "NO\n");
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