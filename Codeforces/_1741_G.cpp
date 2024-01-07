#include <bits/stdc++.h>
using namespace std;

int pow2[7];

void dfs(vector<int> DAG[], bool visited[][7], vector<int> covered[], int status[], pair<int, int> carless[], vector<int> &targets, int bitmask, int ind, int cur) {
    while (ind < targets.size() && cur == carless[targets[ind]].second) {
        ind++;
    }
    if (visited[cur][ind]) {
        return;
    }
    visited[cur][ind] = 1;

    if (ind >= targets.size()) {
        covered[cur].push_back(bitmask);    
    }
    for (int nei : DAG[cur]) {
        dfs(DAG, visited, covered, status, carless, targets, bitmask, ind, nei);
    }
}

void solve() {
    int n, m;
    cin >> n >> m;
    vector<int> graph[n];
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        graph[--u].push_back(--v);
        graph[v].push_back(u);
    }

    vector<int> DAG[n];
    queue<tuple<int, int, int>> bfs;
    int dists[n] = {0};
    // cur, prev, dist
    bfs.push({0, 0, 0});
    while (!bfs.empty()) {
        tuple<int, int, int> cur = bfs.front();
        bfs.pop();

        if (dists[get<0>(cur)] == 0) {
            if (get<0>(cur) != 0) {
                dists[get<0>(cur)] = get<2>(cur);
                DAG[get<1>(cur)].push_back(get<0>(cur));
            }
            for (int nei : graph[get<0>(cur)]) {
                if (nei != 0) {
                    bfs.push({nei, get<0>(cur), get<2>(cur) + 1});
                }
            }
        } else if (dists[get<0>(cur)] == get<2>(cur)) {
            DAG[get<1>(cur)].push_back(get<0>(cur));
        }
    }

    int f;
    cin >> f;
    int friends[f];
    bool has_car[f];
    // status[i] = 0 if no friend lives there, 1 if a friend with car lives there,
    // 2 if a friend with no car lives there.
    int status[n] = {0};
    for (int i = 0; i < f; i++) {
        cin >> friends[i];
        status[--friends[i]] = 1;
        has_car[i] = 1;
    }

    int k;
    cin >> k;
    // stores pairs {distance from 1, node} for each friend without a car.
    pair<int, int> carless[k];
    for (int i = 0; i < k; i++) {
        int ind;
        cin >> ind;
        carless[i] = {dists[friends[--ind]], friends[ind]};
        status[friends[ind]] = 2;
        has_car[ind] = 0;
    }
    sort(carless, carless + k);

    // covered[i] = all of the subsets of carless friends friend i covers
    vector<int> covered[n];
    for (int i = 0; i < pow2[k]; i++) {
        vector<int> subset;
        int val = i;
        for (int j = 0; j < k; j++) {
            if (val & 1 == 1) {
                subset.push_back(j);
            }
            val >>= 1;
        }
        bool visited[n][7];
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < 7; b++) {
                visited[a][b] = 0;
            }
        }
        dfs(DAG, visited, covered, status, carless, subset, i, 0, 0);
    }

    // dp[i][j] = possible to use friends 1...i with cars to send home friends
    // without cars described by bitmask j
    bool dp[f + 1][pow2[k]];
    for (int i = 0; i <= f; i++) {
        for (int j = 0; j < pow2[k]; j++) {
            dp[i][j] = 0;
        }
    }
    dp[0][0] = 1;
    for (int i = 0; i < f; i++) {
        for (int j = 0; j < pow2[k]; j++) {
            if (dp[i][j]) {
                dp[i + 1][j] = 1;
                if (has_car[i]) {
                    for (int bitmask : covered[friends[i]]) {
                        dp[i + 1][j | bitmask] = 1;
                    }
                }
            }
        }
    }

    int res = 0;
    for (int i = 0; i < pow2[k]; i++) {
        if (dp[f][i]) {
            int sent = 0;
            int val = i;
            for (int j = 0; j < k; j++) {
                sent += val & 1;
                val >>= 1;
            }
            res = max(res, sent);
        }
    }
    cout << k - res << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    pow2[0] = 1;
    for (int i = 1; i < 7; i++) {
        pow2[i] = pow2[i - 1] * 2;
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}