import java.util.*;
import java.io.*;

public class _1142_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        line = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(line.nextToken());
        int b = Integer.parseInt(line.nextToken());
        long min = Long.MAX_VALUE;
        long max = 0;
        long total = (long)n * k;
        for(int i = 0; i <= n; i++) {
            long l1 = (long)i * k + b - a;
            long l2 = (long)(i + 1) * k - b - a;
            if(l1 > 0) {
                long stops = stops(l1, total);
                min = Math.min(min, stops);
                max = Math.max(max, stops);
            }
            if(l2 > 0) {
                long stops = stops(l2, total);
                min = Math.min(min, stops);
                max = Math.max(max, stops);
            }
        }
        out.println(min + " " + max);
        in.close();
        out.close();
    }
    static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    static long stops(long l, long total) {
        return total / gcd(total, l);
    }
}
