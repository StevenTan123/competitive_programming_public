#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k, c;
    cin >> n >> k >> c;
    vector<int> last_show(n, -1);
    vector<pair<int, int>> edges[k + 1];
    for (int i = 0; i < c; i++) {
        int a, b, d;
        cin >> a >> b >> d;
        a--; b--;
        edges[d].emplace_back(a, b);
        last_show[a] = max(last_show[a], d);
        last_show[b] = max(last_show[b], d);
    }

    set<int> infectious;
    for (int i = 0; i < n; i++) {
        if (last_show[i] <= 1) {
            infectious.insert(i);
        }
    }
    for (int i = 1; i <= k; i++) {
        set<int> next;
        for (auto [a, b] : edges[i]) {
            if (infectious.count(a) && last_show[b] <= i + 1) {
                next.insert(b);
            }
            if (infectious.count(b) && last_show[a] <= i + 1) {
                next.insert(a);
            }
        }
        infectious = next;
    }
    cout << infectious.size() << "\n";
    for (int x : infectious) {
        cout << x + 1 << "\n";
    }
}