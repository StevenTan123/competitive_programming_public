#include <bits/stdc++.h>
using namespace std;

const int MAXN = 5005;
bitset<MAXN> before[MAXN];
bitset<MAXN> cur;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int m, n;
    cin >> m >> n;
    int p[n];
    for (int i = 0; i < n; i++) {
        cin >> p[i];
    }
    pair<int, int> rank[m][n];
    vector<int> models[n];
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            cin >> rank[i][j].first;
            rank[i][j].second = j;
            models[j].push_back(rank[i][j].first);
        }
        sort(rank[i], rank[i] + n);
    }
    for (int i = 0; i < n; i++) {
        models[i].push_back(i);
    }
    sort(models, models + n);

    for (int i = 0; i < m; i++) {
        cur.reset();
        int ind = 0;
        for (int j = 0; j < n; j++) {
            auto [cur_rank, model] = rank[i][j];
            while (ind < n && rank[i][ind].first < cur_rank) {
                cur[rank[i][ind].second] = 1;
                ind++;
            }
            if (i == 0) {
                before[model] = cur;
            } else {
                before[model] = before[model] & cur;
            }
        }
    }

    long long dp[n];
    long long res = 0;
    for (int i = 0; i < n; i++) {
        int cur_model = models[i][m];
        dp[i] = p[cur_model];
        for (int j = 0; j < n; j++) {
            int prev_model = models[j][m];
            if (j != i && before[cur_model][prev_model]) {
                assert(j < i);
                dp[i] = max(dp[i], dp[j] + p[cur_model]);
            }
        }
        res = max(res, dp[i]);
    }
    cout << res << "\n";
}