#include <bits/stdc++.h>
using namespace std;

int mod(int a, int b) {
    if (a < 0) {
        a += b;
    } else if (a >= b) {
        a -= b;
    }
    return a;
}

void solve() {
    int n, k, w, h;
    cin >> n >> k >> w >> h;
    string s;
    cin >> s;

    map<pair<int, int>, int> cnts;
    int x = 0;
    int y = 0;
    for (int i = 0; i < n; i++) {
        if (s[i] == 'U') {
            y = mod(y + 1, 2 * h);
        } else if (s[i] == 'D') {
            y = mod(y - 1, 2 * h);
        } else if (s[i] == 'R') {
            x = mod(x + 1, 2 * w);
        } else {
            x = mod(x - 1, 2 * w);
        }
        cnts[{x, y}]++;
    }

    int xx = 0;
    int yy = 0;
    long long res = 0;
    for (int i = 0; i < k; i++) {
        int need_x = mod(2 * w - xx, 2 * w);
        int need_y = mod(2 * h - yy, 2 * h);
        res += cnts[{need_x, need_y}];

        xx = mod(xx + x, 2 * w);
        yy = mod(yy + y, 2 * h);
    }
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