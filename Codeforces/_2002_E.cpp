#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    vector<int> b(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i] >> b[i];
    }

    // stk stores pairs of {time, last value}
    deque<pair<long long, int>> stk;
    for (int i = 0; i < n; i++) {
        long long cur_len = a[i];
        long long prev_time = 0;
        while (!stk.empty()) {
            if (stk.front().second != b[i]) {
                if (stk.front().first > cur_len) {
                    break;
                }
            } else {
                cur_len = cur_len + stk.front().first - prev_time;
            }
            prev_time = stk.front().first;
            stk.pop_front();
        }
        stk.emplace_front(cur_len, b[i]);
        cout << stk.back().first << " ";
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