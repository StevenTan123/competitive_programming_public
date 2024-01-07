import java.util.*;
import java.io.*;

public class boards {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("boards.in"));
        PrintWriter out = new PrintWriter("boards.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int p = Integer.parseInt(line.nextToken());
        Point[] points = new Point[2 * p + 4];
        for(int i = 0; i < p; i++) {
            line = new StringTokenizer(in.readLine());
            points[i * 2] = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()), 0, null, 1);
            points[i * 2 + 1] = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()), 1, points[i * 2], 0);
        }
        points[2 * p] = new Point(0, 0, 0, null, 0);
        points[2 * p + 1] = new Point(0, 0, 1, points[2 * p], 0);
        points[2 * p + 2] = new Point(n, n, 0, null, 1);
        points[2 * p + 3] = new Point(n, n, 1, points[2 * p + 2], 1);
        Arrays.sort(points);
        int[] d = new int[p + 2];
        int pointer = 0;
        for(int i = 0; i < points.length; i++) {
            if(points[i].t == 0) {
                points[i].i = pointer;
                d[pointer] = points[i].x + points[i].y;
                pointer++;
            }
        }
        TreeSet<Entry> map = new TreeSet<Entry>();
        pointer = 0;
        for(int i = 0; i < points.length; i++) {
            if(points[i].t == 0) {
                Entry lower = map.lower(new Entry(points[i].y, 0, 1000000000));
                if(lower != null) d[pointer] = lower.val + points[i].x + points[i].y;
                pointer++;
            }else {
                int curval = d[points[i].first.i] - points[i].x - points[i].y;
                boolean add = true;
                TreeSet<Entry> newmap = new TreeSet<Entry>();
                for(Entry e : map) {
                    if(e.y <= points[i].y && e.val <= curval) add = false;
                    if(!(e.y >= points[i].y && e.val >= curval)) newmap.add(e);
                }
                map = newmap;
                if(add) map.add(new Entry(points[i].y, curval, i));
            }
        }
        out.println(d[p + 1]);
        in.close();
        out.close();
    }
    static class Point implements Comparable<Point> {
        int x, y, t, i, end;
        Point first;
        Point(int xx, int yy, int tt, Point f, int e) {
            x = xx;
            y = yy;
            t = tt;
            first = f;
            end = e;
        }
        @Override
        public int compareTo(Point o) {
            if(x == o.x) {
                if(y == o.y) {
                    if(end == o.end) return t - o.t;
                    return end - o.end;
                }
                return y - o.y;
            }
            return x - o.x;
        }
    } 
    static class Entry implements Comparable<Entry> {
        int y, val, id;
        Entry(int yy, int vv, int ii) {
            y = yy;
            val = vv;
            id = ii;
        }
        @Override
        public int compareTo(Entry o) {
            if(y == o.y) return id - o.id;
            return y - o.y;
        }
    }
}
