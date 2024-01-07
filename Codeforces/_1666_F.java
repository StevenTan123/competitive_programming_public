import java.util.*;
import java.io.*;

public class _1666_F {
    static final int MAXN = 5005;
    static long[][] nck = new long[MAXN][MAXN];
    static final long MOD = 998244353;

    public static void main(String[] args) throws IOException {    
        nck[0][0] = 1;
        for (int i = 1; i < MAXN; i++) {
            for (int j = 0; j < MAXN; j++) {
                if (i >= j) {
                    if (j == 0) {
                        nck[i][j] = 1;
                    } else {
                        nck[i][j] = (nck[i - 1][j - 1] + nck[i - 1][j]) % MOD;
                    }
                }
            }
        }
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }

            // dp[i][j] = # ways to build stack using i...n - 1 blocks, with j of them in even positions
            long[][] dp = new long[n + 1][n / 2 + 1];
            dp[n][0] = 1;
            int prev = n;
            for (int i = n - 1; i >= 0; i--) {
                if (i == 0 || a[i] > a[i - 1]) {
                    int k = prev - i;
                    for (int j = 0; j <= Math.min(n - i, n / 2); j++) {
                        int open = j - 1 - (n - prev - j);
                        if (j == n / 2) {
                            open++;
                        }
                        if (open > 0 && open >= k) {
                            dp[i][j] += dp[prev][j] * nck[open][k];
                            dp[i][j] %= MOD;
                        }

                        if (j - 1 >= 0) {
                            open = Math.max(j - 2 - (n - prev - (j - 1)), 0);
                            if (open >= k - 1) { 
                                dp[i][j] += dp[prev][j - 1] * nck[open][k - 1];
                                dp[i][j] %= MOD;
                            }
                        }
                    }
                    prev = i;
                }
            }
            out.println(dp[0][n / 2]);
        }
        
        in.close();
        out.close();
    }
}
