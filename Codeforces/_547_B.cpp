#include <bits/stdc++.h>
using namespace std;

int find(int par[], int node) {
    if (par[node] == node) {
        return node;
    }
    par[node] = find(par, par[node]);
    return par[node];
}

void merge(int par[], int sizes[], int a, int b) {
    int roota = find(par, a);
    int rootb = find(par, b);
    if (sizes[a] > sizes[b]) {
        par[rootb] = roota;
        sizes[roota] += sizes[rootb];
    } else {
        par[roota] = rootb;
        sizes[rootb] += sizes[roota];
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    pair<int, int> a[n];
    int par[n];
    int sizes[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i].first;
        a[i].second = i;
        par[i] = -1;
        sizes[i] = 1;
    }
    sort(a, a + n);

    int res[n + 1] = {0};
    int max_size = 0;
    for (int i = n - 1; i >= 0; i--) {
        int ind = a[i].second;
        par[ind] = ind;
        if (ind > 0 && par[ind - 1] != -1) {
            merge(par, sizes, ind, ind - 1);
        }
        if (ind < n - 1 && par[ind + 1] != -1) {
            merge(par, sizes, ind, ind + 1);
        }
        max_size = max(max_size, sizes[find(par, ind)]);
        res[max_size] = max(res[max_size], a[i].first);
    }
    for (int i = n - 1; i >= 1; i--) {
        res[i] = max(res[i], res[i + 1]);
    }
    for (int i = 1; i <= n; i++) {
        cout << res[i] << " ";
    }
    cout << endl;
}