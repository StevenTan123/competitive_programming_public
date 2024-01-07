#include <bits/stdc++.h>
using namespace std;

const int MAXN = 200005;
int par[MAXN];
int dists[MAXN];

int find(int par[], int node) {
    if (par[node] == node) {
        return node;
    }
    par[node] = find(par, par[node]);
    return par[node];
}

void merge(int par[], int dists[], int a, int b) {
    a = find(par, a);
    b = find(par, b);
    if (a != b) {
        if (dists[a] > dists[b]) {
            swap(a, b);
        }
        par[a] = b;
        if (dists[a] == dists[b]) {
            dists[b]++;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 0; i < MAXN; i++) {
        par[i] = i;
        dists[i] = 0;
    }
}
