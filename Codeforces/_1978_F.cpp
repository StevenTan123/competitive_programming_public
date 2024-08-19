#include <bits/stdc++.h>
using namespace std;

const int MAXN = 1000005;
vector<int> spf(MAXN);
vector<int> prevs(MAXN, -1);

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

void solve() {
    int n, k;
    cin >> n >> k;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    int aa[2 * n - 1];
    for (int i = 0; i < n; i++) {
        aa[n - 1 + i] = a[i];
        if (i > 0) {
            aa[i - 1] = a[i];
        }
    }

    int par[2 * n - 1];
    int dists[2 * n - 1];
    for (int i = 0; i < 2 * n - 1; i++) {
        par[i] = i;
        dists[i] = 0;
    }
    
    vector<int> factors;
    for (int i = 0; i < 2 * n - 1; i++) {
        int cur = aa[i];
        while (cur > 1) {
            if (prevs[spf[cur]] != -1) {
                merge(par, dists, prevs[spf[cur]], i);
            }
            prevs[spf[cur]] = i;
            factors.push_back(spf[cur]);
            cur /= spf[cur];
        }
        if (i >= k) {
            cur = aa[i - k];
            while (cur > 1) {
                if (prevs[spf[cur]] == i - k) {
                    prevs[spf[cur]] = -1;
                }
                cur /= spf[cur];
            }
        }
    }
    set<int> unique;
    long long add = 0;
    for (int i = 0; i < 2 * n - 1; i++) {
        unique.insert(find(par, i));
        if (aa[i] == 1) {
            add += min(i, 2 * n - 2 - i);
        }
    }
    cout << unique.size() + add << "\n";

    for (int factor : factors) {
        prevs[factor] = -1;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 2; i < MAXN; i++) {
        if (spf[i] == 0) {
            for (int j = i; j < MAXN; j += i) {
                if (spf[j] == 0) {
                    spf[j] = i; 
                }
            }
        }
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}