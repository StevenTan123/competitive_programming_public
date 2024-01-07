import java.util.*;
import java.io.*;

public class _1579_G {
    static int MAXLEN = 2005;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            int[][] dp = new int[n][MAXLEN];
            for(int i = 0; i < n; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            dp[0][0] = a[0];
            dp[0][a[0]] = a[0];
            for(int i = 0; i < n - 1; i++) {
                for(int j = 0; j < MAXLEN; j++) {
                    if(dp[i][j] != Integer.MAX_VALUE) {
                        if(a[i + 1] >= j) {
                            dp[i + 1][0] = Math.min(dp[i + 1][0], dp[i][j] + a[i + 1] - j);
                        }else {
                            dp[i + 1][j - a[i + 1]] = Math.min(dp[i + 1][j - a[i + 1]], dp[i][j]);
                        }
                        if(a[i + 1] + j >= dp[i][j]) {
                            if(a[i + 1] + j < MAXLEN) {
                                dp[i + 1][a[i + 1] + j] = Math.min(dp[i + 1][a[i + 1] + j], a[i + 1] + j);
                            }
                        }else {
                            dp[i + 1][a[i + 1] + j] = Math.min(dp[i + 1][a[i + 1] + j], dp[i][j]);
                        }
                    }
                }
            }
            int res = Integer.MAX_VALUE;
            for(int i = 0; i < MAXLEN; i++) {
                res = Math.min(res, dp[n - 1][i]);
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
