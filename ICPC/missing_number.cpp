#include <bits/stdc++.h>
using namespace std;

const int MAXV = 100000;
int digits[MAXV];

// -1 if not possible, 0 if eats nothing, otherwise the eaten number, also return last value
pair<int, int> possible(string &s, int val, int ind) {
    int eaten = 0;
    while (ind < s.length()) {
        if (val >= MAXV) {
            return {-1, -1};
        }
        int len = digits[val];
        if (ind + len > s.length()) {
            return {-1, -1};
        }
        int num = stoi(s.substr(ind, len));
        if (num == val) {
            ind += len;
            val++;
            continue;
        }

        if (val + 1 >= MAXV) {
            return {-1, -1};
        } 
        len = digits[val + 1];
        if (ind + len > s.length()) {
            return {-1, -1};
        }
        num = stoi(s.substr(ind, len));
        if (num == val + 1) {
            if (eaten != 0) {
                return {-1, -1};
            }
            eaten = val;
            ind += len;
            val += 2;
        } else {
            return {-1, -1};
        }
    }
    return {eaten, val};
}

void solve() {
    string s;
    cin >> s;

    set<int> res;
    for (int i = 1; i <= min((int) s.length(), 5); i++) {
        int val = stoi(s.substr(0, i));
        pair<int, int> cur = possible(s, val + 1, i);
        if (cur.first != -1) {
            if (cur.first != 0) {
                res.insert(cur.first);
            } else {
                if (val > 1) {
                    res.insert(val - 1);
                }
                if (cur.second < MAXV) {
                    res.insert(cur.second);
                }
            }
        }
    }
    cout << res.size() << "\n";
    for (int val : res) {
        cout << val << " ";
    }
    cout << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 1; i < MAXV; i++) {
        digits[i] = 0;
        int cur = i;
        while (cur > 0) {
            digits[i]++;
            cur /= 10;
        }
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}