#include <bits/stdc++.h>
using namespace std;

const int MAXN = 500005;
const int K = 21;
int log_two[MAXN];

struct SparseTable {
    int N;
    long long **mi;
    SparseTable(int n, vector<long long> &a) : N(n) {
        mi = new long long*[N];
        for (int i = 0; i < N; i++) {
            mi[i] = new long long[K + 1];
            mi[i][0] = a[i];
        }
        for (int j = 1; j <= K; j++) {
            for(int i = 0; i + (1 << j) <= N; i++) {
                mi[i][j] = min(mi[i][j - 1], mi[i + (1 << (j - 1))][j - 1]);
            }
        }
    }
    ~SparseTable() {
        for (int i = 0; i < N; i++) delete[] mi[i];
        delete[] mi;
    }
    long long rmq(int l, int r) {
        int j = log_two[r - l + 1];
        return min(mi[l][j], mi[r - (1 << j) + 1][j]);
    }
};

void solve() {
    int n, k;
    cin >> n >> k;
    vector<int> a(n + 2);
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }
    string s;
    cin >> s;
    vector<bool> forced(n + 2, true);
    for (int i = 1; i <= n; i++) {
        if (s[i - 1] == '0') {
            forced[i] = false;
        }
    }
    vector<int> next_forced(n + 2);
    int prev = n + 2;
    for (int i = n + 1; i >= 0; i--) {
        next_forced[i] = prev;
        if (forced[i]) {
            prev = i;
        }
    }

    vector<long long> forward(n + 2);
    // {cost, ind}, ind increasing and cost increasing
    deque<pair<long long, int>> window;
    window.emplace_back(0, 0);
    for (int i = 1; i <= n + 1; i++) {
        while (window.front().second < i - k) {
            window.pop_front();
        }
        forward[i] = window.front().first + a[i];
        if (forced[i]) {
            window.clear();
        } else {
            while (!window.empty() && window.back().first >= forward[i]) {
                window.pop_back();
            }
        }
        window.emplace_back(forward[i], i);
    }
    window.clear();
    vector<long long> backward(n + 2);
    window.emplace_back(0, n + 1);
    for (int i = n; i >= 0; i--) {
        while (window.front().second > i + k) {
            window.pop_front();
        }
        backward[i] = window.front().first + a[i];
        if (forced[i]) {
            window.clear();
        } else {
            while (!window.empty() && window.back().first >= backward[i]) {
                window.pop_back();
            }
        }
        window.emplace_back(backward[i], i);
    }

    SparseTable rmq_back(n + 2, backward);
    int q;
    cin >> q;
    for (int i = 0; i < q; i++) {
        int p, v;
        cin >> p >> v;
        if (forced[p]) {
            cout << backward[0] + v - a[p] << "\n";
        } else {
            long long use = forward[p] + backward[p] - 2 * a[p] + v;
            long long no_use = LONG_LONG_MAX;
            for (int j = p - 1; j >= max(0, p - k + 1); j--) {
                no_use = min(no_use, forward[j] + rmq_back.rmq(p + 1, min(j + k, next_forced[j])));
                if (forced[j]) {
                    break;
                }
            }
            cout << min(use, no_use) << "\n";
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    log_two[1] = 0;
    for (int i = 2; i < MAXN; i++) {
        log_two[i] = log_two[i / 2] + 1;
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}