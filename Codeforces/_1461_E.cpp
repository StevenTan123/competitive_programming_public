#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    long long k, l, r, t, y;
    int x;
    cin >> k >> l >> r >> t >> x >> y;

    if (x > y) {
        if (k + y <= r) {
            k += y;
        }
        k -= x;
        
        if (k < l) {
            cout << "NO" << endl;
        } else {
            long long days = (k - l) / (x - y);
            cout << (days >= t - 1 ? "YES" : "NO") << endl;
        }
    } else if (x < y) {
        bool seen[x] = {0};
        bool possible = true;
        while (t > 0) {
            long long lower = r - y;
            long long ceil = 0;
            if (k > lower) {
                ceil = (k - lower) / x;
                if ((k - lower) % x != 0) {
                    ceil++;
                }
            }
            
            ceil = min(t, ceil);
            if (k - x * ceil < l) {
                possible = false;
                break;
            }
            k -= x * ceil;
            t -= ceil;
            if (k <= lower) {
                k += y;
                if (seen[k % x]) {
                    break;
                }
                seen[k % x] = true;
            }
        }
        cout << (possible ? "YES" : "NO") << endl;
    } else {
        if (k + y <= r) {
            k += y;
        }
        if (k - x >= l) {
            cout << "YES" << endl;
        } else {
            cout << "NO" << endl;
        }
    }
}