import java.util.*;
import java.io.*;

public class _1000_D {
    static final int MOD = 998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        long[][] dp = new long[n][n];
        for(int i = 0; i < n - 1; i++) {
            if(a[i] < n && a[i] > 0) {
                dp[i][a[i]]++;
                dp[i][a[i]] %= MOD;
            }
            if(a[i + 1] < n && a[i + 1] > 0) {
                dp[i + 1][a[i + 1]] += dp[i][0];
                dp[i + 1][a[i + 1]] %= MOD;
            }
            for(int j = 0; j < n; j++) {
                if(j > 0) {
                    dp[i + 1][j - 1] += dp[i][j];
                    dp[i + 1][j - 1] %= MOD;
                }
                dp[i + 1][j] += dp[i][j];
                dp[i + 1][j] %= MOD;
            }
        }
        out.println(dp[n - 1][0]);
        in.close();
        out.close();
    }
}