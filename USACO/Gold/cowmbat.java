import java.util.*;
import java.io.*;

public class cowmbat {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cowmbat.in"));
        PrintWriter out = new PrintWriter("cowmbat.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[] combo = new int[n];
        String line2 = in.readLine();
        for(int i = 0; i < n; i++) combo[i] = (int)(line2.charAt(i)) - 97;
        int[][] matrix = new int[m][m];
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            for(int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(line.nextToken());
            }
        }
        floyd_warshall(matrix, m);
        //stores cost of changing first j of the combo into letter i
        int[][] pref = new int[m][n];
        for(int i = 0; i < m; i++) {
            int cost = 0;
            for(int j = 0; j < n; j++) {
                cost += matrix[combo[j]][i];
                pref[i][j] = cost;
            }
        }
        //dp[i][j] min cost to change first i into a combo ending with letter j
        int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        for(int i = 0; i < m; i++) {
            dp[k - 1][i] = pref[i][k - 1];
        }
        for(int i = k; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int a = 0; a < m; a++) {
                    int prev = dp[i - k][a];
                    if(prev == Integer.MAX_VALUE) continue;
                    dp[i][j] = Math.min(dp[i][j], prev + pref[j][i] - pref[j][i - k]);
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + matrix[combo[i]][j]);
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) res = Math.min(res, dp[n - 1][i]);
        out.println(res);
        in.close();
        out.close();
    }
    static void floyd_warshall(int[][] matrix, int m) {
        for(int k = 0; k < m; ++k) {
            for(int i = 0; i < m; ++i) {
                for(int j = 0; j < m; ++j) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
    }
}
