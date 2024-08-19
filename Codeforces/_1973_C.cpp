#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int p[n];
    int q[n];
    int one = -1;
    for (int i = 0; i < n; i++) {
        cin >> p[i];
        q[i] = n + 1 - p[i];
        if (p[i] == 1) {
            one = i;
        }
    }

    vector<pair<int, int>> maxes;
    if (one % 2 == 0) {
        for (int i = 1; i < n - 1; i += 2) {
            maxes.emplace_back(p[i], i);
        }
    } else {
        for (int i = 2; i < n - 1; i += 2) {
            maxes.emplace_back(p[i], i);
        }
    }
    sort(maxes.begin(), maxes.end());
    
    vector<int> res(n);
    for (int i = 0; i < n; i++) {
        res[i] = q[i];
    }
    for (int i = 1; i < maxes.size(); i++) {
        auto [pj, j] = maxes[i];
        auto [ppr, pr] = maxes[i - 1];
        res[j] = q[pr];
    }
    res[maxes[0].second] = q[one];
    res[one] = q[maxes[maxes.size() - 1].second];
    for (int i = 0; i < n; i++) {
        cout << res[i] << " ";
    }
    cout << "\n";
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