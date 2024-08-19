#include <bits/stdc++.h>
using namespace std;

const int MAXV = 2000005;
vector<int> spf(MAXV, 0);

void solve() {
    int n, m;
    cin >> n >> m;

    long long res = 0;
    for (int i = 2; i <= m; i++) {
        map<int, int> freqs;
        int cur = i;
        while (cur > 1) {
            if (!freqs.count(spf[cur])) {
                freqs[spf[cur]] = 1;
            }
            freqs[spf[cur]] *= spf[cur];
            cur /= spf[cur];
        }
        
        vector<pair<int, int>> factors;
        for (auto [factor, freq] : freqs) {
            factors.emplace_back(factor, freq);
        }
        
        int pow2 = (1 << factors.size());
        for (int j = 0; j < pow2; j++) {
            long long b = 1;
            for (int k = 0; k < factors.size(); k++) {
                if (j & (1 << k)) {
                    b *= factors[k].second;
                }
            }
            long long extra = i / b;
            if (b < extra) {
                long long a = extra - b;
                long long cnt = m / i;
                cnt = min(cnt, n / (a * extra));
                res += cnt;
            }
        }
    }
    cout << res << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 2; i < MAXV; i++) {
        if (spf[i] == 0) {
            for (int j = i; j < MAXV; j += i) {
                if (spf[j] == 0) {
                    spf[j] = i;
                }
            }
        }
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}