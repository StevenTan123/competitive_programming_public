#include <bits/stdc++.h>
using namespace std;
 
const int INF = 1000000000;
 
long long cnt_len(string &s, vector<int> &non_a, int len) {
    int chunks = non_a.size() / len;
    int min_a_btwn = INF;
    for (int i = 1; i < chunks; i++) {
        int prev_start = non_a[(i - 1) * len];
        int prev_end = non_a[i * len - 1];
        int start = non_a[i * len];
        int end = non_a[i * len + len - 1];
        min_a_btwn = min(min_a_btwn, start - prev_end - 1);
        if (prev_end - prev_start != end - start) {
            return 0;
        }
        for (int j = 0; j <= prev_end - prev_start; j++) {
            if (s[j + prev_start] != s[j + start]) {
                return 0;
            }
        }
    }
    int a_start = non_a[0];
    int a_end = s.length() - non_a[non_a.size() - 1] - 1;
    long long res = 0;
    for (int i = 0; i <= a_start; i++) {
        if (min_a_btwn - i < 0) {
            break;
        }
        res += min(a_end, min_a_btwn - i) + 1;
    }
    return res;
}
 
void solve() {
    string s;
    cin >> s;
    vector<int> non_a;
    for (int i = 0; i < s.length(); i++) {
        if (s[i] != 'a') {
            non_a.push_back(i);
        }
    }
 
    if (non_a.size() == 0) {
        cout << s.length() - 1 << "\n";
        return;
    }
    
    vector<int> factors;
    for (int i = 1; i * i <= non_a.size(); i++) {
        if (non_a.size() % i == 0) {
            factors.push_back(i);
            if (i * i != non_a.size()) {
                factors.push_back(non_a.size() / i);
            }
        }
    }
    
    long long res = 0;
    for (int factor : factors) {
        res += cnt_len(s, non_a, factor);
    }
    cout << res << "\n";
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