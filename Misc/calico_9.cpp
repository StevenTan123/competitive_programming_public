#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
const int MAXV = 2005;

long long binpow(long long a, long long b) {
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

// Multiply two polynomials, only keeping track of powers up to MAXV.
vector<long long> mult_poly(vector<long long> &a, vector<long long> &b) {
    vector<long long> res(MAXV);
    for (int i = 0; i < MAXV; i++) {
        for (int j = 0; i + j < MAXV; j++) {
            res[i + j] += a[i] * b[j];
            res[i + j] %= MOD;
        }
    }
    return res;
}

// Exponentiate polynomial to the power e, only keeping track of powers up to MAXV.
vector<long long> poly_binpow(vector<long long> a, long long e) {
    if (e == 1) {
        return a;
    }
    vector<long long> half = poly_binpow(a, e / 2);
    vector<long long> res = mult_poly(half, half);
    if (e % 2 == 1) {
        res = mult_poly(res, a);
    }
    return res;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int H, M;
    long long N;
    cin >> H >> N >> M;

    int R[M];
    for (int i = 0; i < M; i++) {
        cin >> R[i];
    }
    
    vector<long long> mult(MAXV);
    for (int i = 0; i < M; i++) {
        mult[R[i]]++;
    }
    vector<long long> res = poly_binpow(mult, N);
    long long ways_survive = 0;
    long long ways_total = binpow(M, N);
    for (int i = 0; i < H; i++) {
        ways_survive += res[i];
        ways_survive %= MOD;
    }
    long long p_survive = ways_survive * modinv(ways_total) % MOD;
    long long p_die = (1 - p_survive + MOD) % MOD;
    cout << p_die << "\n";
}