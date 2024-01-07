#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    if (n == 1) {
        cout << "1\n";
    } else if (n == 3) {
        cout << "169\n196\n961\n";
    } else {
        for (int i = 3; i <= n; i += 2) {
            stringstream ss1;
            stringstream ss2;
            ss1 << '1';
            ss2 << '9';
            for (int j = 0; j < n - 1; j++) {
                ss1 << '0';
                ss2 << '0';
            }
            string s1 = ss1.str();
            string s2 = ss2.str();
            s1[(i + 1) / 2 - 1] = '6';
            s1[i - 1] = '9';
            s2[(i + 1) / 2 - 1] = '6';
            s2[i - 1] = '1';
            cout << s1 << "\n" << s2 << "\n";
        }
        stringstream ss;
        ss << "196";
        for (int i = 0; i < n - 3; i++) {
            ss << '0';
        }    
        cout << ss.str() << "\n";
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