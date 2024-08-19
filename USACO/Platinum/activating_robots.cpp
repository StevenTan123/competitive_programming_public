#include <bits/stdc++.h>
using namespace std;

long long ceil_div(long long a, long long b) {
    return (a + b - 1) / b;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int L, R, N, K;
    cin >> L >> R >> N >> K;
    set<int> a;
    for (int i = 0; i < N; i++) {
        int ai;
        cin >> ai;
        a.insert(ai);
    }
    int gap = L / R;
    vector<long long> time_cs(R);
    vector<long long> time_ccs(R);
    for (int i = 0; i < R; i++) {
        time_cs[i] = ceil_div((long long) i * gap * K, K + 1);
        time_ccs[i] = (K == 1 ? LONG_LONG_MAX : ceil_div((long long) i * gap * K, K - 1));
    }

    // dp[i][bitmask] = min time to activate robots in bitmask, currently at robot i
    vector<vector<long long>> dp(R, vector<long long>(1 << R, LONG_LONG_MAX));
    dp[0][1] = 0;
    long long res = LONG_LONG_MAX;
    for (int j = 2; j < (1 << R); j++) {
        for (int i = 0; i < R; i++) {
            if (j & (1 << i)) {
                int prev_mask = j ^ (1 << i);
                long long min_time = LONG_LONG_MAX;
                for (int x = 0; x < R; x++) {
                    if (dp[x][prev_mask] != LONG_LONG_MAX) {
                        long long diff_c = (x > i ? x - i : x - i + R);
                        long long time_c = time_cs[diff_c];
                        long long diff_cc = (x < i ? i - x : i - x + R);
                        long long time_cc = time_ccs[diff_cc];
                        min_time = min(min_time, min(time_c, time_cc) + dp[x][prev_mask]);
                    }
                }
                if (min_time < LONG_LONG_MAX) {
                    int loc = (i * gap + ceil_div(min_time, K)) % L;
                    auto it = a.lower_bound(loc);
                    if (it == a.end()) {
                        it = a.begin();
                    }
                    long long diff = *it - loc;
                    if (diff < 0) {
                        diff += L;
                    }
                    dp[i][j] = min(dp[i][j], (ceil_div(min_time, K) + diff) * K);
                }
            }
            if (j == (1 << R) - 1) {
                res = min(res, dp[i][j]);
            }
        }
    }
    cout << res << "\n";
}