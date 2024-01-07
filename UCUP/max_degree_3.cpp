#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    vector<pair<int, int>> graph[n];
    for (int i = 0; i < m; i++) {
        int u, v, c;
        cin >> u >> v >> c;
        graph[--u].push_back({--v, c});
        graph[v].push_back({u, c});
    }

    vector<pair<int, int>> match;
    for (int i = 0; i < n; i++) {
        set<int> seen;
        for (pair<int, int> e : graph[i]) {
            if (seen.count(e.first) && i < e.first) {
                match.push_back({i, e.first});
            }
            seen.insert(e.first);
        }
    }

    long long res = n + match.size();
    for (pair<int, int> p : match) {
        int n1 = -1;
        int c1 = -1;
        for (pair<int, int> e : graph[p.first]) {
            if (e.first != p.second) {
                n1 = e.first;
                c1 = e.second;
            }
        }
        int n2 = -1;
        int c2 = -1;
        for (pair<int, int> e : graph[p.second]) {
            if (e.first != p.first) {
                n2 = e.first;
                c2 = e.second;
            }
        }
        if (n1 != -1 && n1 == n2 && c1 != c2) {
            res++;
        }
    }
    long long fours = 0;
    for (int u = 0; u < n; u++) {
        set<int> comp;
        comp.insert(u);
        for (pair<int, int> e : graph[u]) {
            for (pair<int, int> e2 : graph[e.first]) {
                comp.insert(e2.first);
            }
            comp.insert(e.first);
        }
        if (comp.size() == 4) {
            int cnt[] = {0, 0};
            bool works = true;
            for (int v : comp) {
                set<int> unique;
                for (pair<int, int> e : graph[v]) {
                    if (comp.count(e.first)) {
                        cnt[e.second]++;
                    }
                    unique.insert(e.second);
                }
                if (unique.size() != 2) {
                    works = false;
                    break;
                }
            } 
            if (works && cnt[0] == 6 && cnt[1] == 6) {
                fours++;
            }
        }
    }
    cout << (res + fours / 4) % MOD << endl;
}