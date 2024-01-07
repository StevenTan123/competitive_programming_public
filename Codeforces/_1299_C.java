import java.util.*;
import java.io.*;

public class _1299_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        StringTokenizer line = new StringTokenizer(in.readLine());
        int[] a = new int[n + 1];
        long[] psum = new long[n + 1];
        Point[] slopes = new Point[n];
        for(int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            psum[i] = psum[i - 1] + a[i];
            slopes[i - 1] = new Point(i, psum[i]);
        }
        Arrays.sort(slopes);
        Stack<Point> points = new Stack<Point>();
        points.push(new Point(0, 0));
        for(int i = 0; i < n; i++) {
            while(points.size() >= 2) {
                Point p1 = points.get(points.size() - 2);
                Point p2 = points.get(points.size() - 1);
                if(orientation(p1, p2, slopes[i]) < 1) {
                    points.pop();
                }else {
                    break;
                }
            }
            points.push(slopes[i]);
            if(slopes[i].x == n) {
                break;
            }
        }
        int prev = -1;
        for(Point p : points) {
            if(prev != -1) {
                double avg = (double)(psum[p.x] - psum[prev]) / (p.x - prev);
                for(int i = 0; i < p.x - prev; i++) {
                    out.println(avg);
                }
            }
            prev = p.x;
        }
        in.close();
        out.close();
    }
    static int orientation(Point a, Point b, Point c) {
        double v = a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y);
        if(v < 0) {
            return -1; //clockwise
        }else if(v > 0) {
            return 1; //counter-clockwise
        }
        return 0;
    }
    static class Point implements Comparable<Point> {
        int x;
        long y;
        double slope;
        Point(int xx, long yy) {
            x = xx;
            y = yy;
            if(x != 0) {
                slope = (double)y / x;
            }
        }
        @Override
        public int compareTo(Point o) {
            if(slope > o.slope) {
                return 1;
            }else if(slope < o.slope) {
                return -1;
            }
            return x - o.x;
        }
    }
}
