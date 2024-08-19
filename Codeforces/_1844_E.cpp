#include <bits/stdc++.h>
using namespace std;

bool colorable(vector<pair<int, bool>> graph[], vector<int> &marks, int cur, int mark) {
    if (marks[cur] != 0) {
        return mark == marks[cur];
    }
    marks[cur] = mark;
    for (auto [to, diff] : graph[cur]) {
        int next_mark = (diff ? 3 - mark : mark);
        if (!colorable(graph, marks, to, next_mark)) {
            return false;
        }
    }
    return true;
}

void solve() {
    int n, m, k;
    cin >> n >> m >> k;
    int N = n - 1 + m - 1;
    vector<pair<int, bool>> graph[N];
    for (int i = 0; i < k; i++) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        x1--; y1--; x2--; y2--;
        if (y2 == y1 + 1) {
            graph[x1].emplace_back(n - 1 + y1, 1);
            graph[n - 1 + y1].emplace_back(x1, 1);
        } else {
            graph[x1].emplace_back(n - 1 + y2, 0);
            graph[n - 1 + y2].emplace_back(x1, 0);
        }
    }

    vector<int> marks(N);
    bool possible = true;
    for (int i = 0; i < N; i++) {
        if (marks[i] == 0) {
            if (!colorable(graph, marks, i, 1)) {
                possible = false;
                break;
            }
        }
    }
    cout << (possible ? "YES\n" : "NO\n");
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