#include <bits/stdc++.h>
using namespace std;

bool two_color(vector<int> graph[], int colors[], int cur, int color) {
    if (colors[cur] != 0) {
        if (colors[cur] != color) {
            return false;
        }
        return true;
    }
    colors[cur] = color;
    for (int nei : graph[cur]) {
        if (!two_color(graph, colors, nei, 3 - color)) {
            return false;
        }
    }
    return true;
}

void solve() {
    int n, m;
    cin >> n >> m;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    long long sum = 0;
    for (int i = 0; i < n; i++) {
        int val;
        cin >> val;
        a[i] = val - a[i];
        sum += a[i];
    }
    vector<int> graph[n];
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        graph[--v].push_back(--u);
        graph[u].push_back(v);
    }
    if (sum % 2 != 0) {
        cout << "NO\n";
    } else {
        int colors[n] = {0};
        if (two_color(graph, colors, 0, 1)) {
            long long sum_half = 0;
            for (int i = 0; i < n; i++) {
                if (colors[i] == 1) {
                    sum_half += a[i];
                }
            }
            cout << (sum_half * 2 == sum ? "YES\n" : "NO\n");
        } else {
            cout << "YES\n";
        }
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