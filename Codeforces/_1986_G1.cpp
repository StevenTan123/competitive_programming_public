#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int p[n];
    int p_red[n];
    int ind_red[n];
    map<int, int> cnts[n + 1];
    for (int i = 0; i < n; i++) {
        cin >> p[i];
        int gcd_val = __gcd(p[i], i + 1);
        p_red[i] = p[i] / gcd_val;
        ind_red[i] = (i + 1) / gcd_val;

        for (int j = 1; j * j <= p_red[i]; j++) {
            if (p_red[i] % j == 0) {
                cnts[ind_red[i]][j]++;
                if (j * j != p_red[i]) {
                    cnts[ind_red[i]][p_red[i] / j]++;
                }
            }
        }
    }

    long long res = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 1; j * j <= p_red[i]; j++) {
            if (p_red[i] % j == 0) {
                res += cnts[j][ind_red[i]];
                if (j * j != p_red[i]) {
                    res += cnts[p_red[i] / j][ind_red[i]];
                }
            }
        }
        if (ind_red[i] == 1) {
            res--;
        }
    }
    res /= 2;
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