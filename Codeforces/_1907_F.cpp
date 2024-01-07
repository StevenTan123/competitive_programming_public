#include <bits/stdc++.h>
using namespace std;

int moves(int n, int a[]) {
    int inc_count = 0;
    int inc_ind = n;
    int dec_count = 0;
    int dec_ind = n;
    for (int i = 1; i < n; i++) {
        if (a[i] > a[i - 1]) {
            inc_ind = i;
            inc_count++;
        }
        if (a[i] < a[i - 1]) {
            dec_ind = i;
            dec_count++;
        }
    }
    int res = -1;
    if (inc_count == 0 || (inc_count == 1 && a[n - 1] >= a[0])) {
        res = n - inc_ind + 1;
    }
    if (dec_count == 0 || (dec_count == 1 && a[n - 1] <= a[0])) {
        int cur = n - dec_ind;
        if (res == -1 || cur < res) {
            res = cur;
        }
    }
    return res;
}

void solve() {
    int n;
    cin >> n;
    int a[n];
    int a_rev[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a_rev[n - i - 1] = a[i];
    }

    int res = min(moves(n, a), moves(n, a_rev) + 1);
    cout << res << endl;
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