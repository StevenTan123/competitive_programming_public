import java.util.*;
import java.io.*;

public class palpath {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("palpath.in"));
        PrintWriter out = new PrintWriter("palpath.out");
        int n = Integer.parseInt(in.readLine());
        int[][] grid = new int[n][n];
        for(int i = 0; i < n; i++) {
            String line = in.readLine();
            for(int j = 0; j < n; j++) {
                char c = line.charAt(j);
                grid[i][j] = (int)c - 65;
            }
        }
        ArrayList<Integer>[] diags = new ArrayList[2 * n - 1];
        for(int i = 0; i < 2 * n - 1; i++) {
            diags[i] = new ArrayList<Integer>();
            int r = Math.min(i, n - 1);
            int c = Math.max(0, i - n + 1);
            while(r >= 0 && c < n) {
                diags[i].add(grid[r][c]);
                r--;
                c++;
            }
        }
        long[][] dp = new long[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for(int i = 1; i < n; i++) {
            ArrayList<Integer> d1 = diags[n - i - 1];
            ArrayList<Integer> d2 = diags[n + i - 1];
            long[][] dp2 = new long[d1.size()][d2.size()];
            for(int j = 0; j < d1.size(); j++) {
                for(int k = 0; k < d2.size(); k++) {
                    if(d1.get(j) == d2.get(k)) {
                        dp2[j][k] += dp[j][k];
                        dp2[j][k] %= MOD;
                        dp2[j][k] += dp[j + 1][k];
                        dp2[j][k] %= MOD;
                        dp2[j][k] += dp[j][k + 1];
                        dp2[j][k] %= MOD;
                        dp2[j][k] += dp[j + 1][k + 1];
                        dp2[j][k] %= MOD;
                    }
                }
            }
            dp = dp2;
        }
        out.println(dp[0][0]);
        in.close();
        out.close();
    }
}
