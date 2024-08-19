#include <bits/stdc++.h>
using namespace std;
 
int q;
vector<vector<int>> graph;
vector<int> timestamps;
vector<vector<pair<int, int>>> adds;
vector<long long> vals;
 
struct BIT {
    long long *bit;
    int len;
    BIT(int n) {
        bit = new long long[n + 1];
        len = n + 1;
        for (int i = 0; i <= n; i++) {
            bit[i] = 0;
        }
    }
    ~BIT() {
        delete[] bit;
    }
    void update(int index, int add) {
        index++;
        while (index < len) {
            bit[index] += add;
            index += index & -index;
        }
    }
    long long sum(int l, int r) {
        return psum(r) - (l == 0 ? 0 : psum(l - 1));
    }
    long long psum(int index) {
        index++;
        long long res = 0;
        while (index > 0) {
            res += bit[index];      
            index -= index & -index;
        }
        return res;
    }
};


void dfs(BIT &bit, int cur) {
    for (auto [timestamp, add] : adds[cur]) {
        bit.update(timestamp, add);
    }
    vals[cur] = bit.sum(timestamps[cur], q);
    for (int child : graph[cur]) {
        dfs(bit, child);
    }
    for (auto [timestamp, add] : adds[cur]) {
        bit.update(timestamp, -add);
    }
}
 
void solve() {
    cin >> q;
    graph.clear(); graph.push_back({});
    timestamps.clear(); timestamps.push_back(0);
    adds.clear(); adds.push_back({});
    for (int i = 1; i <= q; i++) {
        int t, v; 
        cin >> t >> v;
        v--;
        if (t == 1) {
            int u = graph.size();
            graph.push_back({});
            timestamps.push_back(i);
            adds.push_back({});
            graph[v].push_back(u);
        } else {
            int x;
            cin >> x;
            adds[v].emplace_back(i, x);
        }
    }
 
    vals.resize(graph.size());
    BIT bit(q + 1);
    dfs(bit, 0);
    for (long long val : vals) {
        cout << val << " ";
    }
    cout << "\n";
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