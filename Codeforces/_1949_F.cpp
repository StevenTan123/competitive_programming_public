#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    vector<int> acts[n];
    pair<int, int> sorted[n];
    for (int i = 0; i < n; i++) {
        int k;
        cin >> k;
        for (int j = 0; j < k; j++) {
            int act;
            cin >> act;
            acts[i].push_back(act - 1);
        }
        sorted[i] = {k, i};
    }
    sort(sorted, sorted + n);

    vector<int> prev(m, -1);
    pair<int, int> res = {-1, -1};
    for (int i = n - 1; i >= 0; i--) {
        int ind = sorted[i].second;
        int min_len = -1;
        set<int> unique;
        for (int act : acts[ind]) {
            if (prev[act] != -1) {
                if (min_len == -1 || acts[prev[act]].size() < acts[min_len].size()) {
                    min_len = prev[act];
                }
            }
            unique.insert(prev[act]);
            prev[act] = ind;
        }
        if (unique.size() > 1) {
            res = {min_len, ind};
            break;
        }
    }
    if (res.first == -1) {
        cout << "NO\n";
    } else {
        cout << "YES\n";
        cout << res.first + 1 << " " << res.second + 1 << "\n";
    }
}