#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    vector<bool> used(n, false);
    int p[n];
    int mex = 0;
    for (int i = 0; i < n; i++) {
        if (a[i] > 0) {
            p[i] = mex;
            used[mex] = true;
            while (mex < n && used[mex]) {
                mex++;
            }
        } else {
            p[i] = mex - a[i];
            used[p[i]] = true;
        }
    }
    for (int i = 0; i < n; i++) {
        cout << p[i] << " ";
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