#include <bits/stdc++.h>
using namespace std;

const int inf = 1e9;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    vector<int> a;
    for (int i = 0; i < n; i++) {
        int cur;
        cin >> cur; cur--;
        if (a.size() == 0 || cur != a[a.size() - 1]) {
            a.push_back(cur);
        }
    }

    if (a.size() <= 1) {
        cout << 1 << endl;
        return 0;
    }

    vector<int> prev_val(n, -1);
    vector<int> prev;
    for (int i = 0; i < a.size(); i++) {
        prev.push_back(prev_val[a[i]]);
        prev_val[a[i]] = i;
    }

    vector<int> dp(a.size(), inf);
    dp[0] = 1;
    dp[1] = 2;
    for (int i = 2; i < a.size(); i++) {
        if (a[i - 2] == a[i]) {
            dp[i] = dp[i - 1];
        } else {
            dp[i] = dp[i - 1] + 1;
        }
        if (prev[i] != -1) {
            dp[i] = min(dp[i], dp[prev[i] + 1] + i - prev[i] - 2);
        }
    }

    int res = a.size();
    for (int i = 0; i < a.size(); i++) {
        res = min(res, dp[i] + (int) a.size() - i - 1);
    }
    cout << res << endl;
}