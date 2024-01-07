import java.util.*;
import java.io.*;

public class watering_well {
    static long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("watering_well_chapter_1_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            ArrayList<Point> xPoints = new ArrayList<Point>();
            ArrayList<Point> yPoints = new ArrayList<Point>();
            for(int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(line.nextToken());
                int b = Integer.parseInt(line.nextToken());
                xPoints.add(new Point(a, i, 0));
                yPoints.add(new Point(b, i, 0));
            }
            int q = Integer.parseInt(in.readLine());
            for(int i = 0; i < q; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(line.nextToken());
                int y = Integer.parseInt(line.nextToken());
                xPoints.add(new Point(x, i + n, 1));
                yPoints.add(new Point(y, i + n, 1));
            }

            Collections.sort(xPoints);
            Collections.sort(yPoints);
            long sum = modadd(sum_dists(xPoints), sum_dists(yPoints));

            Collections.reverse(xPoints);
            Collections.reverse(yPoints);
            sum = modadd(sum, modadd(sum_dists(xPoints), sum_dists(yPoints)));
            
            String res = "Case #" + t + ": ";
            out.println(res + sum); 
        }
        in.close();
        out.close();
    }
    static long sum_dists(ArrayList<Point> points) {
        int tree_count = 0;
        long dist_sum = 0;
        long squared_sum = 0;
        long res = 0;
        for(int i = 0; i < points.size(); i++) {
            if(i > 0) {
                int dist = Math.abs(points.get(i).x - points.get(i - 1).x);
                if(dist > 0) {
                    long fterm = modadd(modmult(2, dist_sum), tree_count);
                    long dif = modmult(2, tree_count);
                    long lterm = modadd(fterm, modmult(dif, modadd(dist, -1)));

                    dist_sum = modadd(dist_sum, modmult(tree_count, dist));
                    squared_sum = modadd(squared_sum, modmult(modadd(fterm, lterm), modmult(dist, modinv(2))));
                }
            }
            if(points.get(i).type == 0) {
                tree_count++;
            }else {
                res = modadd(res, squared_sum);
            }
        }
        return res;
    }
    static class Point implements Comparable<Point> {
        int x, id, type;
        Point(int xx, int i, int t) {
            x = xx;
            id = i;
            type = t;
        }
        @Override
        public int compareTo(Point o) {
            if(x == o.x) {
                return id - o.id;
            }
            return x - o.x;
        }
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
    static long modinv(long a) {
        return binpow(a, MOD - 2);
    }
    static long binpow(long a, long b) {
        if(b == 0) {
            return 1;
        }
        long small = binpow(a, b / 2);
        if(b % 2 == 0) {
            return modmult(small, small);
        }else {
            return modmult(modmult(small, small), a);
        }
    }
}
