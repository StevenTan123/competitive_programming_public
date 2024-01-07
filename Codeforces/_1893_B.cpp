#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;
    int a[n];
    int b[m];
    int c[n + m];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < m; i++) {
        cin >> b[i];
    }
    sort(b, b + m);

    int b_ind = m - 1;
    int p = 0;
    for (int i = 0; i <= n; i++) {
        if (i == 0 || i == n || a[i] <= a[i - 1]) {
            while (b_ind >= 0 && (i == n || b[b_ind] >= a[i])) {
                c[p] = b[b_ind];
                b_ind--;
                p++;
            }
        }
        if (i < n) {
            c[p] = a[i];
            p++;
        }
    }

    for (int i = 0; i < n + m; i++) {
        cout << c[i] << " ";
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
