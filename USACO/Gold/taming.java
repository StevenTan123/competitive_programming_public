import java.util.*;
import java.io.*;

public class taming {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("taming.in"));
        PrintWriter out = new PrintWriter("taming.out");
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) a[i] = Integer.parseInt(line.nextToken());
        int[][][] dp = new int[n + 1][n + 1][n];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) Arrays.fill(dp[i][j], Integer.MAX_VALUE);
        }
        dp[1][1][0] = a[0] == 0 ? 0 : 1;
        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= n; j++) {
                if(j > i) continue;
                for(int k = 0; k < n; k++) {
                    if(k > i - 1 || k + 1 < j) continue;
                    if(dp[i][j][k] != Integer.MAX_VALUE) {
                        dp[i + 1][j][k] = Math.min(dp[i + 1][j][k], dp[i][j][k] + (a[i] == i - k ? 0 : 1));
                        if(j < n) dp[i + 1][j + 1][i] = Math.min(dp[i + 1][j + 1][i], dp[i][j][k] + (a[i] == 0 ? 0 : 1));
                    }
                }
            }
        }
        for(int j = 1; j <= n; j++) {
            int res = Integer.MAX_VALUE;
            for(int k = 0; k < n; k++) {
                res = Math.min(res, dp[n][j][k]);
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
