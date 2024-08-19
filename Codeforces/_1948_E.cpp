#include <bits/stdc++.h>
using namespace std;

void fill_array(int a[], int offset, int start, int cnt) {
    int half = cnt / 2;
    if (cnt % 2 != 0) {
        half++;
    }

    for (int i = 0; i < half; i++) {
        a[start + i] = offset + half - i;
    }
    for (int i = half; i < cnt; i++) {
        a[start + i] = offset + cnt - (i - half);
    }
}

void solve() {
    int n, k;
    cin >> n >> k;

    int a[n];
    int clique[n];
    int cliques = 0;
    for (int i = 0; i < n; i += k) {
        fill_array(a, i, i, min(k, n - i));
        for (int j = i; j < min(i + k, n); j++) {
            clique[j] = i / k + 1;
        }
        cliques++;
    }

    for (int i = 0; i < n; i++) {
        cout << a[i] << " ";
    }
    cout << "\n";
    cout << cliques << "\n";
    for (int i = 0; i < n; i++) {
        cout << clique[i] << " ";
    }
    cout << "\n";
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