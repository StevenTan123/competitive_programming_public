#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    while (true) {
        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            a[next] = max(0, a[next] - a[i]);
        }

        bool all3 = true;
        for (int i = 0; i < n; i++) {
            if (a[i] > 0 && a[(i + 1) % n] > 0 && a[(i + 2) % n] > 0 && a[(i + 3) % n] > 0) {
                all3 = false;
                break;
            }
        }
        if (all3) {
            break;
        }
    }

    vector<int> res;
    for (int i = 0; i < n; i++) {
        if (a[i] > 0) {
            int prev = (i + n - 1) % n;
            int prev2 = (i + n - 2) % n;
            if (a[prev] == 0) {
                res.push_back(i);
            } else if (a[prev2] > 0) {
                long long start = a[prev] % a[prev2];
                long long num = a[prev] / a[prev2];
                if (i == 1) {
                    num++;
                }
                long long sub = (2 * start + a[prev2] * (num - 1)) * num / 2;
                if (sub < a[i]) {
                    res.push_back(i);
                }
            }
        }
    }
    cout << res.size() << "\n";
    for (int ind : res) {
        cout << ind + 1 << " ";
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