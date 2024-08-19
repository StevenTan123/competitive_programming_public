#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int p[n];
    int a[n];
    int b[n];
    vector<int> divisors[n + 1];
    for (int i = 1; i <= n; i++) {
        for (int j = i; j <= n; j += i) {
            divisors[j].push_back(i);
        }
    }

    pair<int, int> sorted[n];
    vector<int> by_a[n + 1];
    for (int i = 0; i < n; i++) {
        cin >> p[i];
        int gcd_val = __gcd(p[i], i + 1);
        a[i] = p[i] / gcd_val;
        by_a[a[i]].push_back(i);
        b[i] = (i + 1) / gcd_val;
        sorted[i] = {b[i], i};
    }
    sort(sorted, sorted + n);

    vector<int> cnts(n + 1);
    int b_ptr = 0;
    long long res = 0;
    for (int i = 1; i <= n; i++) {
        vector<int> revert;
        for (int j = i; j <= n; j += i) {
            for (int ind : by_a[j]) {
                revert.push_back(b[ind]);
                cnts[b[ind]]++;
            }
        }

        while (b_ptr < n && sorted[b_ptr].first == i) {
            int cur = sorted[b_ptr].second;
            for (int divisor : divisors[a[cur]]) {
                res += cnts[divisor];
            }
            if (b[cur] == 1) {
                res--;
            }
            b_ptr++;
        }

        for (int val : revert) {
            cnts[val]--;
        }
    }
    res /= 2;
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