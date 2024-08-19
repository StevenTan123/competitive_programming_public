#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    multiset<long long> even;
    multiset<long long> odd;
    for (int i = 0; i < n; i++) {
        int ai;
        cin >> ai;
        if (ai % 2 == 0) {
            even.insert(ai);
        } else {
            odd.insert(ai);
        }
    }
    if (even.empty() || odd.empty()) {
        cout << "0\n";
        return;
    }

    int cnt = 0;
    while (!even.empty()) {
        auto first_even = even.begin();
        auto last_odd = prev(odd.end());
        if (*last_odd > *first_even) {
            odd.insert(*odd.rbegin() + *first_even);
            even.erase(first_even);
        } else {
            auto last_even = prev(even.end());
            long long odd_val = *last_odd;
            odd.erase(last_odd);
            odd.insert(odd_val + *last_even);
        }
        cnt++;
    }
    cout << cnt << "\n";
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