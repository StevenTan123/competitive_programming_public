import java.util.*;
import java.io.*;

public class _1869_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int a = Integer.parseInt(line.nextToken()) - 1;
            int b = Integer.parseInt(line.nextToken()) - 1;
            int[][] cities = new int[n][2];
            for (int i = 0; i < n; i++) {
                line = new StringTokenizer(in.readLine());
                cities[i][0] = Integer.parseInt(line.nextToken());
                cities[i][1] = Integer.parseInt(line.nextToken());
            }
            long min_start = 100000000000l;
            long min_end = 100000000000l;
            for (int i = 0; i < k; i++) {
                min_start = Math.min(min_start, dist(cities, i, a));
                min_end = Math.min(min_end, dist(cities, i, b));
            }
            long res = dist(cities, a, b);
            res = Math.min(res, min_start + min_end);
            out.println(res);
        }
        in.close();
        out.close();
    }
    static long dist(int[][] cities, int i, int j) {
        return Math.abs(((long) cities[i][0]) - cities[j][0]) + Math.abs(((long) cities[i][1]) - cities[j][1]);
    }
}
