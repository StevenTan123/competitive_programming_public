#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, x, y;
    cin >> n >> x >> y;
    int chosen[x];
    for (int i = 0; i < x; i++) {
        cin >> chosen[i];
        chosen[i]--;
    }
    sort(chosen, chosen + x);

    int res = 0;
    for (int i = 0; i < x; i++) {
        if ((chosen[i] + 2) % n == chosen[(i + 1) % x]) {
            res++;
        }
    }
    res += x - 2;
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