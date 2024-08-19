#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1e18 + 7;
const int MAXN = 1000005;
const int P = 31;
long long powP[MAXN];

long long hash_str(string x) {
    long long c_hash = 0;
    for (int i = 0; i < x.length(); i++) {
        int digit = x[i] - 'a' + 1;
        c_hash += (__uint128_t) powP[i] * digit % MOD;
        c_hash %= MOD;
    }
    return c_hash;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    powP[0] = 1;
    for (int i = 1; i < MAXN; i++) {
        powP[i] = (__uint128_t) powP[i - 1] * P % MOD;
    }

    int n, q;
    cin >> n >> q;
    string w[n];
    unordered_map<long long, int> ps_count;
    unordered_map<long long, int> p_count;
    unordered_map<long long, int> s_count;
    for (int i = 0; i < n; i++) {
        cin >> w[i];
        long long p_hash = 0;
        long long s_hash = 0;
        for (int j = 0; j < w[i].length(); j++) {
            int digit = w[i][j] - 'a' + 1;
            p_hash += (__uint128_t) powP[j] * digit % MOD;
            p_hash %= MOD;
            int digit2 = w[i][w[i].length() - j - 1] - 'a' + 1;
            s_hash = (__uint128_t) s_hash * P % MOD + digit2;
            s_hash %= MOD;
            
            long long ps_hash = (p_hash + (__uint128_t) s_hash * powP[j + 1]) % MOD;
            p_count[p_hash]++;
            s_count[s_hash]++;
            ps_count[ps_hash]++;
        }
    }

    for (int i = 0; i < q; i++) {
        string o, p, s;
        cin >> o >> p >> s;

        long long p_hash = hash_str(p);
        long long s_hash = hash_str(s);
        long long ps_hash = (p_hash + (__uint128_t) powP[p.length()] * s_hash) % MOD;
        
        if (o == "AND") {
            cout << ps_count[ps_hash] << "\n";
        } else if (o == "OR") {
            cout << p_count[p_hash] + s_count[s_hash] - ps_count[ps_hash] << "\n";
        } else {
            cout << p_count[p_hash] + s_count[s_hash] - 2 * ps_count[ps_hash] << "\n";
        }
    }
}