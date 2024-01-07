import java.util.*;
import java.io.*;

public class _1515_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int M = Integer.parseInt(line.nextToken());
    
        int[][] nck = new int[n + 1][n + 1];
        nck[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i >= j) {
                    if (j == 0) {
                        nck[i][j] = 1;
                    } else {
                        nck[i][j] = (int) ((nck[i - 1][j - 1] + nck[i - 1][j]) % M);
                    }
                }
            }
        }

        // ways[i] = # ways to turn on i consecutive lights, all manually
        int[] ways = new int[n + 1];
        ways[1] = 1;
        for (int i = 2; i <= n; i++) {
            ways[i] = (ways[i - 1] * 2) % M;
        }

        // dp[i][j] = # ways to turn on lights 1...i using j moves
        long[][] dp = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = ways[i];
            for (int j = 1; j <= i; j++) {
                for (int k = i + 2; k <= n; k++) {
                    dp[k][j + k - i - 1] += dp[i][j] * ways[k - i - 1] % M * nck[j + k - i - 1][j];
                    dp[k][j + k - i - 1] %= M;
                }
            }
        }

        long res = 0;
        for (int i = 0; i <= n; i++) {
            res += dp[n][i];
            res %= M;
        }
        out.println(res);

        in.close();
        out.close();
    }
}
