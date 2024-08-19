#include <bits/stdc++.h>
using namespace std;
 
void solve() {
    int n;
    cin >> n;
    multiset<int> d1;
    multiset<int> d2;
    for (int i = 0; i < n; i++) {
        int d;
        cin >> d;
        d1.insert(d);
    }
    for (int i = 0; i < n; i++) {
        int d;
        cin >> d;
        d2.insert(d);
    }
 
    set<int> diffs;
    int max1 = *d1.rbegin();
    int max2 = *d2.rbegin();
    bool swapped = false;
    if (max1 < max2) {
        swapped = true;
        swap(max1, max2);
        swap(d1, d2);
    } 
    for (int val : d2) {
        diffs.insert(max1 - val);
        diffs.insert(max1 + val);
    }
 
    for (int diff : diffs) {
        multiset<int> dd1(d1);
        multiset<int> dd2(d2);
        
        vector<pair<int, int>> match;
        while (!dd1.empty() && !dd2.empty()) {
            max1 = *dd1.rbegin();
            max2 = *dd2.rbegin();
            if (max1 > max2) {
                if (max1 >= diff) {
                    if (dd2.count(max1 - diff)) {
                        match.emplace_back(max1, max1 - diff);
                        dd1.erase(std::prev(dd1.end()));
                        dd2.erase(dd2.find(max1 - diff));
                    } else {
                        break;
                    }
                } else {
                    if (dd2.count(diff - max1)) {
                        match.emplace_back(max1, diff - max1);
                        dd1.erase(std::prev(dd1.end()));
                        dd2.erase(dd2.find(diff - max1));
                    } else {
                        break;
                    }
                }
            } else {
                if (max2 >= diff) {
                    if (dd1.count(max2 - diff)) {
                        match.emplace_back(max2 - diff, max2);
                        dd2.erase(std::prev(dd2.end()));
                        dd1.erase(dd1.find(max2 - diff));
                    } else {
                        break;
                    }
                } else {
                    if (dd1.count(diff - max2)) {
                        match.emplace_back(max2 - diff, max2);
                        dd2.erase(std::prev(dd2.end()));
                        dd1.erase(dd1.find(diff - max2));
                    } else {
                        break;
                    }
                }
            }
        }
 
        if (match.size() == n) {
            cout << "YES\n";
            vector<int> pos;
            int min_pos = 0;
            for (auto it = match.begin(); it != match.end(); it++) {
                int one = it->first;
                int two = it->second;
                if (one >= two) {
                    pos.push_back(one);
                } else {
                    pos.push_back(-one);
                    min_pos = min(min_pos, -one);
                }
            }
 
            for (int x : pos) {
                cout << x - min_pos << " ";
            }
            cout << "\n";
 
            int p1 = -min_pos;
            int p2 = p1 + diff;
            if (swapped) {
                swap(p1, p2);
            }
            cout << p1 << " " << p2 << "\n";
            return;
        }
    }
    cout << "NO\n";
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