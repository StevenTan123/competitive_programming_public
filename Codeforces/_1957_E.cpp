#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;
const int MAXN = 1000005;
vector<int> spf(MAXN);
vector<long long> diff(MAXN);
vector<long long> res(MAXN);

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 2; i < MAXN; i++) {
        if (spf[i] == 0) {
            for (int j = i; j < MAXN; j += i) {
                if (spf[j] == 0) {
                    spf[j] = i;
                }
            }
        }
    }

    for (int j = 2; j < MAXN; j++) {
        if (spf[j] == j) {
            for (int i = j; i < MAXN; i += j) {
                int add = (-(i / j) % j + j) % j;
                diff[i] += add;
                diff[i] %= MOD;

                if (i + j < MAXN) {
                    diff[i + j] -= add;
                    diff[i + j] = (diff[i + j] + MOD) % MOD;
                }
            }
        }
    }
    for (int i = 4; i < MAXN; i += 4) {
        int add = i / 4 * 2 % 4;
        diff[i] += add;
        diff[i] %= MOD;

        if (i + 4 < MAXN) {
            diff[i + 4] -= add;
            diff[i + 4] = (diff[i + 4] + MOD) % MOD;
        }
    }
    
    long long cur = 0;
    for (int i = 0; i < MAXN; i++) {
        cur += diff[i];
        cur %= MOD;
        res[i] = cur;
    }
    for (int i = 1; i < MAXN; i++) {
        res[i] += res[i - 1];
        res[i] %= MOD;
    }

    int t;
    cin >> t;
    while (t--) {
        int n;
        cin >> n;
        cout << res[n] << "\n";
    }
}