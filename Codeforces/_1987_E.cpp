#include <bits/stdc++.h>
using namespace std;

void dfs(vector<int> graph[], deque<pair<long long, long long>> adds[], long long res[], int a[], int cur) {
    long long sum = 0;
    res[cur] = 0;
    for (int nei : graph[cur]) {
        dfs(graph, adds, res, a, nei);
        sum += a[nei];
        res[cur] += res[nei];
        for (auto [cost, cnt] : adds[nei]) {
            adds[cur].emplace_back(cost, cnt);
        }
    }
    sort(adds[cur].begin(), adds[cur].end());
    if (!graph[cur].empty() && a[cur] > sum) {
        long long diff = a[cur] - sum;
        while (!adds[cur].empty()) {
            auto [cost, cnt] = adds[cur].front();
            if (cnt <= diff) {
                res[cur] += cnt * cost;
                diff -= cnt;
                adds[cur].pop_front();
            } else {
                res[cur] += diff * cost;
                adds[cur].front().second -= diff;
                diff = 0;
            }
            if (diff == 0) {
                break;
            }
        }
    }

    for (int i = 0; i < adds[cur].size(); i++) {
        adds[cur][i].first++;
    }
    if (sum > a[cur]) {
        adds[cur].emplace_front(1, sum - a[cur]);
    } else if (graph[cur].empty()) {
        adds[cur].emplace_back(1, LONG_LONG_MAX);
    }
}

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    vector<int> graph[n];
    for (int i = 1; i < n; i++) {
        int pi;
        cin >> pi;
        graph[pi - 1].push_back(i);
    }

    deque<pair<long long, long long>> adds[n];
    long long res[n];
    dfs(graph, adds, res, a, 0);
    cout << res[0] << "\n";
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