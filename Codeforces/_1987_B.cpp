#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    long long a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    long long diffs[n];
    diffs[0] = 0;
    long long max_diff = 0;
    long long diff_sum = 0;
    for (int i = 1; i < n; i++) {
        if (a[i] < a[i - 1]) {
            diffs[i] = a[i - 1] - a[i];
            a[i] = a[i - 1];
            max_diff = max(max_diff, diffs[i]);
            diff_sum += diffs[i];
        } else {
            diffs[i] = 0;
        }
    }
    cout << diff_sum + max_diff << "\n";
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