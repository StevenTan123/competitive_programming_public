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

// Given a, b, c are collinear, check if b is on segment ac
bool on_seg(Point a, Point b, Point c) {
    return (b.x >= min(a.x, c.x) && b.x <= max(a.x, c.x) && b.y >= min(a.y, c.y) && b.y <= max(a.y, c.y));
}

// Checks if line segments p1q1 and p2q2 intersect
bool intersect(Point p1, Point q1, Point p2, Point q2) { 
    int o1 = orientation(p1, q1, p2); 
    int o2 = orientation(p1, q1, q2); 
    int o3 = orientation(p2, q2, p1); 
    int o4 = orientation(p2, q2, q1); 
 
    // General case 
    if (o1 != o2 && o3 != o4) {
        return true; 
    }
  
    // Special Cases 
    if (o1 == 0 && on_seg(p1, p2, q1)) return true; 
    if (o2 == 0 && on_seg(p1, q2, q1)) return true; 
    if (o3 == 0 && on_seg(p2, p1, q2)) return true; 
    if (o4 == 0 && on_seg(p2, q1, q2)) return true; 

    return false;
}

// In this problem specifically, these polygons are only upper or lower convex hulls
struct Polygon {
    int n;
    vector<Point> points;
    set<pair<int, int>> sorted;

    Polygon(vector<Point> &pts) : n(pts.size()), points(pts) {
        for (int i = 0; i < n; i++) {
            Point p = points[i];
            if (p.y <= -5 || p.y >= inf) {
                continue;
            }
            sorted.insert({p.x, i});
        }
    }

    // Returns whether point is strictly inside polygon. Fast method of checking
    // that works only for upper/lower convex hulls.
    bool point_in_polygon(Point p, bool up) {
        int y = (up ? inf : -5);
        // Point out to infinity
        Point q(p.x, y);
        
        auto it = sorted.lower_bound({p.x, 0});
        if (it != sorted.end()) {
            int ind = it->second;

            Point p1 = points[(ind + n - 1) % n];
            Point p2 = points[ind];
            Point p3 = points[(ind + 1) % n];
            if ((orientation(p1, p, p2) == 0 && on_seg(p1, p, p2)) || (orientation(p2, p, p3) == 0 && on_seg(p2, p, p3))) {
                return false;
            }
            if (intersect(p1, p2, p, q) || intersect(p2, p3, p, q)) {
                return true;
            }
        }
        return false;
    }

    // Returns whether polygon intersects with another polygon
    bool poly_intersect(Polygon &o, bool up) {
        for (Point p : points) {
            if (o.point_in_polygon(p, up)) {
                return true;
            }
        }
        for (Point p : o.points) {
            if (point_in_polygon(p, !up)) {
                return true;
            }
        }
        return false;
    }
};

Polygon convex_hull(vector<Point> points) {
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
    return Polygon(st);
}

void add_infs(vector<Point> &points, int y) {
    int minx = inf;
    int maxx = -5;
    for (Point p : points) {
        minx = min(minx, p.x);
        maxx = max(maxx, p.x);
    }
    points.push_back(Point(minx, y));
    points.push_back(Point(maxx, y));
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    vector<Point> up_l, up_r, bo_l, bo_r;
    for (int i = 0; i < n; i++) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        bo_l.push_back(Point(x1, y1));
        up_r.push_back(Point(x2, y2));
        bo_r.push_back(Point(x2, y1));
        up_l.push_back(Point(x1, y2));
    }
    add_infs(up_l, inf); add_infs(up_r, inf); add_infs(bo_l, -5); add_infs(bo_r, -5);
    
    Polygon p1 = convex_hull(up_l);
    Polygon p2 = convex_hull(bo_r);
    Polygon p3 = convex_hull(up_r);
    Polygon p4 = convex_hull(bo_l);
    if (!p1.poly_intersect(p2, true) || !p3.poly_intersect(p4, true)) {
        cout << "possible" << endl;
    } else {
        cout << "impossible" << endl;
    }
}