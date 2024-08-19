#include <bits/stdc++.h>
using namespace std;

const double epsilon = 1e-10;
const int MAXN = 1000005;
int q[MAXN];
int f[MAXN];
pair<int, int> rem[MAXN];
long long marked[MAXN] = {0};

bool survivable(int n, double val) {
    // maintain amount of unspoiled food left, and theoretical max food if nobody eats
    double food = 0;
    long long max_food = 0;
    int p = 0;
    for (int i = 0; i < n; i++) {
        food += q[i];
        max_food += q[i];
        if (val > food) {
            return false;
        }
        food -= val;

        while (p < n && rem[p].first == i) {
            int day = rem[p].second;
            double eaten = max_food - food;
            max_food -= q[day];
            if (eaten < q[day]) {
                food = max_food;
            }
            p++;
        }
    }
    return true;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k;
    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        cin >> q[i];
        cin >> f[i];
        f[i]--;
        marked[i] += q[i];
        marked[f[i] + 1] -= q[i];
        rem[i] = {f[i], i};
    }
    sort(rem, rem + n);

    bool possible = true;
    long long cur_food = 0;
    for (int i = 0; i < n; i++) {
        cur_food += marked[i];
        if (cur_food <= 0) {
            possible = false;
            break;
        }
    }

    if (possible) {
        long double l = 0;
        long double r = 1e9 + 5;
        while (r - l > epsilon) {
            long double m = (l + r) / 2;
            if (survivable(n, m)) {
                l = m;
            } else {
                r = m;
            }
        }
        cout << fixed << setprecision(10) << (l / k) << "\n";
    } else {
        cout << "-1\n";
    }
}