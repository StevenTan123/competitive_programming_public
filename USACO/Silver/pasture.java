import java.util.*;
import java.io.*;

public class pasture {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        Point[] points = new Point[n];
        int[] yvals = new int[n];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            points[i] = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
            yvals[i] = points[i].y;
        }
        Arrays.sort(points);
        Arrays.sort(yvals);
        HashMap<Integer, Integer> ymap = new HashMap<Integer, Integer>();
        for(int i = 0; i < n; i++) {
            ymap.put(yvals[i], i);
        }
        long res = 0;
        for(int i = 0; i < n; i++) {
            BIT bit = new BIT(new int[n]);
            TreeSet<Integer> active = new TreeSet<Integer>();
            int firsty = points[i].y;
            for(int j = i; j < n; j++) {
                int cury = points[j].y;
                active.add(cury);
                int index = ymap.get(cury);
                if(bit.sum(index, index) == 0) {
                    bit.update(index, 1);
                }
                int maxy = Math.max(firsty, cury);
                int miny = Math.min(firsty, cury);
                int lower = bit.sum(0, ymap.get(miny));
                int higher = active.size() - (ymap.get(maxy) > 0 ? bit.sum(0, ymap.get(maxy) - 1) : 0);
                res += (long)higher * lower;
            } 
        }
        out.println(res + 1);
        in.close();
        out.close();
    }
    static class Point implements Comparable<Point> {
        int x, y;
        Point(int xx, int yy) {
            x = xx;
            y = yy;
        }
        @Override
        public int compareTo(Point o) {
            return x - o.x;
        }
    } 
    static class BIT {
        int[] bit;
        BIT(int[] a) {
            bit = new int[a.length + 1];
            for(int i = 0; i < a.length; i++) {
                update(i, a[i]);
            }
        }
        void update(int index, int add) {
            index++;
            while(index < bit.length) {
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
            while(index > 0) {
                res += bit[index];
                index -= index & -index;
            }
            return res;
        }
    }
}
