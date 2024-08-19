import java.util.*;
import java.io.*;

public class good_sequences {
    static final long MOD = 998244353;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int k = Integer.parseInt(in.readLine());

        // dp[i][j][l] stores the number of sequences of length i that sum to j. l tells
        // if we have an element k yet.
        long[][][] dp = new long[k + 1][2 * k + 1][2];
        dp[0][0][0] = 1;

        // sum[i][j][k] stores the sum of dp[i][0][k] to dp[i][j][k]
        long[][][] sum = new long[k + 1][2 * k + 1][2];
        for (int j = 0; j <= 2 * k; j++) {
            sum[0][j][0] = 1;
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= 2 * k; j++) {
                if (j < 2 * k) {
                    dp[i][j][0] += sum[i - 1][j][0] - (j - k >= 0 ? sum[i - 1][j - k][0] : 0);
                    dp[i][j][0] %= MOD;

                    dp[i][j][1] += j - k >= 0 ? dp[i - 1][j - k][0] : 0;
                    dp[i][j][1] += sum[i - 1][j][1] - (j - k - 1 >= 0 ? sum[i - 1][j - k - 1][1] : 0);
                    dp[i][j][1] %= MOD;
                } else {
                    for (int l = j - k + 1; l <= j; l++) {
                        int options = k - (j - l);
                        dp[i][j][0] += dp[i - 1][l][0] * options;
                        dp[i][j][0] %= MOD;
                    }

                    for (int l = j - k; l <= j; l++) {
                        dp[i][j][1] += dp[i - 1][l][0];

                        int options = k + 1 - (j - l);
                        dp[i][j][1] += dp[i - 1][l][1] * options;
                        dp[i][j][1] %= MOD;
                    }
                }
                sum[i][j][0] = ((j > 0 ? sum[i][j - 1][0] : 0) + dp[i][j][0]) % MOD;
                sum[i][j][1] = ((j > 0 ? sum[i][j - 1][1] : 0) + dp[i][j][1]) % MOD;
            }
        }
        out.println((dp[k][2 * k][1] + MOD) % MOD);

        in.close();
        out.close();
    }
}
