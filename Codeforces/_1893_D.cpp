#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;
    int colors[n] = {0};
    for (int i = 0; i < n; i++) {
        int color;
        cin >> color;
        colors[color - 1]++;
    }
    // priority queue of pairs of {count, color}, used so we can greedily first fill
    // colors that have the most cubes
    priority_queue<pair<int, int>> pq;
    for (int i = 0; i < n; i++) {
        if (colors[i] > 0) {
            pq.push({colors[i], i});
        }
    }
    vector<int> res[m];
    int s[m];
    int d[m];
    for (int i = 0; i < m; i++) {
        cin >> s[i];
        res[i].resize(s[i], -1);
    }
    for (int i = 0; i < m; i++) {
        cin >> d[i];
    }

    bool possible = true;
    for (int i = 0; i < m; i++) {
        map<int, int> inactive;
        for (int j = 0; j < s[i]; j++) {
            if (j >= d[i]) {
                if (inactive.count(res[i][j - d[i]])) {
                    pq.push({inactive[res[i][j - d[i]]], res[i][j - d[i]]});
                    inactive.erase(res[i][j - d[i]]);
                }
            }
            if (pq.empty()) {
                possible = false;
                break;
            }
            pair<int, int> use = pq.top();
            pq.pop();
            res[i][j] = use.second;
            if (use.first > 1) {
                inactive[use.second] = use.first - 1;
            }
        }
        if (possible == false) {
            break;
        }
        for (auto it = inactive.begin(); it != inactive.end(); it++) {
            pq.push({it->second, it->first});
        }
    }

    if (possible) {
        for (int i = 0; i < m; i++) {
            for (int color : res[i]) {
                cout << (color + 1) << " ";
            }
            cout << "\n";
        }
    } else {
        cout << "-1\n";
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
