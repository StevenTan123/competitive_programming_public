#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;
    string s;
    cin >> s;

    int Bcount = 0;
    int type = -1;
    int ind = -1;
    for (int i = n; i >= 0; i--) {
        if (i < n && s[i] == 'B') {
            Bcount++;
        }
        if (Bcount + i == k) {
            type = 0;
            ind = i;
        } else if (Bcount == k) {
            type = 1;
            ind = i;
        }
    }
    
    if (ind == 0) {
        cout << 0 << endl;
    } else {
        cout << 1 << endl;
        if (type == 0) {
            cout << ind << " B" << endl;
        } else {
            cout << ind << " A" << endl;
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