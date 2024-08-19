#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
const int MAXN = 500005;

long long inv[MAXN];

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
    long long res = 1;
    for (int i = n; i > n - k; i--) {
        res *= i;
        res %= MOD;
    }
    for (int i = 1; i <= k; i++) {
        res *= inv[i];
        res %= MOD;
    }
    return res;    
}

void dfs(int BST[][2], int a[], vector<int> &order, int cur) {
    if (cur < 0) return;
    dfs(BST, a, order, BST[cur][0]);
    order.push_back(a[cur]);
    dfs(BST, a, order, BST[cur][1]);
}

void solve() {
    int n, C;
    cin >> n >> C;
    int BST[n][2];
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> BST[i][0] >> BST[i][1] >> a[i];
        BST[i][0]--; BST[i][1]--;
    }
    vector<int> order;
    dfs(BST, a, order, 0);
    int prev = -1;
    long long ways = 1;
    for (int i = 0; i <= order.size(); i++) {
        if (i == order.size() || order[i] >= 1) {
            int lower = (prev == -1 ? 1 : order[prev]);
            int higher = (i < order.size() ? order[i] : C);
            long long prod = nck(i - prev - 1 + higher - lower, i - prev - 1);
            ways *= prod;
            ways %= MOD;
            prev = i;
        }
    }
    cout << ways << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 1; i < MAXN; i++) {
        inv[i] = modinv(i);
    }

    int t;
    cin >> t;
    while (t-- > 0) {
        solve();
    }
}