#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;
const int MAXN = 100005;

long long fact[MAXN];
long long inv_fact[MAXN];

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

long long nck(int n, int k) {
    if (k > n) {
        return 0;
    }
    return fact[n] * inv_fact[n - k] % MOD * inv_fact[k] % MOD;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    fact[0] = 1;
    for (int i = 1; i < MAXN; i++) {
        fact[i] = fact[i - 1] * i % MOD;
    }
    inv_fact[MAXN - 1] = modinv(fact[MAXN - 1]);
    for (int i = MAXN - 2; i >= 0; i--) {
        inv_fact[i] = inv_fact[i + 1] * (i + 1) % MOD;
    }

    int n;
    cin >> n;
    int a[n];
    long long sum = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        sum += a[i];
    }
    if (sum % n == 0) {
        int avg = sum / n;
        int avg_count = 0;
        int source_count = 0;
        int sink_count = 0;
        map<int, int> freq_source;
        map<int, int> freq_sink;
        for (int i = 0; i < n; i++) {
            if (a[i] < avg) {
                freq_sink[a[i]]++;
                sink_count++;
            } else if (a[i] == avg) {
                avg_count++;
            } else {
                freq_source[a[i]]++;
                source_count++;
            }
        }

        long long perm_source = fact[source_count];
        for (pair<int, int> source : freq_source) {
            perm_source *= inv_fact[source.second];
            perm_source %= MOD;
        }
        long long perm_sink = fact[sink_count];
        for (pair<int, int> sink : freq_sink) {
            perm_sink *= inv_fact[sink.second];
            perm_sink %= MOD;
        }

        long long res = nck(n, avg_count) * perm_source % MOD * perm_sink % MOD;
        if (source_count <= 1 || sink_count <= 1) {
            res *= nck(source_count + sink_count, source_count);
        } else {
            res *= 2;
        }
        res %= MOD;
        cout << res << endl;
    } else {
        cout << 0 << endl;
    }
}
