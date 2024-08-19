#include <bits/stdc++.h>
using namespace std;

long long aseq_sum(int a, int d, int n) {
    if (a > n) {
        return 0;
    }
    int num = (n - a) / d + 1;
    return (long long) (a + a + d * (num - 1)) * num / 2;
}

int ceil_div(int a, int b) {
    return (a + b - 1) / b;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    int best_k = 1;
    long long res = 0;
    for (int i = 2; i <= min(m, n); i++) {
        long long cur = 0;
        if (n % i == 0) {
            cur = aseq_sum(i, i, n);
        } else if (__gcd(i, n) == 1) {
            int start = i;
            int avail = 2;
            for (int j = 0; j < i; j++) {
                if (avail > n) {
                    break;
                }
                int add = ceil_div(n + 1 - start, i);
                if (avail > start) {
                    start += ceil_div(avail - start, i - 1) * i;
                }
                cur += aseq_sum(start, i, n);
                start += ceil_div(n + 1 - start, i) * i;
                start = (start - 1) % n + 1;
                avail += add;
            }
        } else {
            continue;
        }

        if (cur > res) {
            res = cur;
            best_k = i;
        }
    }
    cout << best_k << "\n";
}