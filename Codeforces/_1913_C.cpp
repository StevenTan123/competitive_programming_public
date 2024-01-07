#include <bits/stdc++.h>
using namespace std;

const int MAXP = 32;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int m;
    cin >> m;
    int pcounts[MAXP] = {0};
    for (int i = 0; i < m; i++) {
        int t, v;
        cin >> t >> v;
        if (t == 1) {
            pcounts[v]++;
        } else {
            int carry = 0;
            bool possible = true;
            for (int j = 0; j < MAXP; j++) {
                int digit = v % 2;
                carry += pcounts[j];
                if (digit == 1) {
                    if (carry < 1) {
                        possible = false;
                        break;
                    }
                    carry--;
                }
                carry /= 2;
                v /= 2;
            }
            cout << (possible ? "YES\n" : "NO\n");
        }
    }
}