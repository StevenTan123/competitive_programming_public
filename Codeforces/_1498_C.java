import java.util.*;
import java.io.*;

public class _1498_C {
    static int mod = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[][][] dp = new int[n + 1][k + 1][2];
            for(int i = 1; i <= k; i++) {
                dp[0][i][0] = 1;
                dp[n][i][1] = 1;
            }
            for(int j = 1; j <= k; j++) {
                for(int i = 1; i <= n; i++) {
                    dp[i][j][0] = (dp[i - 1][j][0] + dp[i][j - 1][1]) % mod;
                    int i2 = n - i;
                    dp[i2][j][1] = (dp[i2 + 1][j][1] + dp[i2][j - 1][0]) % mod;
                }
            }
            out.println(dp[0][k][1]);
        }
        in.close();
        out.close();
    }
}
