#include <bits/stdc++.h>
using namespace std;

const int inf = 1e9 + 5;

struct Point {
    int x, y;
    Point() : x(0), y(0) {}
    Point(int xx, int yy) : x(xx), y(yy) {}
};

long long dist_sq(Point a, Point b) {
    return (long long) (a.x - b.x) * (a.x - b.x) + (long long) (a.y - b.y) * (a.y - b.y);
}

// 0 -> collinear, -1 -> clockwise, 1 -> counter-clockwise
int orientation(Point a, Point b, Point c) {
    // The determinant of the matrix | vec(a->b) vec(a->c) |
    long long det = (long long) (b.x - a.x) * (c.y - a.y) - (long long) (c.x - a.x) * (b.y - a.y);
    if (det < 0) {
        return -1;
    } else if (det > 0) {
        return 1;
    }
    return 0;
}

vector<Point> convex_hull(vector<Point> points) {
    // Finds smallest y coordinate point, ties broken with smaller x coordinate
    Point p0 = *min_element(points.begin(), points.end(), [](Point a, Point b) {
        return make_pair(a.y, a.x) < make_pair(b.y, b.x);
    });

    // Sort points in clockwise order relative to p0
    sort(points.begin(), points.end(), [&p0](const Point& a, const Point& b) {
        int o = orientation(p0, a, b);
        if (o == 0) {
            return dist_sq(p0, a) < dist_sq(p0, b);
        }
        return o < 0;
    });
    
    vector<Point> st;
    for (int i = 0; i < (int) points.size(); i++) {
        while (st.size() > 1 && orientation(st[st.size()-2], st.back(), points[i]) >= 0) {
            st.pop_back();
        }
        st.push_back(points[i]);
    }
    return st;
}
