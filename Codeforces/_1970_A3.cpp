#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string s;
    cin >> s;
    int prev_open = 0;
    int next_open = 0;
    int cnt_close = 0;
    int cnt = 0;
    int segs[s.length()];
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '(') {
            next_open++;
        } else if (s[i] == ')') {
            if (cnt_close == prev_open) {
                prev_open = next_open;
                next_open = 0;
                cnt_close = 0;
                cnt++;
            }
            cnt_close++;
        }
        segs[i] = cnt;
    }
    
    stack<int> by_balance[cnt + 1];
    for (int i = 0; i < s.length(); i++) {
        by_balance[segs[i]].push(i);
    }

    int balance = 0;
    stringstream res;
    for (int i = 0; i < s.length(); i++) {
        int ind = by_balance[balance].top();
        by_balance[balance].pop();
        balance += (s[ind] == '(' ? 1 : -1);
        res << s[ind];
    }
    cout << res.str() << "\n";
}