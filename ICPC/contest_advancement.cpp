#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k, c;
    cin >> n >> k >> c;
    pair<int, int> teams[n];
    for (int i = 0; i < n; i++) {
        cin >> teams[i].first >> teams[i].second;
        teams[i].second--;
    }
    vector<bool> advanced(n, 0);
    vector<pair<int, int>> order;
    int school_cnt[n] = {0};
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        if (cnt >= k) {
            break;
        }
        if (school_cnt[teams[i].second] < c) {
            advanced[i] = 1;
            order.push_back({i, teams[i].first});
            school_cnt[teams[i].second]++;
            cnt++;
        }
    }
    for (int i = 0; i < n; i++) {
        if (cnt >= k) {
            break;
        }
        if (!advanced[i]) {
            order.push_back({i, teams[i].first});
            cnt++;
        }
    }
    sort(order.begin(), order.end());
    for (pair<int, int> team : order) {
        cout << team.second << "\n";
    }
}