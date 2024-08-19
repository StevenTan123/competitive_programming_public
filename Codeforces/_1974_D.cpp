#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;

    int n_cnt = 0; int s_cnt = 0;
    int e_cnt = 0; int w_cnt = 0;
    for (int i = 0; i < n; i++) {
        if (s[i] == 'N') {
            n_cnt++;
        } else if (s[i] == 'S') {
            s_cnt++;
        } else if (s[i] == 'E') {
            e_cnt++;
        } else {
            w_cnt++;
        }
    }

    if ((n_cnt + s_cnt) % 2 != 0 || (e_cnt + w_cnt) % 2 != 0) {
        cout << "NO\n";
        return;
    } else {
        vector<bool> R(n, false);
        int a = n_cnt / 2;
        int c = s_cnt / 2;
        if (n_cnt > s_cnt) {
            a = (n_cnt + s_cnt) / 2 - s_cnt;
            c = 0;
        }
        for (int i = 0; i < n; i++) {
            if (s[i] == 'N' && a > 0) {
                a--;
                R[i] = true;
            }
            if (s[i] == 'S' && c > 0) {
                c--;
                R[i] = true;
            }
        }

        if (a == 0 && c == 0) {
            a = (e_cnt + 1) / 2;
            c = (w_cnt + 1) / 2;
        } else {
            a = e_cnt / 2;
            c = w_cnt / 2;
        }
        for (int i = 0; i < n; i++) {
            if (s[i] == 'E' && a > 0) {
                a--;
                R[i] = true;
            }
            if (s[i] == 'W' && c > 0) {
                c--;
                R[i] = true;
            }
        }
        
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += R[i];
        }
        if (cnt == 0 || cnt == n) {
            cout << "NO\n";
            return;
        }

        for (int i = 0; i < n; i++) {
            cout << (R[i] ? 'R' : 'H');
        }
        cout << "\n";
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