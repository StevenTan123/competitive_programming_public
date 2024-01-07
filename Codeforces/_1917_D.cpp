#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
const int MAXLOG = 30;
int pow2[MAXLOG];

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
            res %= MOD;
            index -= index & -index;
        }
        return res;
    }
    long long sum(int l, int r) {
        long long ret = psum(r) - (l > 0 ? psum(l - 1) : 0);
        ret = (ret + MOD) % MOD;
        return ret;
    }
};

long long count_bet_inv(int n, int k, int log2n, int p[], pair<int, int> p_sorted[], bool dir) {
    int p_inds[2 * n];
    for (int i = 0; i < n; i++) {
        p_inds[p[i]] = i;
    }
  
    // pointers[i] stores the largest x s.t. 2^i * x < y
    vector<int> pointers(log2n + 1, -1);

    long long ret = 0;
    BIT bit(n);
    for (int i = 0; i < n; i++) {
        for (int j = 1; j <= log2n; j++) {
            while ((pointers[j] + 2) * pow2[j] < p_sorted[i].first) {
                pointers[j] += 2;
                if (k - j > 0) {
                    bit.update(p_inds[pointers[j]], -(k - j));
                }
            }
        }
        if (dir) {
            ret += bit.psum(p_sorted[i].second);
        } else {
            ret += bit.sum(p_sorted[i].second, n - 1);
        }
        bit.update(p_sorted[i].second, (long long) k * (k - 1) / 2);
    }
    return ret;
}

long long count_inversions(int n, pair<int, int> sorted[]) {
    BIT bit(n);
    long long count = 0;
    for (int i = 0; i < n; i++) {
        count += bit.sum(sorted[i].second, n - 1);
        count %= MOD;
        bit.update(sorted[i].second, 1);
    }
    return count;
}

void solve() {
    int n, k;
    cin >> n >> k;
    int p[n];
    int q[k];
    pair<int, int> p_sorted[n];
    pair<int, int> q_sorted[k];
    for (int i = 0; i < n; i++) {
        cin >> p[i];
        p_sorted[i] = {p[i], i};
    }
    for (int i = 0; i < k; i++) {
        cin >> q[i];
        q_sorted[i] = {q[i], i};
    }
    sort(p_sorted, p_sorted + n);
    sort(q_sorted, q_sorted + k);
    
    int log2n = 0;
    for (int i = 0; i < MAXLOG; i++) {
        if (pow2[i] > 2 * n) {
            break;
        }
        log2n = i;
    }
    
    long long in_count = count_inversions(k, q_sorted) * n % MOD;
    long long bet_inv_left = count_bet_inv(n, k, log2n, p, p_sorted, true);
    long long bet_non_inv_right = count_bet_inv(n, k, log2n, p, p_sorted, false); 
    long long bet_tot_inv = count_inversions(n, p_sorted) * k % MOD * k % MOD;
    long long res = in_count + bet_inv_left + bet_tot_inv - bet_non_inv_right;
    res = (res % MOD + MOD) % MOD;
    cout << res << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    pow2[0] = 1;
    for (int i = 1; i < MAXLOG; i++) {
        pow2[i] = pow2[i - 1] * 2;
    }                              
    
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}
