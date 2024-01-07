#include <bits/stdc++.h>
using namespace std;

void solve() {
    long long n, P, l, t;
    cin >> n >> P >> l >> t;

    long long ntasks = (n - 1) / 7 + 1;
    long long study = P / (2 * t + l);
    if (P % (2 * t + l) != 0) {
        study++;
    }
    if (study * 2 <= ntasks) {
        cout << n - study << endl;
    } else {
        study = ntasks / 2;
        ntasks -= study * 2;
        P -= study * (2 * t + l);
        if (ntasks > 0) {
            study++;
            P -= t + l;
        }
        if (P > 0) {
            long long ceil = P / l;
            if (P % l != 0) {
                ceil++;
            }
            study += ceil;
        }
        cout << n - study << endl;
    }
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