#include <bits/stdc++.h>
using namespace std;

const int MAXN = 100005;
vector<int> spf(MAXN);
vector<int> primes;

void eulerian_cycle(int n, vector<int> &cycle, vector<vector<bool>> &visited) {
    vector<int> states(n);
    stack<int> stk;
    stk.push(0);
    while (!stk.empty()) {
        int cur = stk.top();
        while (states[cur] < n && visited[cur][states[cur]]) {
            states[cur]++;
        }
        if (states[cur] < n) {
            visited[cur][states[cur]] = 1;
            visited[states[cur]][cur] = 1;
            stk.push(states[cur]++);
        } else {
            cycle.push_back(cur);
            stk.pop();
        }
    }
}

void solve() {
    int n;
    cin >> n;

    int k = -1;
    int l = 1;
    int r = n;
    while (l <= r) {
        int m = (l + r) / 2;
        long long n_edges = (long long) m * (m - 1) / 2 + m + 1;
        if (m % 2 == 0) {
            n_edges -= (m - 2) / 2;
        }
        if (n_edges >= n) {
            k = m;
            r = m - 1;
        } else {
            l = m + 1;
        }
    }
    
    vector<int> cycle;
    vector<vector<bool>> visited(k, vector<bool>(k));
    if (k % 2 == 0) {
        for (int i = 0; i < k; i += 2) {
            visited[i][i + 1] = 1;
            visited[i + 1][i] = 1;
        }   
    }
    eulerian_cycle(k, cycle, visited);
    if (k % 2 == 0) {
        cycle.push_back(1);
        if (k == 2) {
            cycle.push_back(1);
        }
    }

    for (int i = 0; i < n; i++) {
        cout << primes[cycle[i]] << " ";
    }
    cout << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 2; i < MAXN; i++) {
        if (spf[i] == 0) {
            for (int j = i; j < MAXN; j += i) {
                if (spf[j] == 0) {
                    spf[j] = i;
                }
            }
        }
    }
    for (int i = 2; i < MAXN; i++) {
        if (spf[i] == i) {
            primes.push_back(i);
        }
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}
