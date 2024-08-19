#include <bits/stdc++.h>
using namespace std;

long long solve_subarray(vector<int> &rbs, int l, int r) {
    if (l == r) {
        return 1;
    }
    int m = (l + r) / 2;
    long long left = solve_subarray(rbs, l, m);
    long long right = solve_subarray(rbs, m + 1, r);

    vector<int> sort_left_b;
    vector<tuple<int, int, int>> sort_left;
    int balance = 0;
    int max_balance = 0;
    for (int i = m; i >= l; i--) {
        balance += rbs[i];
        max_balance = max(max_balance, balance);
        int min_balance = balance - max_balance;
        sort_left_b.push_back(balance);
        sort_left.emplace_back(min_balance - balance, balance, min_balance);
    }
    sort(sort_left_b.begin(), sort_left_b.end());
    sort(sort_left.begin(), sort_left.end());

    vector<int> sort_right_b;
    vector<int> sort_right_m;
    balance = 0;
    int min_balance = 0;
    for (int i = m + 1; i <= r; i++) {
        balance += rbs[i];
        min_balance = min(min_balance, balance);
        sort_right_b.push_back(balance);
        sort_right_m.push_back(min_balance);
    }
    sort(sort_right_b.begin(), sort_right_b.end());
    sort(sort_right_m.begin(), sort_right_m.end());

    long long pos_balance_sum = 0;
    long long suf_sum = 0;
    int rp = sort_right_b.size() - 1;
    for (int i = 0; i < sort_left_b.size(); i++) {
        int l_balance = sort_left_b[i];
        while (rp >= 0 && sort_right_b[rp] + l_balance >= 0) {
            suf_sum += sort_right_b[rp];
            rp--;
        }
        pos_balance_sum += suf_sum + (long long) l_balance * (sort_right_b.size() - rp - 1);
    }

    // m_l < m_r + b_l
    long long min_balance_sum = 0;
    long long pref_sum = 0;
    rp = 0;
    for (int i = 0; i < sort_left.size(); i++) {
        auto [l_val, l_balance, l_min] = sort_left[i];
        while (rp < sort_right_m.size() && l_val >= sort_right_m[rp]) {
            pref_sum += sort_right_m[rp];
            rp++;
        }
        min_balance_sum += -(pref_sum + (long long) l_balance * rp);
        min_balance_sum += (long long) -l_min * (sort_right_m.size() - rp);
    }
    return left + right + pos_balance_sum + min_balance_sum;
}

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    vector<int> rbs(n);
    for (int i = 0; i < n; i++) {
        rbs[i] = (s[i] == '(' ? 1 : -1);
    }
    long long res = solve_subarray(rbs, 0, n - 1);
    cout << res << "\n";
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