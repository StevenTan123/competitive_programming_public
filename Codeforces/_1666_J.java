import java.util.*;
import java.io.*;

public class _1666_J {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int n = Integer.parseInt(in.readLine());
        int[][] c = new int[n][n];
        long[][] psums = new long[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                c[i][j] = Integer.parseInt(line.nextToken());
                psums[i][j] = c[i][j] + (j > 0 ? psums[i][j - 1] : 0) + (i > 0 ? psums[i - 1][j] : 0) - (i > 0 && j > 0 ? psums[i - 1][j - 1] : 0);
            }
        }

        long[][] dp = new long[n][n];
        int[][] roots = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            roots[i][i] = i;
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Long.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    long left = 0;
                    if (k > i) {
                        left = dp[i][k - 1];
                        left += sum_subrectangle(psums, i, k - 1, 0, n - 1) - sum_subrectangle(psums, i, k - 1, i, k - 1);
                    }
                    long right = 0;
                    if (k < j) {
                        right = dp[k + 1][j];
                        right += sum_subrectangle(psums, k + 1, j, 0, n - 1) - sum_subrectangle(psums, k + 1, j, k + 1, j);
                    }
                    if (left + right < dp[i][j]) {
                        dp[i][j] = left + right;
                        roots[i][j] = k;
                    }
                }
            }
        }

        int[] par = new int[n];
        dfs(roots, 0, n - 1, -1, par);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(par[i] + 1);
            sb.append(' ');
        }
        out.println(sb.toString());
        
        in.close();
        out.close();
    }

    static void dfs(int[][] roots, int l, int r, int prev, int[] par) {
        if (l > r) {
            return;
        }
        par[roots[l][r]] = prev;
        dfs(roots, l, roots[l][r] - 1, roots[l][r], par);
        dfs(roots, roots[l][r] + 1, r, roots[l][r], par);
    }

    static long sum_subrectangle(long[][] psums, int r1, int r2, int c1, int c2) {
        return psums[r2][c2] - (r1 > 0 ? psums[r1 - 1][c2] : 0) - (c1 > 0 ? psums[r2][c1 - 1] : 0) + (r1 > 0 && c1 > 0 ? psums[r1 - 1][c1 - 1] : 0);
    }
}
