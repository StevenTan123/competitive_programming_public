#include <bits/stdc++.h>
using namespace std;

void to_binary(long long x, vector<bool> &bits) {
    for (int i = 63; i >= 0; i--) {
        bits[i] = x & 1;
        x >>= 1;
    }
}

long long to_dec(vector<bool> &bits) {
    long long val = 0;
    for (int i = 0; i < 64; i++) {
        val <<= 1;
        val = val | bits[i];
    }
    return val;
}

void solve() {
    long long n, m;
    cin >> n >> m;
    vector<bool> nbits(64);
    vector<bool> mbits(64);
    to_binary(n, nbits);
    to_binary(m, mbits);

    int nhigh = -1;
    int mhigh = -1;
    for (int i = 0; i < 64; i++) {
        if (nbits[i] && nhigh == -1) {
            nhigh = i;
        }
        if (mbits[i] && mhigh == -1) {
            mhigh = i;
        }
    }
    bool good_bit = false;
    for (int i = nhigh + 1; i <= mhigh; i++) {
        if (nbits[i]) {
            good_bit = true;
            break;
        }
    }

    if (nhigh == mhigh || nbits[mhigh]) {
        cout << "1\n";
        cout << n << " " << m << "\n";
    } else if (good_bit) {
        vector<bool> intermediate = mbits;
        for (int i = nhigh + 1; i <= mhigh; i++) {
            if (nbits[i]) {
                intermediate[i] = 1;
            }
        }
        long long val = to_dec(intermediate);
        cout << "2\n";
        cout << n << " " << val << " " << m << "\n";
    } else {
        cout << "-1\n";
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