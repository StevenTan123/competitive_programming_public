#include <bits/stdc++.h>
using namespace std;

const long long MOD = 9302023;
string nums[10] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

pair<int, long long> combine(pair<int, long long> &a, pair<int, long long> &b) {
    pair<int, long long> c = a;
    if (b.first < a.first) {
        c = b;
    } else if (b.first == a.first) {
        c.second = (c.second + b.second) % MOD;
    }
    return c;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string s;
    cin >> s;

    vector<int> match(s.length(), -1);
    for (int i = 0; i < s.length(); i++) {
        for (int j = 0; j < 10; j++) {
            if (i + nums[j].length() <= s.length()) {
                if (s.substr(i, nums[j].length()) == nums[j]) {
                    match[i] = j;
                    break;
                }
            }
        }
    }
    
    // dp[i] = {min length of 0...i, # ways} by applying operations to 1...i
    pair<int, long long> dp[s.length()];
    for (int i = 0; i < s.length(); i++) {
        if (i > 0) {
            dp[i] = {dp[i - 1].first + 1, dp[i - 1].second};
        } else {
            dp[0] = {1, 1};
        }
        for (int j = i - 2; j >= max(i - 4, 0); j--) {
            if (match[j] != -1 && nums[match[j]].length() == i - j + 1) {
                pair<int, long long> cur;
                if (j == 0) {
                    cur = {1, 1};
                } else {
                    cur = {dp[j - 1].first + 1, dp[j - 1].second};
                }
                dp[i] = combine(dp[i], cur);
            }
        }
    }
    cout << dp[s.length() - 1].first << " " << dp[s.length() - 1].second << endl;
}