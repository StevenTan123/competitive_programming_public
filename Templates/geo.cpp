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

struct Polygon {
    int n;
    vector<Point> points;

    Polygon() : n(0) {}
    Polygon(vector<Point> &pts) : n(pts.size()), points(pts) {}

    // Returns whether point is strictly inside polygon
    bool point_in_polygon(Point p) {
        // Point out to infinity
        Point q(inf, p.y + 1);
        int intersect_cnt = 0;
        for (int i = 0; i < n; i++) {
            Point p1 = points[(i + n - 1) % n];
            Point p2 = points[i];
            // Point is on border
            if (orientation(p1, p, p2) == 0 && on_seg(p1, p, p2)) {
                return false;
            }
            if (intersect(p1, p2, p, q)) {
                intersect_cnt++;
            }
        }
        return (intersect_cnt % 2 == 1);
    }
};