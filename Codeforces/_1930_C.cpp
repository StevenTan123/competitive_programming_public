#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    int cur = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i] += i + 1;
        cur = max(cur, a[i]);
    }
    sort(a, a + n);
    
    int p = n - 1;
    int cnt = 0;
    vector<int> res;
    while (cur > 1) {
        while (p >= 0 && a[p] >= cur) {
            cnt++;
            p--;
        }
        if (cnt > 0) {
            res.push_back(cur);
            cnt--;
            cur--;
        } else {
            if (p < 0) {
                break;
            }
            cur = a[p];
        }
    }
    for (int i = 0; i < (int) res.size(); i++) {
        cout << res[i] << " ";
    }
    cout << "\n";
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