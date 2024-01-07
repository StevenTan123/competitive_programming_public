#include <bits/stdc++.h>
using namespace std;

const int MAXLOG = 35;

void solve() {
    int n;
    cin >> n;
    int min_val = INT_MAX;
    int max_val = 0;
    for (int i = 0; i < n; i++) {
        int val;
        cin >> val;
        min_val = min(min_val, val);
        max_val = max(max_val, val);
    }
    
    vector<int> moves;
    for (int i = 0; i < MAXLOG; i++) {
        if (max_val == min_val) {
            break;
        }
        if (max_val % 2 == 1) {
            moves.push_back(0);
            max_val /= 2;
            min_val /= 2;
        } else {
            moves.push_back(1);
            max_val /= 2;
            if (min_val % 2 == 1) {
                min_val = min_val / 2 + 1;
            } else {
                min_val /= 2;
            }
        }
    }

    cout << moves.size() << endl;
    if (moves.size() <= n) {
        for (int move : moves) {
            cout << move << " ";
        }
        cout << endl;
    }
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