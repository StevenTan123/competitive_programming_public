#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    int a[n];
    int out_deg[n] = {0};
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i]--;
        out_deg[a[i]]++;
    }

    set<pair<int, int>> q;
    pair<int, int> pairs[n];
    for (int i = 0; i < n; i++) {
        pairs[i] = {out_deg[i], i};
        q.insert(pairs[i]);
    }

    bool seen[n] = {0};
    vector<int> uncircled;
    while (q.size() > 0) {
        pair<int, int> cur = *q.begin();
        q.erase(cur);
        
        if (cur.first > 0) {
            break;
        }

        uncircled.push_back(cur.second);
        seen[cur.second] = true;
        if (!seen[a[cur.second]]) {
            seen[a[cur.second]] = 1;
            q.erase(pairs[a[cur.second]]);

            pair<int, int> &prev = pairs[a[a[cur.second]]];
            q.erase(prev);
            prev.first--;
            q.insert(prev);
        } 
    }

    bool possible = 1;
    for (int i = 0; i < n; i++) {
        if (!seen[i]) {
            int count = 0;
            int cur = i;
            while (count == 0 || cur != i) {
                seen[cur] = 1;
                if (count % 2 == 0) {
                    uncircled.push_back(cur);
                }
                cur = a[cur];
                count++;
            }
            if (count % 2 == 1) {
                possible = 0;
                break;
            }
        }
    }

    if (possible) {
        sort(uncircled.begin(), uncircled.end());
        cout << uncircled.size() << endl;
        for (int val : uncircled) {
            cout << a[val] + 1 << " ";
        }
        cout << endl;
    } else {
        cout << "-1" << endl;
    }
}