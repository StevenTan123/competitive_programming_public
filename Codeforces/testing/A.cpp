#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    map<int, int> freqs;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        freqs[a[i]]++;
    }
    if (freqs.size() == 1) {
        cout << "NO\n";
    } else {
        cout << "YES\n";
        int found = -1;
        for (auto [val, cnt] : freqs) {
            if (cnt >= 2) {
                found = val;
            }
        }
        int ind = 0;
        if (found != -1) {
            for (int i = 0; i < n; i++) {
                if (a[i] == found) {
                    ind = i;
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == ind) {
                cout << "R";
            } else {
                cout << "B";
            }
        }
        cout << "\n";
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