import java.util.*;
import java.io.*;

public class _1644_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int x = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            int[] psum = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                psum[i] = (i > 0 ? psum[i - 1] : 0) + a[i];
            }
            int[] max_sum = new int[n + 1];
            Arrays.fill(max_sum, Integer.MIN_VALUE);
            max_sum[0] = 0;
            for(int i = 1; i <= n; i++) {
                for(int j = 0; j <= n - i; j++) {
                    max_sum[i] = Math.max(max_sum[i], sum(psum, j, j + i - 1));
                }
            }
            int[] suf_max = new int[n + 1];
            for(int i = n; i >= 0; i--) {
                suf_max[i] = Math.max(i < n ? suf_max[i + 1] : Integer.MIN_VALUE, max_sum[i]);
            }
            StringBuilder res = new StringBuilder();
            long max = Long.MIN_VALUE;
            for(int i = 0; i <= n; i++) {
                long max_after = suf_max[i] + (long)x * i;
                res.append(Math.max(max, max_after));
                res.append(' ');
                max = Math.max(max, max_sum[i] + (long)x * i);
            } 
            out.println(res.toString());
        }
        in.close();
        out.close();
    }
    static int sum(int[] psum, int l, int r) {
        return psum[r] - (l > 0 ? psum[l - 1] : 0);
    }
}
