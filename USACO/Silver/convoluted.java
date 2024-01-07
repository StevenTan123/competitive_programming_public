import java.util.*;
import java.io.*;

public class convoluted {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[] starts = new int[m + 1];
        int[] ends = new int[m + 1];
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            starts[Integer.parseInt(line.nextToken())]++;
            ends[Integer.parseInt(line.nextToken())]++;
        }
        long[] start_ways = new long[2 * m + 1];
        long[] end_ways = new long[2 * m + 1];
        count_ways(n, m, starts, start_ways);
        count_ways(n, m, ends, end_ways);
        long ways = 0;
        for(int i = 0; i <= 2 * m; i++) {
            ways += start_ways[i];
            if(i > 0) {
                ways -= end_ways[i - 1];
            }
            out.println(ways);
        }
        in.close();
        out.close();
    }
    static void count_ways(int n, int m, int[] a, long[] ways) {
        for(int i = 0; i <= 2 * m; i++) {
            for(int j = 0; j <= i; j++) {
                if(j <= m && i - j <= m) {
                    ways[i] += (long)a[j] * a[i - j];
                }
            }
        }
    }
}
