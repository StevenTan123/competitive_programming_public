#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;

int main() {
    int n, k, q;
    cin >> n >> k >> q;
    
    int a[n];
    // count[i][j] = # good paths starting at cell i of length j
    long long count[n][k + 1];
    // contrib[i] = # times cell i appears in a good path of length k
    long long contrib[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        count[i][0] = 1;
        for (int j = 1; j <= k; j++) {
            count[i][j] = 0;
        }
        contrib[i] = 0;
    }
    
    for (int j = 1; j <= k; j++) {
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                count[i][j] += count[i - 1][j - 1];
            }
            if (i < n - 1) {
                count[i][j] += count[i + 1][j - 1];
            }
            count[i][j] %= MOD;
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= k; j++) {
            // how many paths of length k does cell i get counted as the j-th cell in the path?
            contrib[i] += count[i][j] * count[i][k - j] % MOD;
            contrib[i] %= MOD;
        }
    }

    long long sum = 0;
    for (int i = 0; i < n; i++) {
        sum += contrib[i] * a[i] % MOD;
        sum %= MOD;
    }
    for (int i = 0; i < q; i++) {
        int ind, val;
        cin >> ind >> val;
        ind--;

        sum -= contrib[ind] * a[ind] % MOD;
        a[ind] = val;
        sum += contrib[ind] * a[ind] % MOD + MOD;
        sum %= MOD;
        cout << sum << endl;
    }
}