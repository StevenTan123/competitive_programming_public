import java.util.*;
import java.io.*;

public class balancing {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("balancing.in"));
        PrintWriter out = new PrintWriter("balancing.out");
        int n = Integer.parseInt(in.readLine());
        Point[] points = new Point[n];
        TreeSet<Integer> yvals = new TreeSet<Integer>();
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            points[i] = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
            yvals.add(points[i].y);
        }
        Comparator<Point> byx = new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                if(a.x == b.x) {
                    return a.y - b.y;
                }
                return a.x - b.x;
            }
        };
        Comparator<Point> byy = new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                if(a.y == b.y) {
                    return a.x - b.x;
                }
                return a.y - b.y;
            }
        };
        Arrays.sort(points, byx);
        HashMap<Integer, Integer> ymap = new HashMap<Integer, Integer>();
        int[] ya = new int[yvals.size()];
        int count = 0;
        for(int yval : yvals) {
            ya[count] = yval;
            ymap.put(yval, count);
            count++;
        }
        TreeSet<Point> left = new TreeSet<Point>(byy);
        TreeSet<Point> right = new TreeSet<Point>(byy);
        BIT bitl = new BIT(new int[yvals.size()]);
        BIT bitr = new BIT(new int[yvals.size()]);
        for(int i = 0; i < n; i++) {
            right.add(points[i]);
            bitr.update(ymap.get(points[i].y), 1);
        }
        int min = n;
        for(int i = 0; i < n; i++) {
            left.add(points[i]);
            bitl.update(ymap.get(points[i].y), 1);
            right.remove(points[i]);
            bitr.update(ymap.get(points[i].y), -1);
            if(i < n - 1 && points[i].x == points[i + 1].x) {
                continue;
            }
            int l = 0;
            int r = ya.length - 1;
            while(l <= r) {
                int avg = (l + r) / 2;
                Point lp = left.lower(new Point(1000005, ya[avg]));
                Point rp = right.lower(new Point(1000005, ya[avg]));
                int lsmall = lp == null ? 0 : bitl.sum(0, ymap.get(lp.y));
                int rsmall = rp == null ? 0 : bitr.sum(0, ymap.get(rp.y));
                int lmax = Math.max(lsmall, left.size() - lsmall);
                int rmax = Math.max(rsmall, right.size() - rsmall);
                min = Math.min(min, Math.max(lmax, rmax));
                if(lmax > rmax) {
                    if(lsmall == lmax) {
                        r = avg - 1;
                    }else {
                        l = avg + 1;
                    }
                }else {
                    if(rsmall == rmax) {
                        r = avg - 1;
                    }else {
                        l = avg + 1;
                    }
                }
            }
        }
        out.println(min);
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
    static class BIT {
        int[] bit;
        BIT(int[] a) {
            bit = new int[a.length + 1];
            for (int i = 0; i < a.length; i++) {
                update(i, a[i]);
            }
        }
        void update(int index, int add) {
            index++;
            while (index < bit.length) {
                bit[index] += add;
                index += index & -index;
            }
        }
        int sum(int l, int r) {
            return psum(r) - (l == 0 ? 0 : psum(l - 1));
        }
        int psum(int index) {
            index++;
            int res = 0;
            while (index > 0) {
                res += bit[index];
                index -= index & -index;
            }
            return res;
        }
    }
}
