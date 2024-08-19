#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int h[n];
    for (int i = 0; i < n; i++) {
        cin >> h[i];
    }

    long long res = 0;
    long long start[n];
    for (int i = n - 1; i >= 0; i--) {
        if (i == n - 1) {
            start[i] = 0;
        } else {
            if (h[i] > h[i + 1]) {
                if (h[i] - h[i + 1] <= start[i + 1]) {
                    h[i] = h[i + 1];
                    start[i] = start[i + 1] + 1;
                } else {
                    h[i] -= start[i + 1];
                    start[i] = start[i + 1];
                }
            } else {
                start[i] = start[i + 1] + h[i + 1] - h[i] + 1;
            }
        }
        res = max(res, start[i] + h[i]);
    }
    cout << res << "\n";
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