import java.util.*;
import java.io.*;

public class _1517_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[][] edges1 = new int[n][m - 1];
        int[][] edges2 = new int[n - 1][m];
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            for(int j = 0; j < m - 1; j++) {
                edges1[i][j] = Integer.parseInt(line.nextToken());
            }
        }
        for(int i = 0; i < n - 1; i++) {
            line = new StringTokenizer(in.readLine());
            for(int j = 0; j < m; j++) {
                edges2[i][j] = Integer.parseInt(line.nextToken());
            }
        }
        if(k % 2 == 1) {
            for(int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    sb.append(-1);
                    if(j < m - 1) sb.append(' ');
                }
                out.println(sb.toString());
            }
        }else {
            int[][][] dp = new int[n][m][k + 1];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    Arrays.fill(dp[i][j], Integer.MAX_VALUE);
                    dp[i][j][0] = 0;
                }
            }
            for(int a = 2; a <= k; a += 2) {
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
                        if(i > 0) {
                            dp[i][j][a] = Math.min(dp[i][j][a], dp[i - 1][j][a - 2] + 2 * edges2[i - 1][j]);
                        }
                        if(i < n - 1) {
                            dp[i][j][a] = Math.min(dp[i][j][a], dp[i + 1][j][a - 2] + 2 * edges2[i][j]);
                        }
                        if(j > 0) {
                            dp[i][j][a] = Math.min(dp[i][j][a], dp[i][j - 1][a - 2] + 2 * edges1[i][j - 1]);
                        }
                        if(j < m - 1) {
                            dp[i][j][a] = Math.min(dp[i][j][a], dp[i][j + 1][a - 2] + 2 * edges1[i][j]);
                        }
                    }
                }
            }
            for(int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < m; j++) {
                    sb.append(dp[i][j][k]);
                    if(j < m - 1) sb.append(' ');
                }
                out.println(sb.toString());
            }
        }
        in.close();
        out.close();
    }
}
