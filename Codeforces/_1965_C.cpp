#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    
    vector<int> folds;
    folds.push_back(0);
    for (int i = 1; i < n; i++) {
        if (s[i - 1] == s[i]) {
            folds.push_back(i);
        }
    }
    folds.push_back(n);
    
    int left = 0;
    int right = 0;
    int cur = 0;
    bool dir = true;
    for (int i = 0; i < (int) folds.size() - 1; i++) {
        int len = folds[i + 1] - folds[i];
        if (dir) {
            cur += len;
        } else {
            cur -= len;
        }
        right = max(right, cur);
        left = min(left, cur);
        dir = !dir;
    }
    cout << right - left << "\n";
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