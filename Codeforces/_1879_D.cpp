#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
const int power = 31;
long long pow2[power] = {0};

int main() {
    pow2[0] = 1;
    for (int i = 1; i < power; i++) {
        pow2[i] = pow2[i - 1] * 2 % MOD;
    }

    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    int bits[n][power];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < power; j++) {
            bits[i][j] = a[i] % 2;
            a[i] /= 2;
        }
    }

    long long len_sum_mat[n][power];
    int count_mat[n][power] = {0};
    for (int i = 0; i < power; i++) {
        long long len_sums[2] = {0};
        int counts[2] = {0};
        int cur_group = 0;
        int prev = n;
        for (int j = n - 1; j >= 0; j--) {
            if (bits[j][i] == 1) {
                cur_group = 1 - cur_group;

                len_sums[cur_group] += (long long)(prev - j) * counts[cur_group];
                if ((prev - j) % 2 == 0) {
                    len_sums[cur_group] += (long long)(prev - j) / 2 * (prev - j + 1);
                } else {
                    len_sums[cur_group] += (long long)(prev - j + 1) / 2 * (prev - j);
                }
                len_sums[cur_group] %= MOD;

                len_sums[1 - cur_group] += (long long)(prev - j) * counts[1 - cur_group];
                len_sums[1 - cur_group] %= MOD;

                counts[cur_group] += prev - j;
                prev = j;

                len_sum_mat[j][i] = len_sums[cur_group];
                count_mat[j][i] = counts[cur_group];
            } else {
                if (j < n - 1) {
                    count_mat[j][i] = count_mat[j + 1][i];
                }
            }
        }
    }

    long long res = 0;
    for (int i = 0; i < power; i++) {
        int prev = -1;
        long long geo_seq = 0;
        for (int j = 0; j < n; j++) {
            if (bits[j][i] == 1) {
                long long add = len_sum_mat[j][i] * pow2[i] % MOD;
                res += add * (j - prev) + geo_seq;
                res %= MOD;
                prev = j;
                geo_seq = 0;
            }
            geo_seq += (long long)(j - prev) * count_mat[j][i] % MOD * pow2[i];
            geo_seq %= MOD;
        }
    }
    cout << res << endl;
}
