#include <bits/stdc++.h>
using namespace std;

const int INF = 1000000;
const int MAXN = 200005;
const int SQRT = 700;
int tri_numbers[SQRT];
int dp[MAXN];
int prev_dp[MAXN];

void solve() {
    long long n, x, y, s;
    cin >> n >> x >> y >> s;

    s -= n * (x % y);
    if (s < 0 || s % y != 0) {
        cout << "NO\n";
        return;
    }
    s /= y;

    int start = x / y;
    int cur = 0;
    for (int i = start; i < MAXN; i++) {
        cur += i;
        if (cur > s) {
            break;
        }
        if (dp[s - cur] + i - start + 1 <= n) {
            cout << "YES\n";
            
            vector<int> lens;
            int sum = s - cur;
            while (sum != 0) {
                lens.push_back(prev_dp[sum]);
                sum -= tri_numbers[prev_dp[sum]];
            }

            for (int j = 0; j <= i - start; j++) {
                cout << x + y * j << " ";
            }
            for (int len : lens) {
                for (int j = 0; j < len; j++) {
                    cout << x % y + j * y << " ";
                }
            }
            
            int left = n - (dp[s - cur] + i - start + 1);
            for (int j = 0; j < left; j++) {
                cout << x % y << " ";
            }
            cout << "\n";
            return;
        }
    }
    cout << "NO\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    tri_numbers[1] = 0;
    for (int i = 2; i < SQRT; i++) {
        tri_numbers[i] = tri_numbers[i - 1] + i - 1;
    }

    // dp[i] = min array length needed to sum to i
    dp[0] = 0;
    prev_dp[0] = -1;
    for (int i = 1; i < MAXN; i++) {
        dp[i] = INF;
        for (int j = 1; j < SQRT; j++) {
            if (i < tri_numbers[j]) {
                break;
            }
            if (dp[i - tri_numbers[j]] + j < dp[i]) {
                dp[i] = dp[i - tri_numbers[j]] + j;
                prev_dp[i] = j;
            }
        }
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}