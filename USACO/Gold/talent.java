import java.util.*;
import java.io.*;

public class talent {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("talent.in"));
        PrintWriter out = new PrintWriter("talent.out");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int w = Integer.parseInt(line.nextToken());
        int[][] cows = new int[n][2];
        for (int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            cows[i][0] = Integer.parseInt(line.nextToken());
            cows[i][1] = Integer.parseInt(line.nextToken());
        }
        int[][] dp = new int[n][n * 1000 + 1];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[0][cows[0][1]] = cows[0][0];
        for (int i = 0; i < n - 1; i++) {
            dp[i + 1][cows[i + 1][1]] = cows[i + 1][0];
            for (int j = 0; j < dp[i].length; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                int newtalent = j + cows[i + 1][1];
                if (newtalent >= dp[i].length || dp[i][j] == Integer.MAX_VALUE)
                    continue;
                dp[i + 1][newtalent] = Math.min(dp[i + 1][newtalent], dp[i][j] + cows[i + 1][0]);
            }
        }
        double maxratio = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i][j] != Integer.MAX_VALUE && dp[i][j] >= w) {
                    maxratio = Math.max(maxratio, (double) j / dp[i][j]);
                }
            }
        }
        out.println((long) (maxratio * 1000));
        in.close();
        out.close();
    }
}
