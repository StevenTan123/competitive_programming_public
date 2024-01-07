#include <bits/stdc++.h>
using namespace std;

const int inf = 1e9;
const int MAXN = 205;
const int P = 200003;
const long long MOD = 1000000007;

// dp[i][j][k][0/1] = distance to exit under optimal strategy give we've explored
// vertices i...j, starting at vertex k, currently at end 0/1, minus the min distance
// to travel with the lights on
int dp[MAXN][MAXN][MAXN][2];

// returns 0 if i->j->k is left turn, 1 if i->j->k is right turn
int orientation(int x[], int y[], int i, int j, int k) {
    long long val = (long long) (y[j] - y[i]) * (x[k] - x[j]) - (long long) (x[j] - x[i]) * (y[k] - y[j]);
    return (val > 0 ? 0 : 1);
}

void rolling_hash(int N, int x[], int y[], int turn[], int start, vector<vector<long long>> &hash_mat, map<long long, vector<pair<int, int>>> &hashes) {
    long long hash = 0;
    for (int i = start; i < N; i++) {
        if (i > start) {
            int diff = abs(x[i] - x[i - 1]) + abs(y[i] - y[i - 1]);
            hash = (hash * P + diff) % MOD;
        }
        hash = (hash * P + turn[i]) % MOD;
        hash_mat[start][i] = hash;
        hashes[hash].push_back({start, i});
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    ifstream in("lightsout.in");
    ofstream out("lightsout.out");
    
    int N;
    in >> N;
    int x[N], y[N];
    for (int i = 0; i < N; i++) {
        in >> x[i] >> y[i];
    }

    int turn[N];
    int dist = 0;
    int min_dist[N];
    int pre_dist[N + 1];
    vector hash_mat(N, vector<long long>(N));
    for (int i = 0; i < N; i++) {
        int prev = (i - 1 + N) % N;
        int next = (i + 1) % N;
        turn[i] = orientation(x, y, prev, i, next);
        if (i > 0) {
            dist += abs(x[i] - x[i - 1]) + abs(y[i] - y[i - 1]);
        }
        min_dist[i] = dist;
        pre_dist[i] = dist;
    }
    pre_dist[N] = pre_dist[N - 1] + abs(x[N - 1] - x[0]) + abs(y[N - 1] - y[0]);
    for (int i = N - 1; i >= 0; i--) {
        min_dist[i] = min(min_dist[i], pre_dist[N] - pre_dist[i]);
    }

    map<long long, vector<pair<int, int>>> hashes;
    for (int i = 1; i < N; i++) {
        rolling_hash(N, x, y, turn, i, hash_mat, hashes);
    }
    
    // base cases: if visited segment contains exit, dp value
    // is -min_dist[start point] 
    for (int i = 0; i < N; i++) {
        for (int j = 1; j <= i; j++) {
            dp[0][i][j][0] = -min_dist[j];
        }
        for (int j = i; j < N; j++) {
            dp[i][N][j][1] = -min_dist[j];
        }
    }

    for (int gap = N - 2; gap >= 0; gap--) {
        for (int i = 1; i + gap < N; i++) {
            int j = i + gap;
            for (int k = i; k <= j; k++) {
                vector<pair<int, int>> &same = hashes[hash_mat[i][j]];

                int max_right_l = -inf;
                int max_left_l = -inf;
                int max_right_r = -inf;
                int max_left_r = -inf;
                for (pair<int, int> p : same) {
                    max_right_l = max(max_right_l, dp[p.first][p.second + 1][k - i + p.first][1] + pre_dist[p.second + 1] - pre_dist[p.first]);
                    max_left_l = max(max_left_l, dp[p.first - 1][p.second][k - i + p.first][0] + pre_dist[p.first] - pre_dist[p.first - 1]);
                    max_right_r = max(max_right_r, dp[p.first][p.second + 1][k - i + p.first][1] + pre_dist[p.second + 1] - pre_dist[p.second]);
                    max_left_r = max(max_left_r, dp[p.first - 1][p.second][k - i + p.first][0] + pre_dist[p.second] - pre_dist[p.first - 1]);
                }
                
                dp[i][j][k][0] = min(max_right_l, max_left_l);
                dp[i][j][k][1] = min(max_right_r, max_left_r);
            
            }
        }
    }

    int res = 0;
    for (int i = 0; i < N; i++) {
        res = max(res, dp[i][i][i][0]);
    }
    out << res << endl;
}