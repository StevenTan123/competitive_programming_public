#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int p[n];
    int c[n];
    for (int i = 0; i < n; i++) {
        cin >> p[i];
        p[i]--;
    }
    for (int i = 0; i < n; i++) {
        cin >> c[i];
        c[i]--;
    }

    bool visited[n] = {0};
    int res = n;
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            int cur = i;
            int size = 0;
            do {
                visited[cur] = 1;
                cur = p[cur];
                size++;
            } while (cur != i);
        
            vector<int> factors;
            for (int j = 1; j * j <= size; j++) {
                if (size % j == 0) {
                    factors.push_back(j);
                    if (size / j != j) factors.push_back(size / j);
                }
            }
            sort(factors.begin(), factors.end());

            for (int j = 0; j < factors.size(); j++) {
                vector<int> prev(factors[j], -1);
                cur = i;
                for (int k = 0; k < size; k++) {
                    int bin = k % factors[j];
                    if (prev[bin] != -1 && c[cur] != prev[bin]) {
                        prev[bin] = -2;
                    } else if (prev[bin] == -1) {
                        prev[bin] = c[cur];
                    }
                    cur = p[cur];
                }
                bool found = false;
                for (int k = 0; k < factors[j]; k++) {
                    if (prev[k] != -2) {
                        res = min(res, factors[j]);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
        }
    }
    cout << res << "\n";
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