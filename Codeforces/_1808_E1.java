import java.util.*;
import java.io.*;

public class _1808_E1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());

        int[][] nck = new int[n + 1][n + 1];
        nck[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i >= j) {
                    if (j == 0) {
                        nck[i][j] = 1;
                    } else {
                        nck[i][j] = (nck[i - 1][j - 1] + nck[i - 1][j]) % m;
                    }
                }
            }
        }

        // dp[a][b][i][j] = # i digit numbers without a and b such that the digit sum mod k is j
        long[][][][] dp = new long[k][k][n][k];
        for (int a = 0; a < k; a++) {
            for (int b = 0; b < k; b++) {
                dp[a][b][0][0] = 1;
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < k; j++) {
                        for (int l = 0; l < k; l++) {
                            if (l != a && l != b) {
                                dp[a][b][i + 1][(j + l) % k] = (dp[a][b][i + 1][(j + l) % k] + dp[a][b][i][j]) % m;
                            }
                        }
                    }
                }
            }
        }

        long res = 0;
        if (k % 2 == 1) {
            // Counting number of lucky tickets with the digit being i
            for (int i = 0; i < k; i++) {
                // Count tickets with exactly j i's
                for (int j = 1; j <= n; j++) {
                    int target = (i - (j - 1) * i) % k; 
                    if (target < 0) {
                        target += k;
                    }
                    res = (res + dp[i][i][n - j][target] * nck[n][j]) % m;
                }
            }
        } else {
            // Counting number of lucky tickets with digits i or i + k / 2
            for (int i = 0; i < k / 2; i++) {
                // Count tickets with exactly a i's and b (i + k / 2)'s
                for (int a = 0; a <= n; a++) {
                    for (int b = 0; b <= n - a; b++) {
                        if (a == 0 && b == 0) {
                            continue;
                        }
                        int target = (i - (a - 1) * i - b * (i + k / 2)) % k;
                        if (target < 0) {
                            target += k;
                        }
                        res = (res + dp[i][i + k / 2][n - a - b][target] * nck[n][a + b] % m * nck[a + b][a]) % m; 
                    }
                }
            }
        }
        out.println(res);

        in.close();
        out.close();
    }
}
