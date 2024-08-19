#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    int l = 0;
    int r = n;
    while (l + 1 < r) {
        int m = (l + r) / 2;
        int diff = (r - l) / 2;
        bool all_zero = true;
        int tmp[n];
        for (int i = 0; i < n; i++) {
            tmp[i] = a[i] ^ a[(i + diff) % n];
            if (tmp[i] != 0) {
                all_zero = false;
            }
        }
        if (all_zero) {
            r = m;
        } else {
            for (int i = 0; i < n; i++) {
                a[i] = tmp[i];
            }
            l = m;
        }
    }
    bool all_zero = true;
    for (int i = 0; i < n; i++) {
        if (a[i] != 0) {
            all_zero = false;
            break;
        }
    }
    cout << (all_zero ? l : r) << "\n";
}