import java.util.*;
import java.io.*;

public class sprinklers2 {
    static long MOD = 1000000007;
    static long[] pow2 = new long[2005];
    public static void main(String[] args) throws IOException {
        pow2[0] = 1;
        for(int i = 1; i < 2005; i++) {
            pow2[i] = modmult(pow2[i - 1], 2);
        }
        BufferedReader in = new BufferedReader(new FileReader("sprinklers2.in"));
        PrintWriter out = new PrintWriter("sprinklers2.out");
        int n = Integer.parseInt(in.readLine());
        int[][] grid = new int[n][n];
        int[][] rsum = new int[n][n];
        for(int i = 0; i < n; i++) {
            String line = in.readLine();
            for(int j = 0; j < n; j++) {
                if(line.charAt(j) == 'W') {
                    grid[i][j] = 1;
                }
                rsum[i][j] = grid[i][j] + (j > 0 ? rsum[i][j - 1] : 0);
            }
        }
        int[][] csum = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                csum[i][j] = grid[j][i] + (j > 0 ? csum[i][j - 1] : 0);
            }
        }
        long[][][] dp = new long[n + 1][n + 1][2];
        for(int i = 1; i <= n; i++) {
            dp[0][i][0] = 1;
            dp[i][0][1] = 1;
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                int cnum = csum[j - 1][i - 1];
                dp[i][j][0] = modadd(dp[i][j][0], modmult(dp[i][j - 1][0], pow2[i - cnum]));
                if(grid[i - 1][j - 1] == 0) {
                    dp[i][j][0] = modadd(dp[i][j][0], modmult(dp[i][j - 1][1], pow2[i - cnum - 1]));
                }
                int rnum = rsum[i - 1][j - 1];
                dp[i][j][1] = modadd(dp[i][j][1], modmult(dp[i - 1][j][1], pow2[j - rnum]));
                if(grid[i - 1][j - 1] == 0) {
                    dp[i][j][1] = modadd(dp[i][j][1], modmult(dp[i - 1][j][0], pow2[j - rnum - 1]));
                }
            }
        }
        out.println(modadd(dp[n][n][0], dp[n][n][1]));
        in.close();
        out.close();
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
}
