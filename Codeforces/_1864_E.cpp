#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
const int MAX_BITS = 30;

long binpow(long long a, long long b) {
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

void solve() {
    int n;
    cin >> n;
    int s[n];
    for (int i = 0; i < n; i++) {
        cin >> s[i];
    }
    std::sort(s, s + n);

    int set_bits[n][MAX_BITS];
    for (int i = 0; i < n; i++) {
        int val = s[i];
        for (int j = MAX_BITS - 1; j >= 0; j--) {
            set_bits[i][j] = val % 2;
            val /= 2;
        }
        for (int j = 1; j < MAX_BITS; j++) {
            set_bits[i][j] += set_bits[i][j - 1];
        }
    }

    // bounds[i][j][0/1] stores the smallest and largest indices 0...n-1
    // such that bits 0...i match for all words between i and j.
    int bounds[n][MAX_BITS][2];
    for (int i = 0; i < MAX_BITS; i++) {
        int prev = 0;
        for (int j = 0; j < n; j++) {
            if (s[prev] >> (MAX_BITS - i - 1) != s[j] >> (MAX_BITS - i - 1)) {
                prev = j;
            }
            bounds[j][i][0] = prev;
        }
        prev = n - 1;
        for (int j = n - 1; j >= 0; j--) {
            if (bounds[j][i][0] != bounds[prev][i][0]) {
                prev = j;
            }
            bounds[j][i][1] = prev;
        }
    }

    long long sum = 0;
    for (int i = 0; i < MAX_BITS; i++) {
        for (int j = 0; j < n; j++) {
            int temp[] = {0, n - 1};
            int *prev = temp;
            if (i > 0) {
                prev = (int *) &bounds[j][i - 1];
            }
            int *cur = (int *) &bounds[j][i];
            if ((s[j] & (1 << (MAX_BITS - i - 1))) == 0) {
                int count = (prev[1] - prev[0] + 1) - (cur[1] - cur[0] + 1);
                int k = set_bits[j][i];
                int turns = k + 1 + (k % 2 == 1 ? 1 : 0);
                sum = (sum + (long long) count * turns) % MOD;
            } else {
                int count = (prev[1] - prev[0] + 1) - (cur[1] - cur[0] + 1);
                int k = set_bits[j][i] - 1;
                int turns = k + 1 + (k % 2 == 0 ? 1 : 0);
                sum = (sum + (long long) count * turns) % MOD;     
            }
            if (i == MAX_BITS - 1) {
                int count = cur[1] - cur[0] + 1;
                int k = set_bits[j][i];
                int turns = k + 1;
                sum = (sum + (long long) count * turns) % MOD;   
            }
        }
    }
    long long exp_val = sum * modinv((long long) n * n % MOD) % MOD;
    cout << exp_val << endl;
}

int main() {
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}