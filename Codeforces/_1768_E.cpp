#include <bits/stdc++.h>
using namespace std;

const int MAXN = 3000005;
long long M = 0;
long long fact[MAXN];
long long inv_fact[MAXN];

long long binpow(long long a, long long b) {
    if (b == 0) {
        return 1;
    }
    long long c = binpow(a, b / 2);
    if (b % 2 == 0) {
        return c * c % M;
    } else {
        return c * c % M * a % M;
    }
}

long long modinv(long long a) {
    return binpow(a, M - 2);
}

long long nck(int n, int k) {
    if (k > n) {
        return 0;
    }
    return fact[n] * inv_fact[n - k] % M * inv_fact[k] % M;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n >> M;

    fact[0] = 1;
    for (int i = 1; i < MAXN; i++) {
        fact[i] = fact[i - 1] * i % M;
    }
    inv_fact[MAXN - 1] = modinv(fact[MAXN - 1]);
    for (int i = MAXN - 2; i >= 0; i--) {
        inv_fact[i] = inv_fact[i + 1] * (i + 1) % M;
    }

    // max_ops[i] = # permutations needing at most i operations to sort
    long long max_ops[4];
    max_ops[0] = 1;
    max_ops[1] = (2 * fact[2 * n] - fact[n] + M) % M;
    max_ops[2] = 2 * nck(2 * n, n) * fact[n] % M * fact[2 * n] % M;
    max_ops[3] = fact[3 * n];

    long long fact_cubed = fact[n] * fact[n] % M * fact[n] % M;
    for (int i = 0; i <= n; i++) {
        long long nci = nck(n, i);
        max_ops[2] -= nci * nci % M * nck(2 * n - i, n) % M * fact_cubed % M;
        max_ops[2] = (max_ops[2] + M) % M;
    }

    long long res = 0;
    for (int i = 1; i < 4; i++) {
        res += (max_ops[i] - max_ops[i - 1] + M) * i % M;
        res %= M;
    }
    cout << res << endl;
}