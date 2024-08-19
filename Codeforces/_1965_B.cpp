#include <bits/stdc++.h>
using namespace std;

const int MAXB = 21;

void solve() {
    int n, k;
    cin >> n >> k;
    int most_sig = 0;
    for (int i = 0; i < MAXB; i++) {
        if (k & (1 << i)) {
            most_sig = i;
        }
    }
    vector<int> res;
    for (int i = 0; i < MAXB; i++) {
        if (i != most_sig) {
            res.push_back((1 << i));
        }
    }
    if (k - (1 << most_sig) > 0) {
        res.push_back(k - (1 << most_sig));
    }
    res.push_back(k + 1);
    res.push_back(k + (1 << most_sig) + 1);
    cout << res.size() << "\n";
    for (int val : res) {
        cout << val << " ";
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