#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
const int MAXN = 4005;

long long binom[MAXN][MAXN];

long long nck(int n, int k) {
    if (k > n || n < 0 || k < 0) {
        return 0;
    }
    return binom[n][k];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 0; i < MAXN; i++) {
        binom[i][0] = 1;
        binom[i][i] = 1;
        for (int j = 1; j < i; j++) {
            binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
        }
    }
}