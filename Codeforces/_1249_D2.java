import java.util.*;
import java.io.*;

public class _1249_D2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        Point[][] segs = new Point[n][2];
        TreeSet<Point> sorted = new TreeSet<Point>(new Comparator<Point>(){
            @Override
            public int compare(Point a, Point b) {
                if(a.x == b.x) {
                    if(a.t == b.t) {
                        return a.i - b.i;
                    }
                    return b.t - a.t;
                }
                return a.x - b.x;
            }
        });
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(line.nextToken());
            int r = Integer.parseInt(line.nextToken());
            segs[i][0] = new Point(0, l, i);
            segs[i][1] = new Point(1, r + 1, i);
            sorted.add(segs[i][0]);
            sorted.add(segs[i][1]);
        }
        TreeSet<Point> active = new TreeSet<Point>(new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                int aend = segs[a.i][1].x;
                int bend = segs[b.i][1].x;
                if(aend == bend) {
                    return a.i - b.i;
                }
                return bend - aend;
            }
        });
        ArrayList<Integer> remove = new ArrayList<Integer>();
        for(Point p : sorted) {
            if(p.t == 0) {
                active.add(p);
                if(active.size() > k) {
                    remove.add(active.pollFirst().i);
                }
            }else {
                Point start = segs[p.i][0];
                if(active.contains(start)) active.remove(start);
            }
        }
        out.println(remove.size());
        StringBuilder sb = new StringBuilder();
        for(int i : remove) {
            sb.append(i + 1);
            sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
    static class Point {
        int t, x, i;
        Point(int tt, int xx, int ii) {
            t = tt;
            x = xx;
            i = ii;
        }
    }
}
