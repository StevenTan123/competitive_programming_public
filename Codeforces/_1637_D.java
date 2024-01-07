import java.util.*;
import java.io.*;

public class _1637_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            int[] b = new int[n];
            StringTokenizer line1 = new StringTokenizer(in.readLine());
            StringTokenizer line2 = new StringTokenizer(in.readLine());
            long square_sum = 0;
            long sum = 0;
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line1.nextToken());
                b[i] = Integer.parseInt(line2.nextToken());
                sum += a[i] + b[i];
                square_sum += a[i] * a[i];
                square_sum += b[i] * b[i];
            }
            int[][] dp = new int[n][10005];
            dp[0][a[0]] = 1;
            dp[0][b[0]] = 1;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < 10005; j++) {
                    if(dp[i][j] == 1 && i < n - 1) {
                        dp[i + 1][j + a[i + 1]] = 1;
                        dp[i + 1][j + b[i + 1]] = 1;
                    }
                }
            }
            long min = Long.MAX_VALUE;
            for(int i = 0; i < 10005; i++) {
                if(dp[n - 1][i] == 1) {
                    long cur_val = (long)i * i + (long)(sum - i) * (sum - i);
                    min = Math.min(min, cur_val);
                }
            }
            out.println(min + square_sum * (n - 2));
        }
        in.close();
        out.close();
    }
}
