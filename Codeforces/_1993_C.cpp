#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;
    vector<int> a(n);
    vector<int> diff(2 * k + 1);
    int max_val = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        max_val = max(max_val, a[i]);

        int residue = a[i] % (2 * k);
        diff[residue]++;
        if (residue + k > 2 * k) {
            diff[2 * k]--;
            diff[0]++;
            diff[residue - k]--;
        } else {
            diff[residue + k]--;
        }
    }
    int max_div = max_val / (2 * k);
    int max_res = max_val % (2 * k);

    vector<int> psum(2 * k + 1);
    int cur = 0;
    long long first = LONG_LONG_MAX;
    for (int i = 0; i <= 2 * k; i++) {
        cur += diff[i];
        psum[i] = cur;
        if (psum[i] == n) {
            long long cur_time = max_div * 2 * k;
            if (max_res <= i) {
                cur_time += i;
            } else {
                cur_time += 2 * k + i;
            }
            first = min(first, cur_time);
        }
    }

    if (first == LONG_LONG_MAX) {
        cout << "-1\n";
    } else {
        cout << first << "\n";
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