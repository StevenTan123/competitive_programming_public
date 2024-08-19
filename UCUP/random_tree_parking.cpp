#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
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

void dfs(vector<vector<int>> &graph, vector<int> &depth, vector<int> &sizes, int cur) {
    sizes[cur] = 1;
    for (int child : graph[cur]) {
        depth[child] = depth[cur] + 1;
        dfs(graph, depth, sizes, child);
        sizes[cur] += sizes[child];
    }
}

void dfs_dp(vector<vector<int>> &graph, vector<int> &depth, vector<int> &sizes, vector<vector<long long>> &dp, int cur) {
    dp[cur].resize(depth[cur]);
    vector<long long> cur_dp(depth[cur] + 1);
    cur_dp[0] = 1;
    long long size_sum = 0;
    for (int child : graph[cur]) {
        dfs_dp(graph, depth, sizes, dp, child);
        vector<long long> next_dp(depth[cur] + 1);
        for (int i = 0; i <= depth[cur]; i++) {
            for (int j = 0; i + j <= depth[cur]; j++) {
                next_dp[i + j] += cur_dp[i] * dp[child][j] % MOD * 
                        nck(size_sum + i + sizes[child] + j, sizes[child] + j) % MOD;
                next_dp[i + j] %= MOD;
            }
        }
        size_sum += sizes[child];
        cur_dp = next_dp;
    }

    for (int i = 0; i <= depth[cur]; i++) {
        for (int j = 0; j <= depth[cur]; j++) {
            if (i + j >= 1 && i + j <= depth[cur]) {
                dp[cur][i + j - 1] += cur_dp[i] * nck(size_sum + i + j, j) % MOD;
                dp[cur][i + j - 1] %= MOD;
            }
        }
    }
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
    vector<int> par(n);
    vector<vector<int>> graph(n);
    for (int i = 1; i < n; i++) {
        cin >> par[i];
        par[i]--;
        graph[par[i]].push_back(i);
    }

    vector<int> depth(n);
    vector<int> sizes(n);
    depth[0] = 1;
    dfs(graph, depth, sizes, 0);

    vector<vector<long long>> dp(n);
    dfs_dp(graph, depth, sizes, dp, 0);
    cout << dp[0][0] << endl;
}