#include <bits/stdc++.h>
using namespace std;
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
 
    int n, k;
    cin >> n >> k;
    int a[n];
    int d[n];
    int min_cost = INT_MAX;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> d[i];
        if (i < n - 1) {
            min_cost = min(min_cost, d[i]);
        }
    }
 
    long long start[n];
    long long ssum = 0;
    for (int i = n - 1; i >= 0; i--) {
        ssum += a[i];
        start[i] = ssum - d[i];
    }
 
    if (k == 0) {
        long long res = 0;
        for (int i = 0; i < n; i++) {
            res = max(res, start[i]);
        }
        cout << res << endl;
    } else if (k == 1) {
        long long res = 0;
        int pre_min = d[0];
        long long psum = a[0];
        for (int i = 1; i < n; i++) {
            res = max(res, start[i]);
            res = max(res, psum - pre_min + start[i]);

            pre_min = min(pre_min, d[i]);
            psum += a[i];

            if (i < n - 1) {
                res = max(res, ssum - a[i] - d[0]);
                res = max(res, ssum - d[i] - d[0]);
            }
        }
        res = max(res, ssum - a[n - 1] - min_cost);
        cout << res << endl;
    } else {
        cout << max(0ll, max(ssum - min_cost, start[n - 1])) << endl;
    }
}