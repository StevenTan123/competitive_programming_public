#include <bits/stdc++.h>
using namespace std;

const int MAXV = 1005;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, a, b;
    cin >> n >> a >> b;
    int v[n + 1];
    v[0] = 0;
    for (int i = 1; i <= n; i++) {
        cin >> v[i];
        v[i] *= -1;
    }
    int suf[n + 1];
    int ssum = 0;
    for (int i = n; i >= 0; i--) {
        suf[i] = ssum;
        ssum += v[i];
    }

    // first_inc[i][j] = smallest index k > i s.t. sum of v[i + 1...k] >= j
    int first_inc[n + 1][MAXV];
    // first_dec[i][j] = smallest index k > i s.t. sum of v[i + 1...k] <= -j
    int first_dec[n + 1][MAXV];
    for (int i = 0; i <= n; i++) {
        int max_inc = 0;
        int max_dec = 0;
        int sum = 0;
        for (int j = i + 1; j <= n; j++) {
            sum += v[j];
            if (sum >= max_inc) {
                while (max_inc < MAXV && max_inc <= sum) {
                    first_inc[i][max_inc] = j;
                    max_inc++;
                }
            } else if (sum <= -max_dec) {
                while (max_dec < MAXV && max_dec <= -sum) {
                    first_dec[i][max_dec] = j;
                    max_dec++;
                }
            }
        }
        while (max_inc < MAXV) {
            first_inc[i][max_inc] = n + 1;
            max_inc++;
        }
        while (max_dec < MAXV) {
            first_dec[i][max_dec] = n + 1;
            max_dec++;
        }
    }

    // end_top[i][j] = ending water if restricted max is i and currently at max water at op j.
    int end_top[MAXV][n + 1];
    // end_bot[i][j] = ending water if restricted max is i and currently at min water at op j.
    int end_bot[MAXV][n + 1];
    for (int i = 0; i < MAXV; i++) {
        end_top[i][n] = i;
        end_bot[i][n] = 0;
        for (int j = n - 1; j >= 0; j--) {
            int top_top = first_inc[j][0];
            int top_bot = first_dec[j][i];
            if (top_top > n && top_bot > n) {
                end_top[i][j] = i + suf[j];
            } else if (top_top < top_bot) {
                end_top[i][j] = end_top[i][top_top];
            } else {
                end_top[i][j] = end_bot[i][top_bot];
            }

            int bot_top = first_inc[j][i];
            int bot_bot = first_dec[j][0];
            if (bot_bot > n && bot_top > n) {
                end_bot[i][j] = suf[j];
            } else if (bot_top < bot_bot) {
                end_bot[i][j] = end_top[i][bot_top];
            } else {
                end_bot[i][j] = end_bot[i][bot_bot];
            }
        }
    }

    for (int i = 0; i <= a; i++) {
        for (int j = 0; j <= b; j++) {
            int minv = max(0, i + j - b);
            int maxv = min(a, i + j);
            int range = maxv - minv;
            int start = i - minv;
            int next_top = first_inc[0][range - start];
            int next_bot = first_dec[0][start];
            int res;
            if (next_top > n && next_bot > n) {
                res = start + suf[0];
            } else if (next_top < next_bot) {
                res = end_top[range][next_top];
            } else {
                res = end_bot[range][next_bot];
            }
            cout << res + minv << " ";
        }
        cout << "\n";
    }
}