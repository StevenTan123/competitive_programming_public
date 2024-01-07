#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

std::random_device rd;
std::mt19937 rng(rd());
std::uniform_int_distribution<long long> uni(1, LONG_LONG_MAX);

void mark(int n, int c[], long long xor_rand[], bool end[], int marked[]) {
    map<long long, int> inds;
    long long tot = 0;
    inds[0] = -1;
    for (int i = 0; i < 2 * n; i++) {
        tot ^= xor_rand[c[i]];
        if (inds.count(tot) && !end[i]) {
            marked[inds[tot] + 1]++;
            marked[i + 1]--;
        }
        inds[tot] = i;
    }
    for (int i = 1; i < 2 * n; i++) {
        marked[i] += marked[i - 1];
    }
}

int find_rb(int c[], pair<int, int> inds[], int start) {
    int r = start;
    for (int i = start; i <= r; i++) {
        r = max(r, inds[c[i]].second);
    }
    return r;
}

void solve() {
    int n;
    cin >> n;
    int c[2 * n];
    long long xor_rand[n];
    pair<int, int> inds[n];
    for (int i = 0; i < n; i++) {
        inds[i] = {-1, -1};
        xor_rand[i] = uni(rng);
    }

    for (int i = 0; i < 2 * n; i++) {
        cin >> c[i];
        c[i]--;
        if (inds[c[i]].first == -1) {
            inds[c[i]].first = i;
        } else {
            inds[c[i]].second = i;
        }
    }

    bool end[2 * n + 1] = {0};
    int ind = 0;
    while (ind < 2 * n) {
        int rb = find_rb(c, inds, ind);
        end[rb] = 1;
        ind = rb + 1;
    }
    int marked[2 * n + 1] = {0};
    mark(n, c, xor_rand, end, marked);

    int count = 0;
    int cur_starts = 0;
    long long prod = 1;
    for (int i = 0; i <= 2 * n; i++) {
        if (!marked[i]) {
            cur_starts++;
        }
        if (end[i]) {
            prod *= cur_starts;
            prod %= MOD;
            cur_starts = 0;
            count++;
        }
    }
    cout << count << " " << prod << endl;
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
