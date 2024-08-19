#include <bits/stdc++.h>
using namespace std;

const long long INF = 3e12;

void solve() {
    int n;
    long long k;
    cin >> n >> k;
    long long a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    long long l = 0;
    long long r = INF;
    long long val = 0;
    long long fcost = 0;
    while (l <= r) {
        long long m = (l + r) / 2;
        long long cost = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] < m) {
                cost += m - a[i];
            }
        }
        if (cost > k) {
            r = m - 1;
        } else {
            val = m;
            fcost = cost;
            l = m + 1;
        }
    }

    int cnt = 0;
    for (int i = 0; i < n; i++) {
        if (a[i] <= val) {
            cnt++;
        }
    }
    long long res = val * n - n + 1 + k - fcost + n - cnt;
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