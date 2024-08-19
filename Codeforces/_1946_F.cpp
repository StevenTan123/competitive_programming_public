#include <bits/stdc++.h>
using namespace std;

struct BIT {
    long long *bit;
    int len;
    BIT(int n) {
        bit = new long long[n + 1];
        len = n + 1;
        for (int i = 0; i <= n; i++) {
            bit[i] = 0;
        }
    }
    ~BIT() {
        delete[] bit;
    }
    void update(int index, long long add) {
        index++;
        while (index < len) {
            bit[index] += add;
            index += index & -index;
        }
    }
    long long psum(int index) {
        index++;
        long long res = 0;
        while (index > 0) {
            res += bit[index];      
            index -= index & -index;
        }
        return res;
    }
};

void solve() {
    int n, q;
    cin >> n >> q;
    int a[n];
    int pos[n + 1];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        pos[a[i]] = i;
    }

    tuple<int, int, int> queries[q];
    for (int i = 0; i < q; i++) {
        int l, r;
        cin >> l >> r;
        queries[i] = {l - 1, r - 1, i};
    }
    sort(queries, queries + q);

    // bit[i] = # of subsequences starting after L and ending at i.
    BIT bit(n);
    int q_pointer = q - 1;
    long long res[q];
    for (int L = n - 1; L >= 0; L--) {
        vector<long long> dp(n / a[L]);
        dp[0] = 1;
        for (int i = a[L] * 2; i <= n; i += a[L]) {
            if (pos[i] > L) {
                dp[(i - a[L]) / a[L]] += dp[0];
                for (int j = i; j <= n; j += i) {
                    if (pos[j] > pos[i]) {
                        dp[(j - a[L]) / a[L]] += dp[(i - a[L]) / a[L]];
                    }
                }
            }
        }
        for (int i = 0; i < n / a[L]; i++) {
            int ind = pos[a[L] + i * a[L]];
            bit.update(ind, dp[i]);
        }

        while (q_pointer >= 0 && get<0>(queries[q_pointer]) == L) {
            res[get<2>(queries[q_pointer])] = bit.psum(get<1>(queries[q_pointer]));
            q_pointer--;
        }
    }
    
    for (int i = 0; i < q; i++) {
        cout << res[i] << " ";
    }
    cout << "\n";
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