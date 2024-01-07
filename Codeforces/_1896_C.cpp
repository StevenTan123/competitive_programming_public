#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, x;
    cin >> n >> x;
    pair<int, int> a[n];
    pair<int, int> b[n];
    for (int i = 0; i < n; i++) {
        int val;
        cin >> val;
        a[i] = {val, i};
    }
    for (int i = 0; i < n; i++) {
        int val;
        cin >> val;
        b[i] = {val, i};
    }
    sort(a, a + n);
    sort(b, b + n);
    
    for (int i = n - 1; i >= n - x; i--) {
        swap(b[i - (n - x)], b[i]);
    }
    sort(b, b + n - x);

    int beauty = 0;
    for (int i = 0; i < n; i++) {
        if (a[i].first > b[i].first) {
            beauty++;
        }
    }
    if (beauty == x) {
        cout << "YES" << endl;
        int res[n];
        for (int i = 0; i < n; i++) {
            res[a[i].second] = b[i].first;
        }
        for (int i = 0; i < n; i++) {
            cout << res[i] << " ";
        }
        cout << endl;
    } else {
        cout << "NO" << endl;
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