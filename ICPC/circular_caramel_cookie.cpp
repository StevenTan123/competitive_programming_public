#include <bits/stdc++.h>
using namespace std;

const double epsilon = 1e-10;
const int MAXR = 500005;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int s;
    cin >> s;
    double l = 1;
    double r = MAXR;
    while (r - l > epsilon) {
        double m = (l + r) / 2;
        long long count = 0;
        for (int x = 1; x < m; x++) {
            // x^2 + y^2 <= m^2
            int y = sqrt(m * m - x * x);
            count += 2 * y;
        }
        count *= 2;
        if (count > s) {
            r = m;
        } else {
            l = m;
        }
    }
    cout << fixed << setprecision(10);
    cout << l << endl;
}