#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    int freqs[n + 1] = {0};
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        freqs[a[i]]++;
    }
    sort(a, a + n);

    long long gcd_cnt[n + 1] = {0};
    for (int i = n; i >= 1; i--) {
        long long cnt = 0;
        long long sub = 0;
        for (int j = i; j <= n; j += i) {
            cnt += freqs[j];
            sub += gcd_cnt[j];
        }
        gcd_cnt[i] = cnt * (cnt - 1) / 2 - sub;
    }

    long long res = 0;
    bool seen[n + 1] = {0};
    for (int i = 0; i < n; i++) {
        if (i == 0 || a[i] > a[i - 1]) {
            for (int j = a[i]; j <= n; j += a[i]) {
                if (!seen[j]) {
                    res += gcd_cnt[j];
                    seen[j] = 1;
                }
            }
        }
    }
    res = (long long) n * (n - 1) / 2 - res;
    cout << res << endl;
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