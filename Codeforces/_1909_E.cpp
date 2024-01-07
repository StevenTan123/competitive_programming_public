#include <bits/stdc++.h>
using namespace std;

const int MAXN = 20;
int pow2[MAXN];
vector<int> bitmasks[MAXN];

bool bit_set(int val, int bit) {
    return (val >> bit) & 1;
}

void solve() {
    int n, m;
    cin >> n >> m;
    vector<pair<int, int>> edges;
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        edges.push_back({u, v});
    }

    if (n < 5) {
        cout << "-1\n";
    } else if (n >= 20) {
        cout << n << "\n";
        for (int i = 1; i <= n; i++) {
            cout << i << " ";
        }
        cout << "\n";
    } else {
        int res = -1;
        int num = -1;
        for (int bitmask : bitmasks[n]) {
            bool valid = true;
            for (int j = 0; j < m; j++) {
                if (bit_set(bitmask, edges[j].first - 1) && !bit_set(bitmask, edges[j].second - 1)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                int count = 0;
                int val = bitmask;
                for (int j = 0; j < n; j++) {
                    count += val % 2;
                    val /= 2;
                }
                res = bitmask;
                num = count; 
                break;
            }
        }
        if (res == -1) {
            cout << "-1\n";
        } else {
            cout << num << "\n";
            for (int j = 0; j < n; j++) {
                if (res % 2 == 1) {
                    cout << j + 1 << " ";
                }
                res /= 2;
            }
            cout << "\n";
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    pow2[0] = 1;
    for (int i = 1; i < MAXN; i++) {
        pow2[i] = pow2[i - 1] * 2;
        if (i >= 5) {
            for (int j = 1; j < pow2[i]; j++) {
                bool on[i + 1] = {0};
                int val = j;
                for (int k = 0; k < i; k++) {
                    if (val % 2 == 1) {
                        for (int l = k + 1; l <= i; l += k + 1) {
                            on[l] = !on[l];
                        }
                    }
                    val /= 2;
                }
                int count = 0;
                for (int k = 0; k <= i; k++) {
                    count += on[k];
                }
                if (count <= i / 5) {
                    bitmasks[i].push_back(j);
                }
            }
        }
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}