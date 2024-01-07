import java.util.*;
import java.io.*;

public class _248 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("248.in"));
        PrintWriter out = new PrintWriter("248.out");
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(in.readLine());
        }
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        for(int i = 0; i < n; i++) dp[i][i] = a[i];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = 0; j < n; j++) {
                if(i >= j) continue;
                for(int k = i; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] == dp[k + 1][j] ? dp[i][k] + 1 : -1);
                }
            }
        }
        int res = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                res = Math.max(res, dp[i][j]);
            }
        }
        out.println(res);
        in.close();
        out.close();
    }

}
