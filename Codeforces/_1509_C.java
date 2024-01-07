import java.util.*;
import java.io.*;

public class _1509_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        Arrays.sort(a);
        long[][] dp = new long[n + 1][n + 1];
        for(int i = 0; i <= n; i++) Arrays.fill(dp[i], Long.MAX_VALUE);
        dp[0][0] = 0;
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(i == 0 && j == 0 || i + j > n) continue;
                if(i > 0) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a[n - j - 1] - a[i - 1]);
                if(j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + a[n - j] - a[i]);
            }
        }
        long res = Long.MAX_VALUE;
        for(int i = 0; i <= n; i++) {
            res = Math.min(res, dp[i][n - i]);
        }
        out.println(res);
        in.close();
        out.close();
    }
}
