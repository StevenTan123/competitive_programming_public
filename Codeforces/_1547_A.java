import java.util.*;
import java.io.*;

public class _1547_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            in.readLine();
            Point[] points = new Point[3];
            for(int i = 0; i < 3; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                points[i] = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
            }
            boolean plus2 = false;
            if(points[0].x == points[1].x && points[1].x == points[2].x) {
                int maxy = Math.max(points[0].y, points[1].y);
                int miny = Math.min(points[0].y, points[1].y);
                if(points[2].y > miny && points[2].y < maxy) {
                    plus2 = true;
                }
            }
            if(points[0].y == points[1].y && points[1].y == points[2].y) {
                int maxx = Math.max(points[0].x, points[1].x);
                int minx = Math.min(points[0].x, points[1].x);
                if(points[2].x > minx && points[2].x < maxx) {
                    plus2 = true;
                }
            }
            int res = Math.abs(points[0].x - points[1].x) + Math.abs(points[0].y - points[1].y);
            if(plus2) res += 2;
            out.println(res);
        }
        in.close();
        out.close();
    }
    static class Point {
        int x, y;
        Point(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }
}
