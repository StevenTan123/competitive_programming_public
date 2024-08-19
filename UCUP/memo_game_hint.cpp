#include <bits/stdc++.h>
using namespace std;

void solve(string &type) {
    if (type == "prepare") {
        string cards;
        cin >> cards;
        vector<bool> seen(25);
        for (int i = 0; i < 50; i++) {
            int cur = cards[i] - 'A';
            if (seen[cur]) {
                cout << 1;
            } else {
                cout << 0;
                seen[cur] = true;
            }
        }
        cout << endl;
    } else {
        string bits;
        cin >> bits;

        vector<int> zeros;
        vector<int> ones;
        for (int i = 0; i < 50; i++) {
            if (bits[i] == '0') {
                zeros.push_back(i);
            } else {
                ones.push_back(i);
            }
        }

        vector<int> pos(25);
        int ind = 0;
        string ret;
        for (int i = 0; i < 12; i++) {
            int first = zeros[ind++];
            int second = zeros[ind++];
            
            cout << first + 1 << endl;
            cin >> ret;
            pos[ret[0] - 'A'] = first;

            cout << second + 1 << endl;
            cin >> ret;
            pos[ret[0] - 'A'] = second;
        }
        cout << zeros[ind] + 1 << endl;
        cin >> ret;
        pos[ret[0] - 'A'] = zeros[ind];

        ind = 0;
        cout << ones[ind] + 1 << endl;
        cin >> ret;
        if (ret[1] == '+') {
            ind++;
        }

        for (; ind < 25; ind++) {
            cout << ones[ind] + 1 << endl;
            cin >> ret;

            cout << pos[ret[0] - 'A'] + 1 << endl;
            cin >> ret;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string type;
    cin >> type;

    int t;
    cin >> t;
    while (t--) {
        solve(type);
    }
}