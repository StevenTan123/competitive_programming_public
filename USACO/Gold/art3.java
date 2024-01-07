import java.util.*;
import java.io.*;

public class art3 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        StringTokenizer line = new StringTokenizer(in.readLine());
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken()) - 1;
        }
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i < j) dp[i][j] = j - i + 1;
                else if(i == j) dp[i][j] = 1;
                else dp[i][j] = 0;
            }
        }
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                if(a[i] == a[j]) dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1] + 1);
                for(int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] - 1);
                }
            }
        }
        out.println(dp[0][dp[0].length - 1]);
        in.close();
        out.close();
    }
}
