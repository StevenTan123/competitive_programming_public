#include <bits/stdc++.h>
using namespace std;

const int N = 20;
const int M = 20;

int n, m;
long long k;

void recurse_half1(long long a[N][M], map<long long, int> diag_freqs[N][M], int r, int c, int left, long long tot) {
    tot ^= a[r][c];
    if (left > 0) {
        if (r < n - 1) {
            recurse_half1(a, diag_freqs, r + 1, c, left - 1, tot);
        }
        if (c < m - 1) {
            recurse_half1(a, diag_freqs, r, c + 1, left - 1, tot);
        }
    } else {
        diag_freqs[r][c][tot]++;
    }
}

long long recurse_half2(long long a[N][M], map<long long, int> diag_freqs[N][M], int r, int c, int left, long long tot) {
    long long ret = 0;
    if (left > 0) {
        tot ^= a[r][c];
        if (r > 0) {
            ret += recurse_half2(a, diag_freqs, r - 1, c, left - 1, tot);
        }
        if (c > 0) {
            ret += recurse_half2(a, diag_freqs, r, c - 1, left - 1, tot);
        }
        return ret;
    } else {
        long long o = k ^ tot;
        if (diag_freqs[r][c].count(o)) {
            ret = diag_freqs[r][c][o];
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    cin >> n >> m >> k;
    long long a[N][M];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
        }
    }
    
    int len1 = (n + m - 2) / 2;
    int len2 = (n + m - 2) - len1;
    map<long long, int> diag_freqs[N][M];
    recurse_half1(a, diag_freqs, 0, 0, len1, 0);
    long long res = recurse_half2(a, diag_freqs, n - 1, m - 1, len2, 0);
    cout << res << endl;
}