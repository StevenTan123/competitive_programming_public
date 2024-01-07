#include <bits/stdc++.h>
using namespace std;

long long const inf = 1e18;

void solve() {
    int n, m;
    cin >> n >> m;

    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    int gcd_all = 0;
    for (int i = 0; i < m; i++) {
        int b;
        cin >> b;
        gcd_all = __gcd(b, gcd_all);
    }

    vector<int> bins[gcd_all];
    long long sums[gcd_all] = {0};
    for (int i = 0; i < n; i++) {
        int bin = i % gcd_all;
        bins[bin].push_back(a[i]);
        sums[bin] += a[i];
    }
    
    long long even_invariant = 0;
    long long odd_invariant = 0;
    for (int i = 0; i < gcd_all; i++) {
        sort(bins[i].begin(), bins[i].end());
    
        long long cur_sum = 0;
        long long max_even = -inf;
        long long max_odd = -inf;
        for (int j = 0; j <= bins[i].size(); j++) {
            if (j % 2 == 0) {
                max_even = max(max_even, sums[i] - cur_sum * 2);
            } else {
                max_odd = max(max_odd, sums[i] - cur_sum * 2);
            }
            if (j < bins[i].size()) {
                cur_sum += bins[i][j];
            }
        }
        even_invariant += max_even;
        odd_invariant += max_odd;
    }
    cout << max(odd_invariant, even_invariant) << endl;
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
