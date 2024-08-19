#include <bits/stdc++.h>
using namespace std;

int height(vector<int> tree[], int heights[], int cur, int prev) {
    int max_height = 0;
    for (int nei : tree[cur]) {
        if (nei != prev) {
            max_height = max(max_height, height(tree, heights, nei, cur));
        }
    }
    heights[cur] = max_height + 1;
    return heights[cur];
}

void solve() {
    int n;
    cin >> n;
    vector<int> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        tree[u].push_back(v);
        tree[v].push_back(u);
    }

    int min_ind = 0;
    int min_height = INT_MAX;
    int heights[n][n];
    for (int i = 0; i < n; i++) {
        int cur_height = height(tree, heights[i], i, -1);
        if (cur_height < min_height) {
            min_height = cur_height;
            min_ind = i;
        }
    }
    int h1 = -1;
    int h2 = -1;
    for (int nei : tree[min_ind]) {
        if (h1 == -1 || heights[min_ind][nei] > heights[min_ind][h1]) {
            h2 = h1;
            h1 = nei;
        } else if (h2 == -1 || heights[min_ind][nei] > heights[min_ind][h2]) {
            h2 = nei;
        }
    }
    if ((h1 == -1 && h2 == -1) || heights[min_ind][h1] == heights[min_ind][h2]) {
        cout << min_height << "\n";
        for (int i = 0; i < min_height; i++) {
            cout << min_ind + 1 << " " << i << "\n";
        }
    } else {
        cout << min_height / 2 * 2 << "\n";
        for (int i = 1; i < min_height; i += 2) {
            cout << min_ind + 1 << " " << i << "\n";
            cout << h1 + 1 << " " << i << "\n";
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