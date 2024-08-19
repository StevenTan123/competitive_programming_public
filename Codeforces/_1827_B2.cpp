#include <bits/stdc++.h>
using namespace std;

const int MAXLOG = 20;

void solve() {
    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    stack<int> stk;
    stack<int> stk2;
    int l[n];
    int l2[n];
    for (int i = 0; i < n; i++) {
        while (!stk.empty() && a[stk.top()] < a[i]) {
            stk.pop();
        }
        while (!stk2.empty() && a[stk2.top()] > a[i]) {
            stk2.pop();
        }
        l[i] = (stk.empty() ? -1 : stk.top());
        l2[i] = (stk2.empty() ? -1 : stk2.top());
        stk.push(i);
        stk2.push(i);
    }
    int jump[MAXLOG][n];
    for (int i = 0; i < MAXLOG; i++) {
        for (int j = 0; j < n; j++) {
            if (i == 0) {
                jump[i][j] = l[j];
            } else {
                int half = jump[i - 1][j];
                jump[i][j] = (half == -1 ? -1 : jump[i - 1][half]);
            }
        }
    }

    stack<int> stk3;
    int r[n];
    for (int i = n - 1; i >= 0; i--) {
        while (!stk3.empty() && a[stk3.top()] > a[i]) {
            stk3.pop();
        }
        r[i] = (stk3.empty() ? n : stk3.top());
        stk3.push(i);
    }

    long long save = 0;
    long long total = 0;
    for (int i = 0; i < n; i++) {
        int cur_l = l2[i];
        if (cur_l != -1) {
            for (int j = MAXLOG - 1; j >= 0; j--) {
                if (jump[j][cur_l] != -1 && a[jump[j][cur_l]] < a[i]) {
                    cur_l = jump[j][cur_l];
                }
            }
            cur_l = jump[0][cur_l];
        }
        save += (long long) (l2[i] - cur_l) * (r[i] - i);
        total += (long long) i * (n - i);
    }
    cout << total - save << "\n";
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