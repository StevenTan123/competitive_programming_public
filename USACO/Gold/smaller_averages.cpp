#include <bits/stdc++.h>
using namespace std;

const int MOD = 1000000007;
const int MAXN = 501;
const double epsilon = 1e-9;

// dp[i][j] = # ways to partition a[0...i] and b[0...j] into subarrays so that
// each subarray of a has <= avg than each subarray of b.
int dp[MAXN][MAXN];
int dp_sum[MAXN][MAXN];
vector<pair<double, int>> sort_a[MAXN];
vector<pair<double, int>> sort_b[MAXN];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N;
    int a[N];
    double avg_a[N];
    for (int i = 0; i < N; i++) {
        cin >> a[i];
        int sum = 0;
        for (int j = i; j > 0; j--) {
            sum += a[j];
            double avg = (double) sum / (i - j + 1);
            sort_a[i].emplace_back(avg, j);
        }
        avg_a[i] = (double) (sum + a[0]) / (i + 1);
        sort(sort_a[i].begin(), sort_a[i].end());
    }
    int b[N];
    double avg_b[N];
    for (int i = 0; i < N; i++) {
        cin >> b[i];
        int sum = 0;
        for (int j = i; j > 0; j--) {
            sum += b[j];
            double avg = (double) sum / (i - j + 1);
            sort_b[i].emplace_back(avg, j);
        }
        avg_b[i] = (double) (sum + b[0]) / (i + 1);
        sort(sort_b[i].begin(), sort_b[i].end());
    }

    for (int j = 0; j < N; j++) {    
        vector<pair<double, int>> sortj = sort_b[j];
        for (int i = 0; i < N; i++) {
            dp[i][j] = 0;
            if (avg_a[i] < avg_b[j] + epsilon) {
                dp[i][j] = 1;
            }
            
            int b_ptr = 0;
            vector<pair<double, int>> sorti = sort_a[i]; 
            for (int a_ptr = 0; a_ptr < sorti.size(); a_ptr++) {
                while (b_ptr < sortj.size() && sortj[b_ptr].first + epsilon < sorti[a_ptr].first) {
                    b_ptr++;
                }
                int ind_a = sorti[a_ptr].second;
                if (b_ptr < sortj.size()) {
                    dp[i][j] = (dp[i][j] + dp_sum[ind_a - 1][b_ptr]) % MOD;
                }
            }

            for (int k = sortj.size() - 1; k >= 0; k--) {
                dp_sum[i][k] = (k < sortj.size() - 1 ? dp_sum[i][k + 1] : 0) + dp[i][sortj[k].second - 1];
                dp_sum[i][k] %= MOD;
            }
        }
    }
    cout << dp[N - 1][N - 1] << "\n";
}