#include <bits/stdc++.h>
using namespace std;

int MAXLOG = 20;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    pair<int, int> segs[n];
    for (int i = 0; i < n; i++) {
        int l, r;
        cin >> l >> r;
        segs[i] = {l, l - r};
    }
    sort(segs, segs + n);

    set<int> right;
    vector<pair<int, int>> useful;
    for (int i = 0; i < n; i++) {
        int r = segs[i].first - segs[i].second;
        if (right.empty() || *right.rbegin() < r) {
            useful.push_back({segs[i].first, r});
            right.insert(r);
        }
    }

    set<pair<int, int>> l_inds;
    set<pair<int, int>> r_inds;
    for (int i = 0; i < (int) useful.size(); i++) {
        l_inds.insert({useful[i].first, i});
        r_inds.insert({useful[i].second, i});
    }

    int next[useful.size()];
    for (int i = 0; i < useful.size(); i++) {
        next[i] = -1;
        auto it = l_inds.lower_bound({useful[i].second + 1, -1});
        if (it != l_inds.begin()) {
            it--;
            if (it->second > i) {
                next[i] = it->second;
            }
        }
    }

    // jump[i][j] = rightmost segment reached starting with segment i,
    // using 2^j segments.
    int jump[useful.size()][MAXLOG];
    for (int i = useful.size() - 1; i >= 0; i--) {
        jump[i][0] = next[i];
        for (int j = 1; j < MAXLOG; j++) {
            if (jump[i][j - 1] == -1) {
                jump[i][j] = -1;
            } else {
                jump[i][j] = jump[jump[i][j - 1]][j - 1];
            }
        }
    }
    
    for (int i = 0; i < m; i++) {
        int x, y;
        cin >> x >> y;
        auto l = l_inds.lower_bound({x + 1, -1});
        if (l != l_inds.begin()) {
            l--;
            int ind = l->second;
            if (useful[ind].second >= y) {
                cout << "1\n";
            } else {
                int res = 0;
                for (int j = MAXLOG - 1; j >= 0; j--) {
                    if (jump[ind][j] != -1 && useful[jump[ind][j]].second < y) {
                        ind = jump[ind][j];
                        res += 1 << j;
                    }
                }
                if (jump[ind][0] != -1 && useful[jump[ind][0]].second >= y) {
                    cout << res + 2 << "\n";
                } else {
                    cout << "-1\n";
                }
            }
        } else {
            cout << "-1\n";
        }
    }
}