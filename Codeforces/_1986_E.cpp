#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    sort(a, a + n);

    map<int, vector<int>> residue_map;
    for (int i = 0; i < n; i++) {
        int residue = a[i] % k;
        residue_map[residue].push_back(i);
    }

    bool seen_odd = false;
    bool possible = true;
    long long res = 0;
    for (auto [residue, inds] : residue_map) {
        vector<long long> pre(inds.size());
        long long cur = 0;
        for (int i = 0; i < (int) inds.size() - 1; i += 2) {
            cur += (a[inds[i + 1]] - a[inds[i]]) / k;
            pre[i + 1] = cur;
        }
        if (inds.size() % 2 == 0) {
            res += pre[inds.size() - 1];
        } else {
            if (seen_odd) {
                possible = false;
                break;
            }
            seen_odd = true;

            long long min_add = (inds.size() > 1 ? pre[(int) inds.size() - 2] : 0);
            cur = 0;
            for (int i = inds.size() - 1; i > 0; i -= 2) {
                cur += (a[inds[i]] - a[inds[i - 1]]) / k;
                min_add = min(min_add, cur + (i > 2 ? pre[i - 3] : 0));
            }
            res += min_add;
        }
    }
    if (possible) {
        cout << res << "\n";
    } else {
        cout << "-1\n";
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