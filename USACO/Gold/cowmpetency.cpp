#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;

long long binpow(long long a, long long b) {
    if (b == 0) {
        return 1;
    }
    long long c = binpow(a, b / 2);
    if (b % 2 == 0) {
        return c * c % MOD;
    } else {
        return c * c % MOD * a % MOD;
    }
}

long long modinv(long long a) {
    return binpow(a, MOD - 2);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, Q, C;
    cin >> N >> Q >> C;
    vector<pair<int, int>> segs;
    for (int i = 0; i < Q; i++) {
        int a, h;
        cin >> a >> h;
        bool found = false;
        for (int j = 0; j < segs.size(); j++) {
            if (segs[j].second == h) {
                segs[j].first = min(segs[j].first, a);
                found = true;
                break;
            }
        }
        if (!found) {
            segs.emplace_back(a, h);
        }
    }
    Q = segs.size();
    sort(segs.begin(), segs.end());
    int a0 = segs[0].first;
    int h0 = segs[0].second;

    // dp1[i][j] = # ways to fill 1...h[i] with max value j
    // dp2[i][j] = # ways to fill 1...h[i + 1] - 1 with max value j
    // sum[i][j] = # sum of dp[i][0...j]
    vector<vector<long long>> dp1(Q + 1, vector<long long>(C + 1));
    vector<vector<long long>> dp2(Q + 1, vector<long long>(C + 1));
    vector<vector<long long>> sum1(Q + 1, vector<long long>(C + 1));
    vector<vector<long long>> sum2(Q + 1, vector<long long>(C + 1));
    for (int i = 1; i <= C; i++) {
        long long sub = (binpow(i, a0) - binpow(i - 1, a0)) % MOD;
        dp2[0][i] = sub * binpow(i, h0 - a0 - 1) % MOD;
        sum2[0][i] = (sum2[0][i - 1] + dp2[0][i]) % MOD;
    }
    for (int i = 1; i <= Q; i++) {
        for (int j = 1; j <= C; j++) {
            dp1[i][j] = sum2[i - 1][j - 1];
            if (i < Q) {
                dp2[i][j] = dp1[i][j] * binpow(j, segs[i].second - segs[i - 1].second - 1) % MOD;
                int diff = segs[i].first - segs[i - 1].second;
                long long sub = (binpow(j, diff) - binpow(j - 1, diff)) % MOD;
                dp2[i][j] += sum1[i][j - 1] * sub % MOD * binpow(j, segs[i].second - segs[i].first - 1);
                dp2[i][j] %= MOD; 
            }
            sum1[i][j] = (sum1[i][j - 1] + dp1[i][j]) % MOD;
            sum2[i][j] = (sum2[i][j - 1] + dp2[i][j]) % MOD;
        }
    }
    long long res = sum1[Q][C] * binpow(C, N - segs[Q - 1].second) % MOD;
    res = (res + MOD) % MOD;
    cout << res << endl;
}