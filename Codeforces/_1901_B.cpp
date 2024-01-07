#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int c[n];
    long long sum = 0;
    for (int i = 0; i < n; i++) {
        cin >> c[i];
        sum += c[i];
    }

    long long save = 1;
    for (int i = 1; i < n; i++) {
        save += min(c[i - 1], c[i]);
    }
    long long res = sum - save;
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