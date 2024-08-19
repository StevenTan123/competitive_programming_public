#include <bits/stdc++.h>
using namespace std;

const int MAXN = 30005;
const int MAXL = 300005;
bitset<MAXL> dp;

struct Sheet {
    int d, fast, slow;
    Sheet(int _d, int _fast, int _slow) : d(_d), fast(_fast), slow(_slow) {}
    bool operator<(const Sheet &o) const {
        return slow < o.slow;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, q;
    cin >> n >> q;
    vector<Sheet> sheets;
    for (int i = 0; i < n; i++) {
        int d, fast, slow;
        cin >> d >> fast >> slow;
        sheets.push_back(Sheet(d, fast, slow));
    }
    sort(sheets.begin(), sheets.end());

    int pre[n];
    int suf[n];
    long long psum[n];
    long long ssum[n];
    for (int i = 0; i < n; i++) {
        pre[i] = max(i > 0 ? pre[i - 1] : 0, sheets[i].slow);
        psum[i] = (i > 0 ? psum[i - 1] : 0) + sheets[i].d;
    }
    for (int i = n - 1; i >= 0; i--) {
        suf[i] = max(i < n - 1 ? suf[i + 1] : 0, sheets[i].fast);
        ssum[i] = (i < n - 1 ? ssum[i + 1] : 0) + sheets[i].d;
    }

    // dp[i][j] = possible to use subset of sheets 0...i to fill j units of space on one line.
    dp[0] = 1;

    // For each index i, calculate the minimum length needed to fit everything given i...n use both lines.
    int min_l[n + 1];
    min_l[0] = ssum[0];

    for (int i = 0; i < n; i++) {
        dp = dp | (dp << sheets[i].d);
        
        long long suf_len = (i < n - 1 ? ssum[i + 1] : 0);
        min_l[i + 1] = suf_len;
        long long half = psum[i] / 2;
        if (psum[i] % 2 == 0) {
            half--;
        }
        min_l[i + 1] += dp._Find_next(half);
    }
    
    int res[MAXL];
    int p = 0;
    for (int i = MAXL - 1; i >= 1; i--) {
        while (p <= n && min_l[p] > i) {
            p++;
        }
        if (p > n) {
            res[i] = -1;
        } else {
            res[i] = max(p > 0 ? pre[p - 1] : 0, p < n ? suf[p] : 0);
        }
    }

    for (int i = 0; i < q; i++) {
        int l;
        cin >> l;
        cout << res[l] << "\n";
    }
}