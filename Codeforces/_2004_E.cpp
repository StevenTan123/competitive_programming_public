#include <bits/stdc++.h>
using namespace std;

const int MAXN = 10000005;
vector<int> spf(MAXN);
vector<int> grundy(MAXN);

void solve() {
    int n;
    cin >> n;
    int xor_all = 0;
    for (int i = 0; i < n; i++) {
        int ai;
        cin >> ai;
        xor_all ^= grundy[ai];
    }
    cout << (xor_all == 0 ? "Bob" : "Alice") << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 2; i < MAXN; i++) {
        for (int j = i; j < MAXN; j += i) {
            if (spf[j] == 0) {
                spf[j] = i;
            }
        }
    }

    grundy[1] = 1;
    int prime_val = 2;
    for (int i = 3; i < MAXN; i += 2) {
        if (spf[i] == i) {
            grundy[i] = prime_val++;
        } else {
            grundy[i] = grundy[spf[i]];
        }
    }
    
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}