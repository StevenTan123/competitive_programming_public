#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;
    pair<int, int> blocked[m];
    for (int i = 0; i < m; i++) {
        cin >> blocked[i].second >> blocked[i].first;
    }
    sort(blocked, blocked + m);

    // stores if it's possible to fill up to current col {levelled, one above, one below}
    tuple<bool, bool, bool> possible = {1, 0, 0};
    int prevx = 0;
    for (int i = 0; i < m; i++) {
        if (i < m - 1 && blocked[i].first == blocked[i + 1].first) {
            continue;
        }

        int gap = blocked[i].first - prevx;
        bool level = get<0>(possible); bool above = get<1>(possible); bool below = get<2>(possible);
        tuple<bool, bool, bool> next;
        if (i > 0 && blocked[i].first == blocked[i - 1].first) {
            next = {level, 0, 0};
        } else if (blocked[i].second == 1) {
            next = {(below && gap % 2 == 1) || (above && gap % 2 == 0), 0, level};
        } else {
            next = {(above && gap % 2 == 1) || (below && gap % 2 == 0), level, 0};
        }
        possible = next;
        prevx = blocked[i].first;
    }
    cout << (get<0>(possible) ? "YES\n" : "NO\n");
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