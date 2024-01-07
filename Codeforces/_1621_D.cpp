#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    long long must_remove = 0;
    int min_corner = INT_MAX;
    for (int i = 0; i < 2 * n; i++) {
        for (int j = 0; j < 2 * n; j++) {
            int val;
            cin >> val;
            if ((i < n && j < n) || (i >= n && j >= n)) {
                must_remove += val;
            } else if ((i % n == 0 || i % n == n - 1) && (j % n == 0 || j % n == n - 1)) {
                min_corner = min(min_corner, val);
            }
        }
    }
    cout << must_remove + min_corner << endl;
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