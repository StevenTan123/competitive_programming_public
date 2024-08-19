#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    long long n, k, p;
    cin >> n >> k >> p;
    vector<long long> divisors;
    for (long long i = 1; i * i <= n; i++) {
        if (n % i == 0) {
            divisors.push_back(i);
            if (i * i != n) {
                divisors.push_back(n / i);
            }
        }
    }
    vector<long long> res;
    for (long long divisor : divisors) {
        if (divisor <= k && n / divisor <= p) {
            res.push_back(divisor);
        }
    }
    sort(res.begin(), res.end());
    cout << res.size() << "\n";
    for (long long val : res) {
        cout << val << "\n";
    }
}