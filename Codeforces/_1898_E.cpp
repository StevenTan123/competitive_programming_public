#include <bits/stdc++.h>
using namespace std;

const int ALPH = 26;

void solve() {
    int n, m;
    cin >> n >> m;

    string s, t;
    cin >> s >> t;

    queue<int> queues[ALPH];
    for (int i = 0; i < n; i++) {
        int c = s[i] - 'a';
        queues[c].push(i);
    }

    bool possible = true;
    for (int i = 0; i < m; i++) {
        int c = t[i] - 'a';
        if (queues[c].empty()) {
            possible = false;
            break;
        }
        int ind = queues[c].front();
        queues[c].pop();
        for (int j = 0; j < c; j++) {
            while (!queues[j].empty() && queues[j].front() < ind) {
                queues[j].pop();
            }
        }
    }
    cout << (possible ? "YES" : "NO") << endl;
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