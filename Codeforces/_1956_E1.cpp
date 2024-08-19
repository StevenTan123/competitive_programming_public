#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    deque<int> inds;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        inds.push_back(i);
    }

    while (!inds.empty()) {
        int ind = inds.front();
        int next = (ind + 1) % n;
        int prev = (ind + n - 1) % n;
        int next_next = (next + 1) % n;
        inds.pop_front();

        a[next] = max(0, a[next] - a[ind]);
        if (n >= 4 && a[prev] == 0 && a[next_next] == 0) {
            continue;
        }
        if (a[ind] > 0 && a[next] > 0) {
            inds.push_back(ind);
        } 
    }

    vector<int> res;
    for (int i = 0; i < n; i++) {
        int prev = (i + n - 1) % n;
        if (a[i] != 0 && a[prev] == 0) {
            res.push_back(i + 1);
        }
    }
    cout << res.size() << "\n";
    for (int ind : res) {
        cout << ind << " ";
    }
    cout << "\n";
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