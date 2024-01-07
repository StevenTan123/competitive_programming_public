#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;
const int MAXN = 300005;
long long pow2[MAXN];
long long pow3[MAXN];
vector<int> add[MAXN];
vector<int> rem[MAXN];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    pow2[0] = 1;
    pow3[0] = 1;
    for (int i = 1; i < MAXN; i++) {
        pow2[i] = pow2[i - 1] * 2 % MOD;
        pow3[i] = pow3[i - 1] * 3 % MOD;
    }

    int N;
    cin >> N;
    for (int i = 0; i < N; i++) {
        int l, r;
        cin >> l >> r;
        add[l].push_back(i);
        rem[r].push_back(i);
    }

    long long res = 0;
    set<int> active;
    for (int i = 0; i < MAXN; i++) {
        for (int val : add[i]) {
            active.insert(val);
        }

        if (!active.empty()) {
            int last = *active.rbegin();
            long long contrib = pow2[N - 1];
            if (last > 1) {
                contrib = pow3[last - 1] * pow2[N - last] % MOD;
            }
            res = (res + contrib) % MOD;
        }

        for (int val : rem[i]) {
            active.erase(val);
        }
    }
    cout << res << endl;
}