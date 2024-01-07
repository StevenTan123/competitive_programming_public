import java.util.*;
import java.io.*;

public class _1458_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] glasses = new int[n][2];
        int total = 0;
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            glasses[i][0] = Integer.parseInt(line.nextToken());
            glasses[i][1] = Integer.parseInt(line.nextToken());
            total += glasses[i][1];
        }
        int[][] dp = new int[n + 1][10005];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;
        dp[1][glasses[0][0]] = glasses[0][1];
        for(int i = 1; i < n; i++) {
            int[][] dp2 = new int[n + 1][10005];
            Arrays.fill(dp2[0], -1);
            dp2[0][0] = 0;
            for(int j = 1; j <= n; j++) {
                for(int k = 0; k < 10005; k++) {
                    dp2[j][k] = -1;
                    if(dp[j][k] != -1) {
                        dp2[j][k] = dp[j][k];
                    }
                    int prev_cap = k - glasses[i][0];
                    if(prev_cap >= 0 && dp[j - 1][prev_cap] != -1) {
                        dp2[j][k] = Math.max(dp2[j][k], dp[j - 1][prev_cap] + glasses[i][1]);
                    }
                }
            }
            dp = dp2;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            double max = 0;
            for(int j = 0; j < 10005; j++) {
                int start = dp[i][j];
                if(start > -1) {
                    double collected = Math.min((start + (double)(total - start) / 2), j);
                    max = Math.max(max, collected);
                }
            }
            sb.append(max);
            sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
}
