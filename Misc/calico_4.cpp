#include <bits/stdc++.h>
using namespace std;

bool possible(int N, int K, vector<int> &inds, int x) {
    if (x > (int) inds.size()) {
        return false;
    }
    for (int i = 0; i < (int) inds.size(); i++) {
        if (i < x && inds[i] > K) {
            return false;
        } else if (i + x < (int) inds.size() && inds[i + x] > inds[i] + K + 1) {
            return false;
        } else if (i + x >= (int) inds.size() && inds[i] + K + 1 < N) {
            return false;
        }
    }
    return true;
}

void solve() {
    int N, K;
    cin >> N >> K;
    string S;
    cin >> S;

    if (K >= N) {
        cout << "-1\n";
        return;
    }

    vector<int> inds;
    for (int i = 0; i < N; i++) {
        if (S[i] == '#') {
            inds.push_back(i);
        }
    }

    int l = 0;
    int r = inds.size();
    int res = 0;
    while (l <= r) {
        int m = (l + r) / 2;
        if (possible(N, K, inds, m)) {
            res = m;
            l = m + 1;
        } else {
            r = m - 1;
        }
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