#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    set<int> unique;
    for (int i = 0; i < n; i++) {
        int a;
        cin >> a;
        unique.insert(a);
    }
    vector<int> a;
    for (int val : unique) {
        a.push_back(val);
    }
    
    vector<bool> win(a.size());
    win[(int) a.size() - 1] = 1;
    for (int i = (int) a.size() - 2; i >= 0; i--) {
        int val = (i > 0 ? a[i] - a[i - 1] : a[i]);
        if (val > 1) {
            win[i] = 1;
        } else {
            win[i] = !win[i + 1];
        }
    }
    cout << (win[0] ? "Alice\n" : "Bob\n");
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