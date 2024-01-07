#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    
    int c[n];
    for (int i = 0; i < n; i++) {
        cin >> c[i];
    }

    sort(c, c + n);
    double min_frac = 1;
    bool possible = true;
    for (int i = 0; i < n; i++) {
        if (c[i] > i + 1) {
            possible = false;
            break;
        }
        min_frac = min(min_frac, (double) c[i] / (i + 1));
    }
    
    if (possible) {
        cout << min_frac << endl;
    } else {
        cout << -1 << endl;
    }
}