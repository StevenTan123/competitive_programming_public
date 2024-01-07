#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    int b[n];
    int b_sort[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> b[i];
        b_sort[i] = b[i];
    }
    sort(b_sort, b_sort + n);

    for (int i = 0; i < n; i++) {
        bool on = false;
        for (int j = 0; j < n; j++) {
            if (a[j] == b_sort[i]) {
                on = true;
            }
            if (on && (a[j] > b_sort[i] || b[j] < b_sort[i])) {
                on = false;
            }
            if (on) {
                a[j] = b_sort[i];
            }
        }

        on = false;
        for (int j = n - 1; j >= 0; j--) {
            if (a[j] == b_sort[i]) {
                on = true;
            }
            if (on && (a[j] > b_sort[i] || b[j] < b_sort[i])) {
                on = false;
            }
            if (on) {
                a[j] = b_sort[i];
            }
        }
    }

    bool possible = true;
    for (int i = 0; i < n; i++) {
        if (a[i] != b[i]) {
            possible = false;
            break;
        }
    }
    cout << (possible ? "YES" : "NO") << endl;
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