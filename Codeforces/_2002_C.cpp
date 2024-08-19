#include <bits/stdc++.h>
using namespace std;

long long dist(long long x1, long long y1, long long x2, long long y2) {
    return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2); 
}

void solve() {
    int n;
    cin >> n;
    vector<int> x(n);
    vector<int> y(n);
    for (int i = 0; i < n; i++) {
        cin >> x[i] >> y[i];
    }
    int xs, ys, xf, yf;
    cin >> xs >> ys >> xf >> yf;
    long long sf_dist = dist(xs, ys, xf, yf);
    long long min_dist = LONG_LONG_MAX;
    for (int i = 0; i < n; i++) {
        min_dist = min(min_dist, dist(x[i], y[i], xf, yf));
    }
    if (min_dist <= sf_dist) {
        cout << "NO\n";
    } else {
        cout << "YES\n";
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