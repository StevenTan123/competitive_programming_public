public class LineIntersection {
    static boolean intersect(Segment s1, Segment s2) {
        int o1 = orientation(s1.p1, s1.p2, s2.p1);
        int o2 = orientation(s1.p1, s1.p2, s2.p2);
        int o3 = orientation(s2.p1, s2.p2, s1.p1);
        int o4 = orientation(s2.p1, s2.p2, s1.p2);
        if(o1 != o2 && o3 != o4) {
            return true;
        }
        if(o1 == 0 && onsegment(s2.p1, s1)) {
            return true;
        }
        if(o2 == 0 && onsegment(s2.p2, s1)) {
            return true;
        }
        if(o3 == 0 && onsegment(s1.p1, s2)) {
            return true;
        }
        if(o4 == 0 && onsegment(s1.p2, s2)) {
            return true;
        }
        return false;
    }
    static int orientation(Point p1, Point p2, Point p3) {
        long val = (p2.y - p1.y) * (p3.x - p2.x) - (p2.x - p1.x) * (p3.y - p2.y);
        if(val == 0) {
            return 0;
        }
        return val > 0 ? 1 : -1;
    }
    static boolean onsegment(Point p, Segment s) {
        if(p.x >= Math.min(s.p1.x, s.p2.x) && p.x <= Math.max(s.p1.x, s.p2.x) && p.y >= Math.min(s.p1.y, s.p2.y)
                && p.y <= Math.max(s.p1.y, s.p2.y)) {
            return true;
        }
        return false;
    }
    static class Point {
        int x, y;
        Point(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }
    static class Segment {
        Point p1, p2;
        Segment(Point pp1, Point pp2) {
            p1 = pp1;
            p2 = pp2;
        }
    }
}
