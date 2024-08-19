#include <bits/stdc++.h>
using namespace std;

int check_wins(int n, int a[], int start) {
    int cnt = (start > 0 ? 1 : 0);
    for (int i = start + 1; i < n; i++) {
        if (a[i] < a[start]) {
            cnt++;
        } else {
            break;
        }
    }
    return cnt;
}

void solve() {
    int n, k;
    cin >> n >> k;
    k--;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    swap(a[0], a[k]);
    int res = check_wins(n, a, 0);
    swap(a[0], a[k]);

    int bigger = -1;
    for (int i = 0; i < n; i++) {
        if (a[i] > a[k]) {
            bigger = i;
            break;
        }
    }
    if (bigger != -1 && bigger < k) {
        swap(a[bigger], a[k]);
        res = max(res, check_wins(n, a, bigger));
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