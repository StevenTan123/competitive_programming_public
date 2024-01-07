#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int l[n]; set<int> r; int c[n];
    for (int i = 0; i < n; i++) cin >> l[i];
    for (int i = 0; i < n; i++) {
        int ri; cin >> ri;
        r.insert(ri);
    }
    for (int i = 0; i < n; i++) cin >> c[i];
    sort(l, l + n);
    sort(c, c + n);

    vector<int> lens;
    for (int i = n - 1; i >= 0; i--) {
        auto it = r.lower_bound(l[i]);
        lens.push_back(*it - l[i]);
        r.erase(it);
    }
    sort(lens.begin(), lens.end());

    long long res = 0;
    for (int i = 0; i < n; i++) {
        res += (long long) c[n - i - 1] * lens[i];
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