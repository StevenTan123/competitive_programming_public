#include <bits/stdc++.h>
using namespace std;

void gen_divisors(map<int, int> &factors, map<long long, int> &divisors, int pfactor, long long divisor) {
    divisors[divisor] = 0;
    auto it = factors.upper_bound(pfactor);
    if (it != factors.end()) {
        gen_divisors(factors, divisors, it->first, divisor);
        for (int i = 0; i < it->second; i++) {
            divisor *= it->first;
            gen_divisors(factors, divisors, it->first, divisor);
        }
    }
}

void solve() {
    int n, m1, m2;
    cin >> n >> m1 >> m2;

    map<int, int> pfactors;
    for (int i = 2; i * i <= m1; i++) {
        while (m1 % i == 0) {
            pfactors[i]++;
            m1 /= i;
        }
    } 
    if (m1 > 1) {
        pfactors[m1]++;
    }
    for (int i = 2; i * i <= m2; i++) {
        while (m2 % i == 0) {
            pfactors[i]++;
            m2 /= i;
        }
    }
    if (m2 > 1) {
        pfactors[m2]++;
    }
    
    map<long long, int> divisors;
    gen_divisors(pfactors, divisors, 0, 1);
    int res = 0;
    int cnt = 0;
    for (auto it = divisors.begin(); it != divisors.end(); it++) {
        if (it->first <= n) {
            divisors[it->first] = it->first;
        } else {
            int max_row = 0;
            for (auto it2 = pfactors.begin(); it2 != pfactors.end(); it2++) {
                if (it->first % it2->first == 0) {
                    long long prev = it->first / it2->first;
                    long long prev_max = divisors[prev];
                    if (prev_max != 0 && it->first / prev_max <= prev_max && prev_max > max_row) {
                        max_row = prev_max;
                    } 
                }
            }
            divisors[it->first] = max_row;
        }
        if (divisors[it->first] != 0) {
            cnt++;
            res ^= it->first / divisors[it->first];
        }
    }
    cout << cnt << " " << res << "\n";

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