import java.util.*;
import java.io.*;

public class _1197_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        long[][] dp = new long[n][m];
        int val = 1 % m;
        for(int i = 0; i < n; i++)  {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
            dp[i][0] = 0;
            dp[i][val] = a[i] - k;
        }
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < m; j++) {
                if(dp[i][j] > Integer.MIN_VALUE) {
                    int j2 = (j + 1) % m;
                    long next = dp[i][j] + a[i + 1];
                    if(j == 0) {
                        next -= k;
                    }
                    dp[i + 1][j2] = Math.max(dp[i + 1][j2], next);
                }
            }
        }
        long res = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                res = Math.max(res, dp[i][j]);
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
}
