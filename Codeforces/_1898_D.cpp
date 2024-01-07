#include <bits/stdc++.h>
using namespace std;

const int inf = 1e9 + 5;

void solve() {
    int n;
    cin >> n;
    pair<int, int> pairs[n];
    long long res = 0;
    for (int i = 0; i < n; i++) {
        cin >> pairs[i].second;
    }
    for (int i = 0; i < n; i++) {
        cin >> pairs[i].first;
        res += abs(pairs[i].first - pairs[i].second);
    }
    sort(pairs, pairs + n);

    int mina_less = inf;
    int mina_more = inf;
    int minb_less = inf;
    int minb_more = inf;
    long long max_imp = 0;
    for (int i = 0; i < n; i++) {
        int a = pairs[i].second;
        int b = pairs[i].first;

        if (a >= b) {
            max_imp = max(max_imp, (long long) 2 * (b - minb_less));
            max_imp = max(max_imp, (long long) 2 * (b - mina_more));
        }
        if (a <= b) {
            max_imp = max(max_imp, (long long) 2 * (a - minb_less));
            max_imp = max(max_imp, (long long) 2 * (a - mina_more));
        }

        if (a <= b) {
            mina_less = min(mina_less, a);
            minb_less = min(minb_less, b);
        }
        if (a >= b) {
            mina_more = min(mina_more, a);
            minb_more = min(minb_more, b);
        }
    }
    cout << res + max_imp << endl;
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