#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, q;
    cin >> n >> q;
    int a[n];
    int pre[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        pre[i] = (i > 0 ? pre[i - 1] : 0) ^ a[i];
    }

    tuple<int, int, int> queries[q];
    tuple<int, int, int> queries2[q];
    for (int i = 0; i < q; i++) {
        int l, r;
        cin >> l >> r;
        queries[i] = {r - 1, l - 1, i};
        queries2[i] = {l - 1, r - 1, i};
    }
    sort(queries, queries + q);
    sort(queries2, queries2 + q);

    vector<bool> possible(q, 0);
    vector<int> left(q, -1);
    
    map<int, int> prev_xor;
    prev_xor[0] = -1;
    int p = 0;
    for (int i = 0; i < n; i++) {
        while (p < q && get<0>(queries[p]) == i) {
            auto [r, l, ind] = queries[p];
            int xor_range = pre[r] ^ (l > 0 ? pre[l - 1] : 0);

            if (xor_range == 0) {
                possible[ind] = 1;
            } else {
                int target = pre[i] ^ xor_range;
                if (prev_xor.count(target) && prev_xor[target] + 1 >= l) {
                    left[ind] = prev_xor[target] + 1;
                }
            }
            p++;
        }
        prev_xor[pre[i]] = i;
    }

    map<int, int> next_xor;
    next_xor[0] = n;
    p = q - 1;
    int cxor = 0;
    for (int i = n - 1; i >= 0; i--) {
        cxor ^= a[i];
        while (p >= 0 && get<0>(queries2[p]) == i) {
            auto [l, r, ind] = queries2[p];
            int xor_range = pre[r] ^ (l > 0 ? pre[l - 1] : 0);

            if (!possible[ind]) {
                int target = cxor ^ xor_range;
                if (next_xor.count(target)) {
                    if (next_xor[target] - 1 < left[ind]) {
                        possible[ind] = 1;
                    }
                }
            }
            p--;
        }
        next_xor[cxor] = i;
    }

    for (int i = 0; i < q; i++) {
        cout << (possible[i] ? "YES\n" : "NO\n");
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