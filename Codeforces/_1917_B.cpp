#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    bool seen[26] = {0};
    int unique[n] = {0};
    for (int i = 0; i < n; i++) {
        unique[i] = (i > 0 ? unique[i - 1] : 0);
        int cur = s[i] - 'a';
        if (!seen[cur]) {
            seen[cur] = true;
            unique[i]++;
        }
    }

    long long res = unique[n - 1];
    for (int i = 1; i < n; i++) {
        res += unique[i - 1];
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