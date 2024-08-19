#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;

vector<vector<long long>> mult(vector<vector<long long>> &a, vector<vector<long long>> &b) {
    vector<vector<long long>> mat(2, vector<long long>(2));
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 2; k++) {
                mat[i][j] += a[i][k] * b[k][j];
                mat[i][j] %= MOD;
            }
        }
    }
    return mat;
}

vector<vector<long long>> mat_exp(vector<vector<long long>> &a, int e) {
    if (e == 0) {
        return { {1, 0}, {0, 1} };
    }
    vector<vector<long long>> half = mat_exp(a, e / 2);
    vector<vector<long long>> res = mult(half, half);
    if (e % 2 == 1) {
        res = mult(res, a);
    }
    return res;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int m, n;
    cin >> m >> n;
    int s[m];
    int l[m];
    for (int i = 0; i < m; i++) {
        cin >> s[i];
    }

    long long ss = 0; long long sl = 0; long long ts = 0; long long tl = 0; 
    long long ssum = 0; long long tsum = 0;
    for (int i = 0; i < m; i++) {
        cin >> l[i];
        ss += s[i] * s[i];
        sl += s[i] * l[i];
        tl += (s[i] + l[i]) * l[i];
        ts += (s[i] + l[i]) * s[i];
        ssum += s[i];
        tsum += s[i] + l[i];
    }
    ss %= MOD; sl %= MOD; tl %= MOD; ts %= MOD;

    vector<vector<long long>> mat(2, vector<long long>(2));
    mat[0][0] = ts; mat[0][1] = tl; mat[1][0] = ss; mat[1][1] = sl;

    mat = mat_exp(mat, n - 1);
    long long res = s[0] * mat[0][0] % MOD * tsum % MOD + s[0] * mat[0][1] % MOD * ssum % MOD
                  + l[0] * mat[1][0] % MOD * tsum % MOD + l[0] * mat[1][1] % MOD * ssum % MOD;
    res %= MOD;
    cout << res << "\n";
}