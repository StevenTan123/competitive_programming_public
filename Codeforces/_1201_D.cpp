#include <bits/stdc++.h>
using namespace std;

typedef map<int, pair<int, int>>::iterator MapIt;
typedef set<int>::iterator SetIt;

pair<long long, long long> transition(int pos, int y_diff, long long dp, set<int> &safe, pair<long long, long long> bounds) {
    long long dpl = LONG_LONG_MAX;
    long long dpr = LONG_LONG_MAX;
    SetIt it = safe.lower_bound(pos);
    if (it != safe.end()) {
        long long cost_l = dp + (*it - pos) + y_diff + (abs(*it - bounds.second) + bounds.second - bounds.first);
        long long cost_r = dp + (*it - pos) + y_diff + (abs(*it - bounds.first) + bounds.second - bounds.first);
        dpl = min(dpl, cost_l);
        dpr = min(dpr, cost_r);
    }
    if (it != safe.begin()) {
        it--;
        long long cost_l = dp + (pos - *it) + y_diff + (abs(*it - bounds.second) + bounds.second - bounds.first);
        long long cost_r = dp + (pos - *it) + y_diff + (abs(*it - bounds.first) + bounds.second - bounds.first);
        dpl = min(dpl, cost_l);
        dpr = min(dpr, cost_r);
    }
    return {dpl, dpr};
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, k, q;
    cin >> n >> m >> k >> q;
    map<int, pair<int, int>> treasure;
    for (int i = 0; i < k; i++) {
        int r, c;
        cin >> r >> c;
        if (treasure.count(r)) {
            treasure[r].first = min(treasure[r].first, c);
            treasure[r].second = max(treasure[r].second, c);
        } else {
            treasure[r] = {c, c};
        }
    }
    set<int> safe;
    for (int i = 0; i < q; i++) {
        int row; cin >> row;
        safe.insert(row);
    }

    int l = 1;
    int r = 1;
    long long dpl = 0;
    long long dpr = 0;
    int prevr = 1;
    MapIt it = treasure.begin();
    if (it->first == 1) {
        l = it->second.first; r = it->second.second;
        dpl = (r - 1) + (r - l); dpr = r - 1;
        it++;
    }
    while (it != treasure.end()) {
        pair<long long, long long> dp_next1 = transition(l, it->first - prevr, dpl, safe, it->second);
        pair<long long, long long> dp_next2 = transition(r, it->first - prevr, dpr, safe, it->second);
        l = it->second.first;
        r = it->second.second;
        dpl = min(dp_next1.first, dp_next2.first);
        dpr = min(dp_next1.second, dp_next2.second);
        prevr = it->first;
        it++;
    }
    cout << min(dpl, dpr) << endl;
}