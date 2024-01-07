import java.util.*;
import java.io.*;

public class balanced_subsets {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] a = new int[n][n];
        int[][] rsum = new int[n][n];
        for(int i = 0; i < n; i++) {
            String line = in.readLine();
            for(int j = 0; j < n; j++) {
                if(line.charAt(j) == 'G') {
                    a[i][j] = 1;
                }
                rsum[i][j] = (j > 0 ? rsum[i][j - 1] : 0) + a[i][j]; 
            }
        }
        long[][][][][] dp = new long[n][2][2][n][n];
        long[][][][][] psum = new long[n][2][2][n][n];
        long res = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    if(k >= j && rsum[i][k] - (j > 0 ? rsum[i][j - 1] : 0) == k - j + 1) {
                        if(i > 0) {
                            dp[i][0][0][j][k] = sum(psum[i - 1][0][0], j, k, j, k);

                            dp[i][0][1][j][k] = modadd(sum(psum[i - 1][0][0], j, k, k + 1, n - 1),
                                                       sum(psum[i - 1][0][1], j, k, k, n - 1));

                            dp[i][1][0][j][k] = modadd(sum(psum[i - 1][0][0], 0, j - 1, j, k),
                                                       sum(psum[i - 1][1][0], 0, j, j, k));

                            dp[i][1][1][j][k] = modadd(modadd(sum(psum[i - 1][0][0], 0, j - 1, k + 1, n - 1),
                                                              sum(psum[i - 1][0][1], 0, j - 1, k, n - 1)),
                                                       modadd(sum(psum[i - 1][1][0], 0, j, k + 1, n - 1),
                                                              sum(psum[i - 1][1][1], 0, j, k, n - 1)));
                        }
                        dp[i][0][0][j][k] = modadd(dp[i][0][0][j][k], 1);

                        long add = modadd(modadd(dp[i][0][0][j][k], dp[i][0][1][j][k]), 
                                          modadd(dp[i][1][0][j][k], dp[i][1][1][j][k]));
                        res = modadd(res, add);
                    }
                    psum[i][0][0][j][k] = add_psum(psum[i][0][0], j, k, dp[i][0][0][j][k]);
                    psum[i][0][1][j][k] = add_psum(psum[i][0][1], j, k, dp[i][0][1][j][k]);
                    psum[i][1][0][j][k] = add_psum(psum[i][1][0], j, k, dp[i][1][0][j][k]);
                    psum[i][1][1][j][k] = add_psum(psum[i][1][1], j, k, dp[i][1][1][j][k]);
                }
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static long add_psum(long[][] psum, int r, int c, long add) {
        long left = c > 0 ? psum[r][c - 1] : 0;
        long above = r > 0 ? psum[r - 1][c] : 0;
        long diag = r > 0 && c > 0 ? psum[r - 1][c - 1] : 0;
        return modadd(add, modadd(modadd(left, above), -diag));
    }
    static long sum(long[][] psum, int r1, int r2, int c1, int c2) {
        if(r1 > r2 || c1 > c2) {
            return 0;
        }
        long left = c1 > 0 ? psum[r2][c1 - 1] : 0;
        long above = r1 > 0 ? psum[r1 - 1][c2] : 0;
        long diag = r1 > 0 && c1 > 0 ? psum[r1 - 1][c1 - 1] : 0;
        long res = modadd(modadd(left, above), -diag);
        return modadd(psum[r2][c2], -res);
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
}
