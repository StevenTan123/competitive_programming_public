#include <bits/stdc++.h>
using namespace std;

// Possible to finish pile ending on Bessie given Bessie's pile is size keep.
bool possible(int N, long long a[], int keep) {
    // Need helper == N - keep
    int helper = 0;
    long long thres = keep;
    int left = keep - 1;
    for (int i = 0; i < N; i++) {
        if (helper >= N - keep) {
            break;
        }
        if (a[i] < thres) {
            helper++;
        } else {
            thres += left;
            left--;
        }
    }
    return helper >= N - keep;
}

void solve() {
    int N;
    cin >> N;
    long long a[N];
    for (int i = 0; i < N; i++) {
        cin >> a[i];
    }
    sort(a, a + N);

    int l = 1;
    int r = N;
    int res = N;
    while (l <= r) {
        int m = (l + r) / 2;
        if (possible(N, a, m)) {
            res = m;
            r = m - 1;
        } else {
            l = m + 1;
        }
    }
    long long ans = (long long) (res + 1) * res / 2;
    ans = min(ans, a[N - 1]);
    cout << ans << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T;
    cin >> T;
    while (T--) {
        solve();
    }
}