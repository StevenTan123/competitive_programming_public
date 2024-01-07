#include <bits/stdc++.h>
using namespace std;

const long long inf = 1e18;
const int MAXN = 100005;

void solve() {
    int m;
    cin >> m;
    map<long long, long long> multisets[m];
    long long sizes[m] = {0};
    long long l[m]; long long r[m];
    long long lsum = 0; long long rsum = 0;
    // for each size S, val_msets[S] lists all multisets containing S as an element
    map<long long, vector<int>> val_msets;
    for (int i = 0; i < m; i++) {
        int n;
        cin >> n >> l[i] >> r[i];
        lsum = min(inf, lsum + l[i]);
        rsum = min(inf, rsum + r[i]);

        long long vals[n];
        for (int j = 0; j < n; j++) {
            cin >> vals[j];
            val_msets[vals[j]].push_back(i);
        }
        for (int j = 0; j < n; j++) {
            long long count;
            cin >> count;
            sizes[i] += count;
            multisets[i][vals[j]] = count;
        }
    }

    if (rsum - lsum >= MAXN) {
        cout << "0\n";
    } else {
        long long res = inf;
        for (long long i = lsum; i <= rsum; i++) {
            if (val_msets.count(i)) {
                vector<int> cmsets = val_msets[i];
                long long suf_sums[cmsets.size() + 1] = {0};
                for (int j = cmsets.size() - 1; j >= 0; j--) {
                    suf_sums[j] = suf_sums[j + 1] + r[cmsets[j]];
                }

                long long left = i - (rsum - suf_sums[0]);
                long long anti_beauty = 0;
                for (int j = 0; j < cmsets.size(); j++) {
                    int mset = cmsets[j];
                    long long count = multisets[mset][i];
                    long long min_use = max(l[mset], left - suf_sums[j + 1]);
                    if (min_use + count <= sizes[mset]) {
                        left -= min(r[mset], sizes[mset] - count);
                    } else {
                        anti_beauty += min_use + count - sizes[mset];
                        left -= min_use;
                    }
                }
                res = min(res, anti_beauty);
            } else {
                res = 0;
                break;
            }
        }
        cout << res << "\n";
    }
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
