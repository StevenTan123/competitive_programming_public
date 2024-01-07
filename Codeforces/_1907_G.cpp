#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;
    bool lights[n] = {0};
    int a[n];
    int in_deg[n] = {0};
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i]--;
        in_deg[a[i]]++;
        if (s[i] == '1') {
            lights[i] = true;
        }
    }

    bool visited[n] = {0};
    vector<int> res;
    for (int i = 0; i < n; i++) {
        int cur = i;
        while (in_deg[cur] == 0) {
            visited[cur] = true;
            if (lights[cur]) {
                res.push_back(cur);
                lights[cur] = !lights[cur];
                lights[a[cur]] = !lights[a[cur]];
            }
            in_deg[cur]--;
            in_deg[a[cur]]--;
            cur = a[cur];
        }
    }

    for (int i = 0; i < n; i++) {
        if (!visited[i] && lights[i]) {
            int cnt1 = 0;
            int cur = i;
            bool flag = false;
            do {
                if (lights[cur] ^ flag) {
                    cnt1++;
                    flag = true;
                } else {
                    flag = false;
                }
                cur = a[cur];
            } while (cur != i);

            int cnt2 = 0;
            cur = a[i];
            flag = false;
            do {
                if (lights[cur] ^ flag) {
                    cnt2++;
                    flag = true;
                } else {
                    flag = false;
                }
                cur = a[cur];
            } while (cur != i);

            if (cnt1 <= cnt2) {
                cur = i;
                while (!visited[cur]) {
                    visited[cur] = true;
                    if (lights[cur]) {
                        res.push_back(cur);
                        lights[cur] = !lights[cur];
                        lights[a[cur]] = !lights[a[cur]];
                    }
                    cur = a[cur];
                }
            } else {
                cur = a[i];
                while (!visited[cur]) {
                    visited[cur] = true;
                    if (lights[cur]) {
                        res.push_back(cur);
                        lights[cur] = !lights[cur];
                        lights[a[cur]] = !lights[a[cur]];
                    }
                    cur = a[cur];
                }
            }
        }
    }

    bool or_all = false;
    for (int i = 0; i < n; i++) {
        or_all |= lights[i];
    }
    if (!or_all) {
        cout << res.size() << endl;
        for (int val : res) {
            cout << val + 1 << " ";
        }
        cout << endl;
    } else {
        cout << -1 << endl;
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