#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;
const int BITS = 63;
long long pow2[BITS];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    pow2[0] = 1;
    for (int i = 1; i < BITS; i++) {
        pow2[i] = (pow2[i - 1] * 2) % MOD;
    }

    int n;
    cin >> n;
    long long a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    vector<long long> dp(n);
    // bit_sums[i][0] = sum of dp values where i-th bit is set in the range leading up to it.
    // bit_sums[i][1] = sum of dp values where i-th bit is not set in the range leading up to it.
    vector<vector<long long>> bit_sums(BITS, vector<long long>(2));
    vector<int> bit_cnts(BITS);
    for (int i = n - 1; i >= 0; i--) {
        for (int j = 0; j < BITS; j++) {
            if (a[i] & ((long long) 1 << j)) {
                bit_cnts[j]++;
                long long temp = bit_sums[j][0];
                bit_sums[j][0] = bit_sums[j][1];
                bit_sums[j][1] = temp;
            }
            dp[i] += pow2[j] * bit_sums[j][0] % MOD + (bit_cnts[j] % 2 == 1 ? pow2[j] : 0);
            dp[i] %= MOD;
        }
        for (int j = 0; j < BITS; j++) {
            bit_sums[j][1] = (bit_sums[j][1] + dp[i]) % MOD;
        }
    }
    cout << dp[0] << endl;
}