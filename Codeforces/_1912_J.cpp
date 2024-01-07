#include <bits/stdc++.h>
using namespace std;

void solve() {
    int t, s;
    cin >> t >> s;
    int l[s];
    for (int i = 0; i < s; i++) {
        cin >> l[i];
    }

    if (s == 1) {
        if (t % l[0] == 0) {
            cout << "1\n";
        } else {
            cout << "0\n";
        }
    } else if (s == 2) {
        int lcm = l[0] * l[1] / __gcd(l[0], l[1]);
        long long res = 0;
        for (int i = 0; i < lcm / l[0]; i++) {
            for (int j = 0; j < lcm / l[1]; j++) {
                int left = t - i * l[0] - j * l[1];
                if (left >= 0 && left % lcm == 0) {
                    int quo = left / lcm;
                    res += quo + 1;
                }
            }
        }
        cout << res << "\n";
    } else {
        int lcm = l[0] * l[1] / __gcd(l[0], l[1]);
        lcm = lcm * l[2] / __gcd(lcm, l[2]);
        long long res = 0;
        for (int x = 0; x <= 3; x++) {
            int residue = t % lcm + x * lcm;
            if (residue > t) {
                break;
            }
            for (int i = 0; i < lcm / l[0]; i++) {
                for (int j = 0; j < lcm / l[1]; j++) {
                    int left = residue - i * l[0] - j * l[1];
                    if (left >= 0 && left % l[2] == 0 && left < lcm) {
                        int quo = t / lcm - x;
                        res += (long long) (quo + 2) * (quo + 1) / 2;
                    }
                }
            }
        }
        cout << res << "\n";
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int h;
    cin >> h;
    while (h--) {
        solve();
    }
}