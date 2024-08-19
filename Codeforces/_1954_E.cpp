#include <bits/stdc++.h>
using namespace std;

const int SQRT = 400;

int ceil_div(int a, int b) {
    return a / b + (a % b == 0 ? 0 : 1);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    int a[n];
    int d[n - 1];
    int maxv = 0;
    long long sum = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        if (i > 0) {
            d[i - 1] = min(a[i - 1], a[i]);
        }
        maxv = max(maxv, a[i]);
        sum += a[i];
    }

    if (n == 1) {
        for (int i = 1; i <= maxv; i++) {
            cout << ceil_div(maxv, i) << " ";
        }
        cout << "\n";
        return 0;
    }

    vector<int> cnta(maxv + 1);
    vector<int> cntd(maxv + 1);
    for (int i = 0; i < n; i++) {
        if (i < n - 1) {
            cntd[d[i]]++;
        }
        cnta[a[i]]++;
    }
    int psuma[maxv + 1];
    int psumd[maxv + 1];
    for (int i = 0; i <= maxv; i++) {
        psuma[i] = (i > 0 ? psuma[i - 1] : 0) + cnta[i];
        psumd[i] = (i > 0 ? psumd[i - 1] : 0) + cntd[i];
    }

    // res[i] = # seconds to kill all monsters given k = i
    vector<long long> res(maxv + 1);
    for (int i = 1; i <= maxv; i++) {
        if (i < SQRT) {
            for (int j = 0; j < n; j++) {
                res[i] += ceil_div(a[j], i);
            }
            for (int j = 0; j < n - 1; j++) {
                res[i] -= ceil_div(min(a[j], a[j + 1]), i);
            }
        } else {
            for (int j = 1; j <= SQRT; j++) {
                int l = i * j - i;
                int r = i * j;
                if (l > maxv) {
                    continue;
                }
                
                // Summing ceils.
                res[i] += (long long) j * (psuma[min(r, maxv)] - psuma[l]);
            
                // Subtracting adjacent carries.
                res[i] -= (long long) j * (psumd[min(r, maxv)] - psumd[l]);
            }
        }
    }
    for (int i = 1; i <= maxv; i++) {
        cout << res[i] << " ";
    }
    cout << "\n";
}