#include <bits/stdc++.h>
using namespace std;
 
const long long MOD = 1000000009;
const int MAXV = 1001;
const int MAXB = 128;
 
unordered_map<long long, int> dp;
__uint128_t pow2[MAXB];
__uint128_t MAX128 = 0;
 
long long hash_state(int x, int y, int b) {
    long long ret = (b + (long long) y * MAXV + (long long) x * MAXV * MAXV);
    return ret;
}
 
long long recurse(int x, int y, int b) {
    long long val = hash_state(x, y, b);
    if (dp.count(val)) {
        return dp[val];
    }
    if (b == 0 || x >= pow2[b]) {
        return 0;
    }
 
    long long ret = 0;
    if (y % 2 == 1) {
        ret = (recurse(x, 2 * y, b) + recurse(x + y, 2 * y, b)) % MOD;
    } else if (x % 2 == 0) {
        ret = recurse(x / 2, y / 2, b - 1);
    } else {
        __uint128_t cpow = (b < MAXB ? (pow2[b] - 1) : MAX128);
        long long add = ((cpow - x) / y) % MOD + 1;
        ret = (recurse(x - 1, y, b) + add) % MOD;
    }
 
    dp[val] = ret;
    return ret;
}
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    MAX128--;

    pow2[0] = 1;
    for (int i = 1; i < MAXB; i++) {
        pow2[i] = pow2[i - 1] * 2;
    }
    
    int k, b;
    cin >> k >> b;
    cout << recurse(0, k, b) << endl;
}