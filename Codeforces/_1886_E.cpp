#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    
    pair<int, int> a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i].first;
        a[i].second = i;
    }
    sort(a, a + n);
    reverse(a, a + n);

    int b[m];
    for (int i = 0; i < m; i++) {
        cin >> b[i];
    }

    int next[m][n];
    for (int i = 0; i < m; i++) {
        int r = 0;
        for (int l = 0; l < n; l++) {
            r = max(l, r);
            while (r < n && (long long) a[r].first * (r - l + 1) < b[i]) {
                r++;
            }
            next[i][l] = r;
        }
    }

    int N = (1 << m);
    vector<int> dp(N, INT_MAX);
    vector<int> prev(N, INT_MAX);
    dp[0] = 0;
    for (int i = 0; i < N; i++) {
        if (dp[i] == INT_MAX) {
            continue;
        }
        for (int j = 0; j < m; j++) {
            if (i & (1 << j)) {
                continue;
            }
            if (dp[i] < n) {
                int next_ind = next[j][dp[i]];
                int next_mask = i | (1 << j);
                if (next_ind < n && next_ind + 1 < dp[next_mask]) {
                    dp[next_mask] = next_ind + 1;
                    prev[next_mask] = i;
                }
            }
        }
    }
    
    if (dp[N - 1] == INT_MAX) {
        cout << "NO\n";
    } else {
        cout << "YES\n";
        vector<int> assign[m];
        int mask = N - 1;
        while (mask != 0) {
            int proj = -1;
            for (int i = 0; i < m; i++) {
                if ((mask & (1 << i)) && !(prev[mask] & (1 << i))) {
                    proj = i;
                    break;
                }
            }
            for (int i = dp[prev[mask]]; i < dp[mask]; i++) {
                assign[proj].push_back(a[i].second);
            }
            mask = prev[mask];
        }
        for (int i = 0; i < m; i++) {
            cout << assign[i].size() << " ";
            for (int person : assign[i]) {
                cout << person + 1 << " ";
            }
            cout << "\n";
        }
    }
}