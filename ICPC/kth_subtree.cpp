#include <bits/stdc++.h>
using namespace std;

const long long INF = 1e18 + 5;
const int MAXN = 5005;
vector<int> tree[MAXN];
int sizes[MAXN];
long long dp[MAXN][MAXN] = {{0}};

long long prod(long long a, long long b) {
    long long ceil = INF / b;
    if (INF % b != 0) ceil++;
    if (ceil <= a) {
        return INF;
    }
    return a * b;
}

long long add(long long a, long long b) {
    return min(INF, a + b);
}

void dfs(int cur, int prev) {
    sizes[cur] = 1;
    dp[cur][1] = 1;
    for (int nei : tree[cur]) {
        if (nei != prev) {
            dfs(nei, cur);
            for (int i = sizes[cur]; i >= 1; i--) {
                for (int j = 1; j <= sizes[nei] && i + j < MAXN; j++) {
                    dp[cur][i + j] = add(dp[cur][i + j], prod(dp[cur][i], dp[nei][j]));
                }
            }
            sizes[cur] += sizes[nei];
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    long long K;
    cin >> n >> K;
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        tree[u].push_back(v);
        tree[v].push_back(u);
    }

    dfs(0, -1);
    long long count[n + 1];
    long long psum = 0;
    int res = -1;
    for (int i = 1; i <= n; i++) {
        count[i] = 0;
        for (int j = 0; j < n; j++) {
            count[i] += dp[j][i];
        }
        psum += count[i];
        if (psum >= K) {
            res = i;
            break;
        }
    }
    cout << res << endl;
}