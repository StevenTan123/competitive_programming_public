#include <bits/stdc++.h>
using namespace std;
 
const int MAXN = 200005;
const long long MOD = 998244353;
long long fact[MAXN];
long long inv_fact[MAXN];
 
long long binom(int n, int k) {
	if (k > n) {
		return 0;
	}
	return fact[n] * inv_fact[k] % MOD * inv_fact[n - k] % MOD;
}
 
long long binpow(int a, int b) {
	if (b == 0) {
		return 1;
	}
	long long small = binpow(a, b / 2);
	if (b % 2 == 0) {
		return small * small % MOD;
	} else {
		return small * small % MOD * a % MOD;
	}
}

long long modinv(long long a) {
	return binpow(a, MOD - 2);
}
 
int main() {
	fact[0] = 1;
	for (int i = 1; i < MAXN; i++) {
		fact[i] = fact[i - 1] * i % MOD;
	}
	inv_fact[MAXN - 1] = modinv(fact[MAXN - 1]);
	for (int i = MAXN - 2; i >= 0; i--) {
		inv_fact[i] = inv_fact[i + 1] * (i + 1) % MOD;
	}
 
	int N, K; 
	cin >> N >> K;
 
	int extra = 0;
	if (N > K) {
		extra = N - K;
		N = K;
	}
	// count_arr[i] = # arrays with max length i of non-empty unordered sets
	long long count_arr[MAXN];
	for (int i = 0; i <= N; i++) {
		count_arr[i] = fact[i] * binpow(i + 1, K - i + 1) % MOD;
	}

	long long res = 1;
	for (int i = 1; i <= N; i++) {
		long long cur = (count_arr[i] - count_arr[i - 1] + MOD) % MOD;
		res += cur * fact[i] % MOD * binom(N + extra, i) % MOD;
		res %= MOD;
	}
	cout << res << endl;
}
