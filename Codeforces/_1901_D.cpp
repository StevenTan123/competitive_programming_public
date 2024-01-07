#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    int a[n];
    int forw[n];
    int back[n];
    int pre[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        forw[i] = a[i] + i;
        back[i] = a[i] + n - 1 - i;
        pre[i] = i;
        if (i > 0 && back[i] < back[pre[i - 1]]) {
            pre[i] = pre[i - 1];
        }
    }
    int suf[n];
    for (int i = n - 1; i >= 0; i--) {
        suf[i] = i;
        if (i < n - 1 && forw[i] < forw[suf[i + 1]]) {
            suf[i] = suf[i + 1];
        }
    }
    
    int res = INT_MAX;
    for (int i = 0; i < n; i++) {
        int cur = a[i];
        if (i > 0) {
            int pref = pre[i - 1];
            cur = max(cur, n - pref - 1 + a[pref]);
        }
        if (i < n - 1) {
            int suff = suf[i + 1];
            cur = max(cur, suff + a[suff]);
        }
        res = min(res, cur);
    }
    cout << res << endl;
}