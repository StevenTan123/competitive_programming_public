#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, q;
    cin >> n >> q;
    int a[n];
    int tot = 0;
    set<int> ones;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        tot += a[i];
        if (a[i] == 1) {
            ones.insert(i);
        }
    }

    for (int i = 0; i < q; i++) {
        int t;
        cin >> t;
        if (t == 1) {
            int s;
            cin >> s;
            if (s > tot) {
                cout << "NO\n";
            } else if (tot % 2 == s % 2) {
                cout << "YES\n";
            } else {
                if (!ones.empty()) {
                    int first = *ones.begin();
                    int last = *ones.rbegin();
                    int max_val = tot - 1 - first * 2;
                    max_val = max(max_val, tot - 1 - (n - last - 1) * 2);
                    cout << (s <= max_val ? "YES\n" : "NO\n");
                } else {
                    cout << "NO\n";
                }
            }
        } else {
            int ind, v;
            cin >> ind >> v;
            ind--;
            if (a[ind] != v) {
                if (a[ind] == 1) {
                    ones.erase(ind);
                    tot++;
                } else {
                    ones.insert(ind);
                    tot--;
                }
                a[ind] = 3 - a[ind];
            }
        }
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