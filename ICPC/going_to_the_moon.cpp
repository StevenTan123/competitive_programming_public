#include <bits/stdc++.h>
using namespace std;
 
const double PI = acos(-1);
 
double epsilon = 1e-11;
int xa, ya, xb, yb, r;
 
template<class T>
struct Point {
    typedef Point P;
    T x, y;
    explicit Point(T xx=0, T yy=0) : x(xx), y(yy) {}
    bool operator==(P p) const { return tie(x, y) == tie(p.x, p.y); }
    P operator-(P p) const { return P(x - p.x, y - p.y); }
    P operator*(T d) const { return P(x * d, y * d); }
    T dot(P p) const { return x * p.x + y * p.y; }
    T dist2() const { return x * x + y * y; }
    double dist() const { return sqrt((double) dist2()); } 
};
 
double find_angle(int x, int y) {
    if (x == 0) {
        if (y > 0) {
            return PI / 2;
        } else {
            return 3 * PI / 2;
        }
    } else {
        double angle = atan((double) y / x);
        if (x < 0) {
            angle += PI;
        }
        if (angle < 0) {
            angle += 2 * PI;
        }
        return angle;
    }
}
 
double dist(double x1, double y1, double x2, double y2) {
    return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
}
 
double f(double theta) {
    double xc = r * cos(theta);
    double yc = r * sin(theta);
    return dist(xc, yc, xa, ya) + dist(xc, yc, xb, yb);
}
 
typedef Point<double> P;
double segDist(P &s, P &e, P& p) {
    if (s == e) return (p - s).dist();
    double d = (e - s).dist2();
    double t = min(d, max(.0, (p - s).dot(e - s)));
    return ((p - s) * d - (e - s) * t).dist() / d;
}
 
typedef Point<double> P;
void solve() {
    int xc, yc;
    cin >> xa >> ya >> xb >> yb >> xc >> yc >> r;
    xa -= xc; ya -= yc; xb -= xc; yb -= yc;
 
    P ap(xa, ya); P bp(xb, yb); P zero(0, 0);
    double seg_dist = segDist(ap, bp, zero);
    if (seg_dist <= r + epsilon) {
        cout << fixed << setprecision(10) << dist(xa, ya, xb, yb) << "\n";
    } else {
        double theta1 = find_angle(xa, ya);
        double theta2 = find_angle(xb, yb);
 
        double left = min(theta1, theta2);
        double right = max(theta1, theta2);
        if (right > left + PI) {
            swap(left, right);
            left -= 2 * PI;
        }
 
        while (right - left > epsilon) {
            double m1 = left + (right - left) / 3;
            double m2 = left + 2 * (right - left) / 3;
 
            double f1 = f(m1);
            double f2 = f(m2);
            if (f1 > f2) {
                left = m1;
            } else {
                right = m2;
            }
        }        
        cout << fixed << setprecision(10) << f(left) << "\n";
    }
}
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
 
    int T;
    cin >> T;
    while (T--) {
        solve();
    }
}