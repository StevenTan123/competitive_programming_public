#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, a, b;
    cin >> n >> a >> b;
    int p[n];
    map<int, int> inds;
    for (int i = 0; i < n; i++) {
        cin >> p[i];
        inds[p[i]] = i;
    }
    
    vector<int> graph[n];
    for (int i = 0; i < n; i++) {
        if (inds.count(a - p[i]) && p[i] * 2 != a) {
            graph[i].push_back(inds[a - p[i]]);
        }
        if (inds.count(b - p[i]) && p[i] * 2 != b) {
            graph[i].push_back(inds[b - p[i]]);
        }
    }

    bool setA[n] = {0};
    bool visited[n] = {0};
    bool possible = true;
    for (int i = 0; i < n; i++) {
        if (visited[i] || graph[i].size() > 1) {
            continue;
        }
        int cur = i;
        int prev = -1;
        vector<int> chain;
        while (true) {
            if (visited[cur]) {
                break;
            }
            visited[cur] = true;
            chain.push_back(cur);
            bool moved = false;
            for (int nei : graph[cur]) {
                if (nei != prev) {
                    moved = true;
                    prev = cur;
                    cur = nei;
                    break;
                }
            }
            if (!moved) {
                break;
            }
        }
        if (chain.size() % 2 == 0) {
            for (int j = 0; j < chain.size(); j += 2) {
                if (p[chain[j]] + p[chain[j + 1]] == a) {
                    setA[chain[j]] = true;
                    setA[chain[j + 1]] = true;
                }
            }
        } else {
            bool used = false;
            bool done = false;
            for (int j = 0; j < chain.size(); j++) {
                if (used) {
                    used = false;
                    continue;
                }
                if (!done && p[chain[j]] * 2 == a) {
                    done = true;
                    setA[chain[j]] = true;
                } else if (!done && p[chain[j]] * 2 == b) {
                    done = true;
                } else if (j < chain.size() - 1) {
                    if (p[chain[j]] + p[chain[j + 1]] == a) {
                        setA[chain[j]] = true;
                        setA[chain[j + 1]] = true;
                    }
                    used = true;
                } else {
                    possible = false;
                    break;
                }
            }
        }

    }
    if (possible) {
        cout << "YES" << endl;
        for (int i = 0; i < n; i++) {
            cout << (setA[i] ? 0 : 1) << " ";
        }
        cout << endl;
    } else {
        cout << "NO" << endl;
    }
}