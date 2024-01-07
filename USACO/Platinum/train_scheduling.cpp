#include <bits/stdc++.h>
using namespace std;

const int MAXN = 5005;
const long long inf = 1e18;

int inds[2][MAXN];
long long psum[2][MAXN];
long long dp[2][MAXN][MAXN];
long long dp2[2][MAXN];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    long long T;
    cin >> N >> T;
    vector<long long> trains[2];
    for (int i = 0; i < N; i++) {
        char s;
        long long t;
        cin >> s >> t;
        if (s == 'A') {
            trains[0].push_back(t);
        } else {
            trains[1].push_back(t);
        }
    }
    sort(trains[0].begin(), trains[0].end());
    sort(trains[1].begin(), trains[1].end());

    int p = 0;
    for (int i = 0; i < trains[0].size(); i++) {
        psum[0][i] = (i > 0 ? psum[0][i - 1] : 0) + trains[0][i];
        while (p < trains[1].size() && trains[1][p] <= trains[0][i] + T) {
            p++;
        }
        inds[0][i] = p - 1;
    }
    p = 0;
    for (int i = 0; i < trains[1].size(); i++) {
        psum[1][i] = (i > 0 ? psum[1][i - 1] : 0) + trains[1][i];
        while (p < trains[0].size() && trains[0][p] <= trains[1][i] + T) {
            p++;
        }
        inds[1][i] = p - 1;
    }

    vector<tuple<long long, int, int>> sort_states;
    for (int i = 0; i <= 1; i++) {
        for (int j = 0; j <= trains[i].size(); j++) {
            sort_states.push_back({j > 0 ? trains[i][j - 1] : -1, i, j});
            dp2[i][j] = inf;
            for (int k = 0; k <= trains[1 - i].size(); k++) {
                dp[i][j][k] = inf;
            }
        }
    }
    sort(sort_states.begin(), sort_states.end());

    dp[0][0][0] = 0;
    dp[1][0][0] = 0;
    long long res = inf;
    for (tuple<long long, int, int> state : sort_states) {
        int i = get<1>(state);
        int j = get<2>(state);
        for (int k = 0; k <= trains[1 - i].size(); k++) {
            if (dp[i][j][k] != inf) {
                if (j < trains[i].size()) {
                    dp[i][j + 1][k] = min(dp[i][j + 1][k], dp[i][j][k]);
                }
                if (j > 0) {
                    long long delay = 0;
                    int ind = inds[i][j - 1];
                    if (ind >= 0) {
                        delay = psum[1 - i][ind] - (k > 0 ? psum[1 - i][k - 1] : 0);
                        delay = (ind - (k - 1)) * (trains[i][j - 1] + T) - delay;
                    }
                    dp2[i][j] = min(dp2[i][j], dp[i][j][k] + delay);
                }
            }
        }
        if (j > 0 && dp2[i][j] != inf) {
            int side = 1 - i;
            int ind = inds[i][j - 1];
            int ind_o = j - 1;
            long long time = trains[i][j - 1] + T;
            long long delay = 0;
            while (true) {
                if (ind + 2 <= trains[side].size()) {
                    dp[side][ind + 2][ind_o + 1] = min(dp[side][ind + 2][ind_o + 1], dp2[i][j] + delay);
                }
                if (ind == trains[side].size() - 1 && ind_o == trains[1 - side].size() - 1) {
                    res = min(res, dp2[i][j] + delay);
                    break;
                }
                time += T;
                side = 1 - side;
                swap(ind, ind_o);
                while (ind + 1 < trains[side].size() && trains[side][ind + 1] < time) {
                    delay += time - trains[side][ind + 1];
                    ind++;
                }
            }
        }
    }
    for (int i = 0; i <= 1; i++) {
        res = min(res, dp[i][trains[i].size()][trains[1 - i].size()]);
    }
    cout << res << endl;
}
