#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string s;
    cin >> s;
    stack<pair<char, int>> st;
    vector<bool> marked(s.length());
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '1') {
            st.push({'1', i});
        } else {
            if (!st.empty() && st.top().first == '1') {
                marked[st.top().second] = true;
                marked[i] = true;
                st.pop();
            }
        }
    }
    stringstream ss;
    for (int i = 0; i < s.length(); i++) {
        if (!marked[i] && s[i] == '1') {
            ss << '0';
        } else {
            ss << s[i];
        }
    }
    cout << ss.str() << endl;
}