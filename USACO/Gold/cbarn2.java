import java.util.*;
import java.io.*;

public class cbarn2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cbarn2.in"));
        PrintWriter out = new PrintWriter("cbarn2.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        long[] r = new long[n];
        for(int i = 0; i < n; i++) r[i] = Integer.parseInt(in.readLine());
        long res = Long.MAX_VALUE;
        res = Math.min(res, dp(r, n, k));
        for(int i = 0; i < n - 1; i++) {
            r = shift(r, n);
            res = Math.min(res, dp(r, n, k));
        }
        out.println(res);
        in.close();
        out.close();
    }
    static long dp(long[] r, int n, int k) {
        //dp[i][j][k] = min distance travelled after assigning first i rooms,
        //using j openings, previous opening at index k
        long[][][] dp = new long[n + 1][k + 1][n];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= k; j++) Arrays.fill(dp[i][j], Long.MAX_VALUE);
        }
        dp[1][1][0] = 0;
        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= k; j++) {
                for(int a = 0; a < n; a++) {
                    if(a > i - 1 || j > i) continue;
                    if(dp[i][j][a] != Long.MAX_VALUE) {
                        dp[i + 1][j][a] = Math.min(dp[i + 1][j][a], dp[i][j][a] + (i - a) * r[i]);
                        if(j < k) {
                            dp[i + 1][j + 1][i] = Math.min(dp[i + 1][j + 1][i], dp[i][j][a]);
                        }
                    }
                }
            }
        }
        long res = Long.MAX_VALUE;
        for(int a = 0; a < n; a++) {
            res = Math.min(res, dp[n][k][a]);
        }
        return res;
    }
    static long[] shift(long[] r, int n) {
        long[] r2 = new long[n];
        for(int i = 0; i < n; i++) {
            r2[(i + 1) % n] = r[i];
        }
        return r2;
    }
}
