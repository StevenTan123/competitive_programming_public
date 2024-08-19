#include <bits/stdc++.h>
using namespace std;

const int MAXN = 30;
const int MAXV = MAXN * 1000000 + 1;
bitset<MAXV * 2> dp;
bitset<MAXV * 2> temp;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N;
    int a[N];
    set<int> seen;
    bool zero = false;
    for (int i = 0; i < N; i++) {
        cin >> a[i];
        if (a[i] == 0 || seen.count(a[i])) {
            zero = true;
        }
        seen.insert(a[i]);
    }

    if (zero || N >= MAXN) {
        cout << "0\n";
    } else {
        for (int i = 0; i < N; i++) {
            temp = dp;
            dp = (dp | (temp >> a[i]));
            dp = (dp | (temp << a[i]));
            dp[a[i] + MAXV] = 1;
            dp[MAXV - a[i]] = 1;
        }
        int res = INT_MAX;
        for (int i = MAXV; i < 2 * MAXV; i++) {
            if (dp[i]) {
                res = min(res, __builtin_popcount(i - MAXV));
            }
        }
        cout << res << "\n";
    }
}