#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

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

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, m;
    cin >> n >> m;
    string s;
    cin >> s;

    long long prod = 1;
    for (int i = n - 2; i >= 1; i--) {
        if (s[i] == '?') {
            prod *= i;
            prod %= MOD;
        }
    }

    bool zero = s[0] == '?';
    cout << (zero ? 0 : prod) << "\n";
    for (int i = 0; i < m; i++) {
        int ind;
        cin >> ind;
        char c;
        cin >> c;

        if (s[ind - 1] == '?' && c != '?') {
            if (ind - 1 == 0) {
                zero = 0;
            } else {
                prod *= modinv(ind - 1);
                prod %= MOD;
            }
            s[ind - 1] = c;
        } else if (s[ind - 1] != '?' && c == '?') {
            if (ind - 1 == 0) {
                zero = 1;
            } else {
                prod *= ind - 1;
                prod %= MOD;
            }
            s[ind - 1] = c;
        }
        cout << (zero ? 0 : prod) << "\n";
    }
}