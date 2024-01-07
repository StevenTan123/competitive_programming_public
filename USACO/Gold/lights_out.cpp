#include <bits/stdc++.h>
using namespace std;

const int P = 200003;
const long long MOD = 1000000007;

// returns 0 if i->j->k is left turn, 1 if i->j->k is right turn
int orientation(int x[], int y[], int i, int j, int k) {
    long long val = (long long) (y[j] - y[i]) * (x[k] - x[j]) - (long long) (x[j] - x[i]) * (y[k] - y[j]);
    return (val > 0 ? 0 : 1);
}

void rolling_hash(int N, int x[], int y[], int turn[], int start, vector<vector<long long>> &hash_mat, map<long long, int> &hashes) {
    long long hash = 0;
    for (int i = start; i < N; i++) {
        if (i > start) {
            int diff = abs(x[i] - x[i - 1]) + abs(y[i] - y[i - 1]);
            hash = (hash * P + diff) % MOD;
        }
        hash = (hash * P + turn[i]) % MOD;
        hash_mat[start][i] = hash;
        hashes[hash]++;
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
    long long dist = 0;
    long long min_dist[N];
    vector<vector<long long>> hash_mat(N);
    for (int i = 0; i < N; i++) {
        int prev = (i - 1 + N) % N;
        int next = (i + 1) % N;
        turn[i] = orientation(x, y, prev, i, next);
        hash_mat[i].resize(N);
        if (i > 0) {
            dist += abs(x[i] - x[i - 1]) + abs(y[i] - y[i - 1]);
        }
        min_dist[i] = dist;
    }
    dist = 0;
    for (int i = N - 1; i >= 0; i--) {
        int prev = (i + 1) % N;
        dist += abs(x[i] - x[prev]) + abs(y[i] - y[prev]);
        min_dist[i] = min(min_dist[i], dist);
    }

    map<long long, int> hashes;
    for (int i = 0; i < N; i++) {
        rolling_hash(N, x, y, turn, i, hash_mat, hashes);
    }

    long long max_diff = 0;
    for (int i = 1; i < N; i++) {
        int end = 0;
        dist = 0;
        for (int j = i; j <= N; j++) {
            if (j > i) {
                dist += abs(x[j % N] - x[j - 1]) + abs(y[j % N] - y[j - 1]);    
            }
            long long hash = hash_mat[i][j];
            if (hashes.count(hash) && hashes[hash] == 1) {
                end = j;
                break;
            }
        }
        int off_dist = dist + min_dist[end];
        max_diff = max(max_diff, off_dist - min_dist[i]);
    }
    out << max_diff << endl;
}