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

void solve() {
    int n;
    cin >> n;
    int A[n][n];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> A[i][j];
        }
    }
    
    int par[2 * n];
    int sizes[2 * n];
    for (int i = 0; i < 2 * n; i++) {
        par[i] = i;
        sizes[i] = 1;
    }

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (A[i][j] == A[j][i]) {
                continue;
            }
            if (A[i][j] < A[j][i]) {
                if (find(par, i * 2) != find(par, j * 2 + 1)) {
                    merge(par, sizes, i * 2, j * 2);
                    merge(par, sizes, i * 2 + 1, j * 2 + 1);
                }
            } else {
                if (find(par, i * 2) != find(par, j * 2)) {
                    merge(par, sizes, i * 2, j * 2 + 1);
                    merge(par, sizes, i * 2 + 1, j * 2);
                }
            }
        }
    }

    int res[n][n];
    for (int i = 0; i < n; i++) {
        res[i][i] = A[i][i];
        for (int j = i + 1; j < n; j++) {
            if (find(par, i * 2) == find(par, j * 2)) {
                res[i][j] = A[i][j];
                res[j][i] = A[j][i];
            } else {
                res[i][j] = A[j][i];
                res[j][i] = A[i][j];
            }
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cout << res[i][j] << " ";
        }
        cout << "\n";
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