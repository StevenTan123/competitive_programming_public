import java.util.*;
import java.io.*;

public class _1168_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        int l = 0;
        int r = 1000000000;
        int res = 0;
        while(l <= r) {
            int avg = (l + r) / 2;
            if(ok(a, n, m, avg)) {
                res = avg;
                r = avg - 1;
            }else {
                l = avg + 1;
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static boolean ok(int[] a, int n, int m, int max) {
        int prev = 0;
        for(int i = 0; i < n; i++) {
            if(a[i] >= prev) {
                int wrap = m - a[i] + prev;
                if(wrap > max) {
                    prev = a[i];
                }
            }else {
                if(prev - a[i] > max) {
                    return false;
                }
            }
        }
        return true;
    }
}
