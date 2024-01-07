#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    
    long long res = 0;
    int prev = a[n - 1];
    for (int i = n - 2; i >= 0; i--) {
        if (a[i] <= prev) {
            prev = a[i];
            continue;
        }
        int ceil = a[i] / prev;
        if (a[i] % prev != 0) {
            ceil++;
        }
        res += ceil - 1;
        if (a[i] % prev != 0) {
            prev = a[i] / ceil;
        }
    }
    cout << res << endl;
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