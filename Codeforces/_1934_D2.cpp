#include <bits/stdc++.h>
using namespace std;

int solve() {
    long long n;
    cin >> n;

    int set_bits = __builtin_popcountll(n);
    if (set_bits % 2 == 0) {
        cout << "first" << endl;
    } else {
        n = -1;
        cout << "second" << endl;
    }
    while (true) {
        if (n == -1) {
            long long p1, p2;
            cin >> p1 >> p2;
            if (p1 == 0 && p2 == 0) {
                return 0;
            }
            if (p1 == -1 && p2 == -1) {
                return 1;
            }
            int set1 = __builtin_popcountll(p1);
            n = (set1 % 2 == 0) ? p1 : p2;
        }

        long long o;
        for (int i = 61; i >= 0; i--) {
            o = (long long) 1 << i;
            if (n & o) {
                n ^= o;
                break;
            }
        }
        cout << n << " " << o << endl;
        n = -1;
    }
    return 0;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t--) {
        if (solve()) {
            break;
        }
    }
}