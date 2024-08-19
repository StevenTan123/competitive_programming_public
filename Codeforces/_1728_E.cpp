#include <bits/stdc++.h>
using namespace std;

long long gcd(long long a, long long b, long long& x, long long& y) {
    if (b == 0) {
        x = 1;
        y = 0;
        return a;
    }
    long long x1, y1;
    long long d = gcd(b, a % b, x1, y1);
    x = y1;
    y = x1 - y1 * (a / b);
    return d;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    int a[n];
    int b[n];
    int diffs[n];
    long long bsum = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        cin >> b[i];
        diffs[i] = b[i] - a[i];
        bsum += b[i];
    }
    sort(diffs, diffs + n);
    long long diff_sum[n];
    int target = 0;
    for (int i = 0; i < n; i++) {
        diffs[i] *= -1;
        diff_sum[i] = (i > 0 ? diff_sum[i - 1] : 0) + diffs[i];
        if (diffs[i] >= 0) {
            target = i + 1;
        }
    }
    
    int m;
    cin >> m;
    for (int i = 0; i < m; i++) {
        long long red, black;
        cin >> red >> black;

        long long x = 0;
        long long y = 0;
        long long GCD = gcd(red, black, x, y);
        if (n % GCD != 0) {
            cout << "-1\n";
            continue;
        }
        long long lcm = red / GCD * black;

        long long num_red = x * n / GCD * red;
        if (num_red < target) {
            long long floor = (target - num_red) / lcm;
            num_red += floor * lcm;
        } else {
            long long ceil = (num_red - target) / lcm;
            if ((num_red - target) % lcm != 0) {
                ceil++;
            }
            num_red -= ceil * lcm;
        }
        
        long long res = -1;
        for (int j = -5; j <= 5; j++) {
            long long cur_red = num_red + j * lcm;
            if (cur_red >= 0 && cur_red <= n) {
                res = max(res, (cur_red > 0 ? diff_sum[cur_red - 1] : 0) + bsum);
            }
        }
        cout << res << "\n";
    }
}