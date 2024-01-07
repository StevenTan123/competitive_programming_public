#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

void solve() {
    int n;
    cin >> n;
    int p[n];
    for (int i = 0; i < n; i++) {
        cin >> p[i];
    }

    stack<int> st;
    st.push(-1);
    int l[n];
    for (int i = 0; i < n; i++) {
        while (st.top() != -1 && p[st.top()] >= p[i]) {
            st.pop();
        }
        l[i] = st.top() + 1;
        st.push(i);
    }
    
    long long dp[n] = {0};
    long long psum[n] = {0};
    long long dp_jump[n] = {0};
    for (int i = 0; i < n; i++) {
        int lb = l[i];
        if (lb == 0) {
            dp[i] = 1;
        }

        dp[i] += (i > 0 ? psum[i - 1] : 0) - (lb > 0 ? psum[lb - 1] : 0);
        dp[i] += (lb > 0 ? dp_jump[lb - 1] : 0);
        dp[i] = (dp[i] + MOD) % MOD;

        dp_jump[i] = ((lb > 0 ? dp_jump[lb - 1] : 0) + dp[i]) % MOD;

        psum[i] = (i > 0 ? psum[i - 1] : 0) + dp[i];
        psum[i] %= MOD;

    }
    
    int min_val = INT_MAX;
    long long res = 0;
    for (int i = n - 1; i >= 0; i--) {
        if (p[i] < min_val) {
            min_val = p[i];
            res += dp[i];
            res %= MOD;
        }
    }
    cout << res << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}