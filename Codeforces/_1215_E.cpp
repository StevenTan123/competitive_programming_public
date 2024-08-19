#include <bits/stdc++.h>
using namespace std;

const int MAXA = 20;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    int a[n];
    vector<int> cnt(MAXA);
    // cost[i] = cost of moving all occurences of i to the front
    vector<long long> cost(MAXA);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i]--;
        cost[a[i]] += i - cnt[a[i]];
        cnt[a[i]]++;
    }

    // before[i][j] = # of occurences of i before j in a
    vector<vector<long long>> before(MAXA, vector<long long>(MAXA));
    for (int i = 0; i < MAXA; i++) {
        for (int j = 0; j < MAXA; j++) {
            if (i != j) {
                int num_j = 0;
                for (int k = n - 1; k >= 0; k--) {
                    if (a[k] == j) {
                        num_j++;
                    } else if (a[k] == i) {
                        before[i][j] += num_j;
                    }
                }
            }
        }
    }

    int pow2 = 1 << MAXA;
    vector<long long> dp(pow2, LONG_LONG_MAX);
    dp[0] = 0;
    for (int i = 0; i < pow2; i++) {
        if (dp[i] < LONG_LONG_MAX) {
            for (int j = 0; j < MAXA; j++) {
                if ((i & (1 << j)) == 0) {
                    long long sub = 0;
                    for (int k = 0; k < MAXA; k++) {
                        if (i & (1 << k)) {
                            sub += before[k][j];
                        }
                    }
                    long long add = cost[j] - sub;
                    dp[i | (1 << j)] = min(dp[i | (1 << j)], dp[i] + add);
                }
            }
        }
    }
    cout << dp[pow2 - 1] << endl;
}