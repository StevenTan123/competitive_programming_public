import java.util.*;
import java.io.*;

public class _1861_E {
    static long MOD = 998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());

        long[] powk = new long[n + 1];
        powk[0] = 1;
        for (int i = 1; i <= n; i++) {
            powk[i] = powk[i - 1] * k % MOD;
        }

        // dp[i][j] = # arrays 0...i with previous j elements unique
        long[][] dp = new long[n][k + 1];
        long[][] dp_sums = new long[n][k + 1];
        dp[0][1] = k;

        long res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i > 0) {
                    dp[i][j] += dp_sums[i - 1][j];
                    dp[i][j] %= MOD;
                }
                if (i < n - 1) {
                    if (j < k) {
                        dp[i + 1][j + 1] += (k - j) * dp[i][j];
                        dp[i + 1][j + 1] %= MOD;
                    } else {
                        dp[i + 1][1] += k * dp[i][j];
                        dp[i + 1][1] %= MOD;
                    }
                }
                if (k == j) {
                    res += dp[i][j] * powk[n - i - 1];
                    res %= MOD;
                }
            }
            dp_sums[i][k - 1] = dp[i][k - 1];
            for (int j = k - 2; j >= 0; j--) {
                dp_sums[i][j] += dp_sums[i][j + 1] + dp[i][j];
                dp_sums[i][j] %= MOD;
            }
        }
        out.println(res);

        in.close();
        out.close();
    }
}
