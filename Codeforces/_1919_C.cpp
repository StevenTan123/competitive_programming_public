#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    int one = INT_MAX;
    int two = INT_MAX;
    int res = 0;
    for (int i = 0; i < n; i++) {
        if (a[i] <= one && a[i] <= two) {
            if (one < two) {
                one = a[i];
            } else {
                two = a[i];
            }
        } else if (a[i] <= one) {
            one = a[i];
        } else if (a[i] <= two) {
            two = a[i];
        } else {
            if (one < two) {
                one = a[i];
            } else {
                two = a[i];
            }
            res++;
        }
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