#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;
    tuple<int, int, int> costa[n];
    tuple<int, int, int> costb[n];
    int a[n];
    int b[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> b[i];
    }
    for (int i = 0; i < n; i++) {
        costa[i] = {a[i], b[i], i};
        costb[i] = {b[i], a[i], i};
    }
    sort(costa, costa + n);
    sort(costb, costb + n);

    if (k == 0) {
        long long res = 0;
        for (int i = 0; i < n; i++) {
            res += max(0, b[i] - a[i]);
        }
        cout << res << "\n";
        return;
    }

    long long k_sum = 0;
    vector<bool> top_k(n, 0);
    deque<int> dq;
    for (int i = 0; i < n; i++) {
        auto [ai, bi, ind] = costa[i];
        if (i < k) {
            top_k[ind] = 1;
            k_sum += ai;
        } else {
            dq.push_back(ind);
        }
    }

    long long diff_sum = 0;
    long long max_profit = 0;
    vector<bool> gone(n, 0);
    for (int i = 0; i < n; i++) {
        auto [bi, ai, ind] = costb[i];
        max_profit = max(max_profit, diff_sum - k_sum);
        
        if (top_k[ind]) {
            k_sum -= ai;
            while (!dq.empty() && gone[dq.front()]) {
                dq.pop_front();
            }
            if (dq.empty()) {
                break;
            }
            int next_ind = dq.front();
            dq.pop_front();
            k_sum += a[next_ind];
            top_k[next_ind] = 1;
        } else {
            gone[ind] = 1;
        }

        diff_sum += max(bi - ai, 0);
    }
    cout << max_profit << "\n";
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