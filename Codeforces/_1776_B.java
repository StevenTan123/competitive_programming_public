import java.util.*;
import java.io.*;

public class _1776_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int h = Integer.parseInt(line.nextToken());
        line = new StringTokenizer(in.readLine());
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(line.nextToken());
        }
        int max_width = 2 * h;

        long[][] dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = h;
            for (int j = i + 1; j < n; j++) {
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    if (x[j] - x[i] <= max_width) {
                        int save = h - ceil(x[j] - x[i], 2) + 1;
                        long val1 = dp[i][k] + dp[k + 1][j] - save;
                        dp[i][j] = Math.min(dp[i][j], val1);
                    }
                }
            }
        }
        out.println(dp[0][n - 1]);

        in.close();
        out.close();
    }
    static int ceil(int a, int b) {
        int ret = a / b;
        if (a % b != 0) {
            ret++;
        }
        return ret;
    }
}