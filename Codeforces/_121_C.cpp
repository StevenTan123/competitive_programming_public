#include <bits/stdc++.h>
using namespace std;

const int MAXF = 14;
long long fact[MAXF];

bool is_lucky(int val) {
    while (val > 0) {
        if (val % 10 != 7 && val % 10 != 4) {
            return false;
        }
        val /= 10;
    }
    return true;
}

int gen_vals(int n, int k, long long cur, vector<long long> &p) {
    if (cur > n) {
        return 0;
    }
    bool works = false;
    if (cur > 0) {
        if (cur - 1 < n - (int) p.size()) {
            works = true;
        } else if (is_lucky(p[cur - 1 - (n - (int) p.size())])) {
            works = true;
        }
    }
    return works + gen_vals(n, k, cur * 10 + 4, p) + gen_vals(n, k, cur * 10 + 7, p);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    fact[0] = 1;
    for (int i = 1; i < MAXF; i++) {
        fact[i] = fact[i - 1] * i;
    }

    int n, k;
    cin >> n >> k;
    k--;

    if (n < MAXF && k >= fact[n]) {
        cout << "-1\n";
        return 0;
    }

    vector<long long> p;
    vector<int> vals;
    for (int i = max(0, n - MAXF); i < n; i++) {
        vals.push_back(i + 1);
    }
    for (int i = max(0, n - MAXF); i < n; i++) {
        int left = n - i - 1;
        long long use = k / fact[left];
        k -= use * fact[left];
        p.push_back(vals[use]);
        vals.erase(vals.begin() + use);
    }
    cout << gen_vals(n, k, 0, p) << "\n";
}