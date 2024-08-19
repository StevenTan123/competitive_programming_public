#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int p[n];
    int inds[n + 1];
    for (int i = 0; i < n; i++) {
        cin >> p[i];
        inds[p[i]] = i;
    }

    set<int> sums;
    int L = n + 1;
    int R = 0;
    for (int i = 1; i <= n; i++) {
        int ind = inds[i];
        if (ind != i - 1) {
            sums.insert(p[i - 1] + i);
            L = min(L, i);
            R = max(R, i);
        }
    }

    long long res = 0;
    if (sums.size() == 0) {
        cout << (long long) n * (2 * n - 1) + 2 * n << "\n";
        return;
    } else if (sums.size() == 1) {
        res++;
    }
    for (int i = R + 1; i <= 2 * n; i++) {
        res += min(L + n, i - 1);
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