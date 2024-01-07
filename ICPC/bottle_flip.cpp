#include <bits/stdc++.h>
using namespace std;

const double epsilon = 1e-9;
int h, rad, a, w;

double eval(double x) {
    return ((w - a) * x * x + h * h * a) / ((w - a) * x + h * a) / 2;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> h >> rad >> a >> w;
    double l = 0;
    double r = h;
    while (r - l > epsilon) {
        double one = l + (r - l) / 3;
        double two = r - (r - l) / 3;
        double eval1 = eval(one);
        double eval2 = eval(two);
        if (eval1 < eval2) {
            r = two;
        } else {
            l = one;
        }
    }
    cout << fixed << setprecision(10);
    cout << l << endl;
}