#include <bits/stdc++.h>
using namespace std;

int third(int a, int b) {
    if (a == b) {
        return a;
    }
    for (int i = 0; i < 3; i++) {
        if (i != a && i != b) {
            return i;
        }
    }
    return -1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k;
    cin >> n >> k;
    vector<vector<int>> cards(n, vector<int>(k));
    set<long long> hashes;
    for (int i = 0; i < n; i++) {
        long long hash = 0;
        for (int j = 0; j < k; j++) {
            cin >> cards[i][j];
            hash = hash * 3 + cards[i][j];
        }
        hashes.insert(hash);
    }

    int res = 0;
    for (int i = 0; i < n; i++) {
        int cnt = 0;
        for (int j = 0; j < n; j++) {
            if (j != i) {
                long long hash = 0;
                for (int x = 0; x < k; x++) {
                    hash = hash * 3 + third(cards[i][x], cards[j][x]);
                }
                if (hashes.count(hash)) {
                    cnt++;
                }
            }
        }
        cnt /= 2;
        res += cnt * (cnt - 1) / 2;
    }
    cout << res << "\n";
}