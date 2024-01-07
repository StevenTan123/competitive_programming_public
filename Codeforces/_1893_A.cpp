#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;
    int b[n];
    for (int i = 0; i < n; i++) {
        cin >> b[i];
    }

    bool possible = true;
    int end = n - 1;
    bool visited[n] = {0};
    for (int i = 0; i < k; i++) {
        if (visited[end]) {
            break;
        }
        visited[end] = true;
        int next = (end + b[end]) % n;
        if (b[end] > n) {
            possible = false;
            break;
        }
        end = (end - next + end + n) % n;
    }
    cout << (possible ? "YES\n" : "NO\n");
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
