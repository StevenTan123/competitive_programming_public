#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    pair<int, int> a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i].first;
        a[i].second = i;
    }
    sort(a, a + n);
    
    int x[n];
    int y[n];
    int which[n];
    for (int i = 0; i < n; i++) {
        auto [ai, ind] = a[i];
        x[ind] = i + 1;
        if (i == 0) {
            y[ind] = 1;
            which[ind] = ind;
        } else {
            int prev_ind = a[i - 1].second;
            if (ai == 0) {
                y[ind] = 1;
                which[ind] = ind;
            } else if (y[prev_ind] - (ai - 1) >= 1) {
                y[ind] = y[prev_ind] - (ai - 1);
                which[ind] = prev_ind;
            } else if (y[prev_ind] + (ai - 1) <= n) {
                y[ind] = y[prev_ind] + (ai - 1);
                which[ind] = prev_ind;
            } else if (i >= ai) {
                which[ind] = a[i - ai].second;
                y[ind] = y[which[ind]];
            } else {
                y[ind] = 1 + (ai - i);
                which[ind] = a[0].second;
            }
        }
    }

    cout << "YES\n";
    for (int i = 0; i < n; i++) {
        cout << x[i] << " " << y[i] << "\n";
    }
    for (int i = 0; i < n; i++) {
        cout << which[i] + 1 << " ";
    }
    cout << "\n";
}