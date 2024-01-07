#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    pair<int, int> a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i].first;
        a[i].second = i;
    }
    sort(a, a + n);

    int res[n];
    int prev = 0;
    long long sum = 0;
    for (int i = 0; i <= n; i++) {
        if ((i > 0 && sum < a[i].first) || i == n) {
            for (int j = prev; j < i; j++) {
                res[a[j].second] = i - j - 1 + j;
            }
            prev = i;
        }
        if (i < n) {
            sum += a[i].first;
        }
    }

    for (int i = 0; i < n; i++) {
        cout << res[i] << " ";
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