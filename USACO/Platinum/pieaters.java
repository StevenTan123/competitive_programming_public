import java.util.*;
import java.io.*;

public class pieaters {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("pieaters.in"));
        PrintWriter out = new PrintWriter("pieaters.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[][] cows = new int[m][3];
        int[][] dp = new int[n][n];
        int[][][] max = new int[n][n][n];
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            cows[i][0] = Integer.parseInt(line.nextToken());
            cows[i][1] = Integer.parseInt(line.nextToken()) - 1;
            cows[i][2] = Integer.parseInt(line.nextToken()) - 1;
            for(int j = cows[i][1]; j <= cows[i][2]; j++) {
                max[j][cows[i][1]][cows[i][2]] = Math.max(max[j][cows[i][1]][cows[i][2]], cows[i][0]);
            }
            dp[cows[i][1]][cows[i][2]] = Math.max(dp[cows[i][1]][cows[i][2]], cows[i][0]);
        }
        for(int i = 0; i < n; i++) {
            for(int j = n - 1; j >= 0; j--) {
                for(int k = 0; k < n; k++) {
                    if(j < n - 1) {
                        max[i][j][k] = Math.max(max[i][j][k], max[i][j + 1][k]);
                    }
                    if(k > 0) {
                        max[i][j][k] = Math.max(max[i][j][k], max[i][j][k - 1]);
                    }
                }
            }
        }
        for(int i = n - 1; i >= 0; i--) {
            for(int j = 0; j < n; j++) {
                for(int k = i; k <= j; k++) {
                    if(k < j) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                    int left = k > i ? dp[i][k - 1] : 0;
                    int right = k < j ? dp[k + 1][j] : 0;
                    dp[i][j] = Math.max(dp[i][j], left + max[k][i][j] + right);
                }
            }
        }
        out.println(dp[0][n - 1]);
        in.close();
        out.close();
    }
}
