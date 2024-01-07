import java.util.*;
import java.io.*;

public class _1860_D {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String s = in.readLine();
        short n = (short) s.length();
        short n_sq = (short) (n * n);

        // dp[i][j][k] = min changes to 1...i so that there are j 0's from 1..i
        // k 01 subsequences from 1...i
        short[][][] dp = new short[n + 1][n + 1][n_sq + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], (short) 101);
            }
        }
        dp[0][0][0] = 0;
        short num0 = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                num0++;
            }
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n_sq; k++) {
                    if (dp[i][j][k] == 101) {
                        continue;
                    }
                    if (s.charAt(i) == '0') {
                        dp[i + 1][j + 1][k] = (short) Math.min(dp[i + 1][j + 1][k], dp[i][j][k]);
                        dp[i + 1][j][k + j] = (short) Math.min(dp[i + 1][j][k + j], dp[i][j][k] + 1);
                    } else {
                        dp[i + 1][j][k + j] = (short) Math.min(dp[i + 1][j][k + j], dp[i][j][k]);
                        dp[i + 1][j + 1][k] = (short) Math.min(dp[i + 1][j + 1][k], dp[i][j][k] + 1);
                    }
                }
            }
        }
        short balanced = (short) (num0 * (n - num0) / 2);
        out.println(dp[n][num0][balanced] / 2);

        in.close();
        out.close();
    }
}
