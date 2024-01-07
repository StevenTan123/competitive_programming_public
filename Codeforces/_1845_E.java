import java.util.*;
import java.io.*;

public class _1845_E {
    static final int MOD = 1000000007;
    static int sqrt = 40;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        int m = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            m += a[i];
        }

        int[] pos = new int[m];
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 1) {
                pos[p] = i;
                p++;
            }
        }

        int[][] ranges = new int[m][2];
        for (int i = 0; i < m; i++) {
            ranges[i][0] = i >= sqrt ? pos[i - sqrt] : 0;
            ranges[i][1] = i + sqrt < m ? pos[i + sqrt] : n - 1;
        }

        int[][] dp = new int[k + 1][n];
        for (int j = 0; j <= k; j++) {
            for (int l = ranges[0][0]; l <= ranges[0][1]; l++) {
                if (Math.abs(l - pos[0]) == j) {
                    dp[j][l] = 1;
                }
                if (l > 0) {
                    dp[j][l] = (dp[j][l] + dp[j][l - 1]) % MOD;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = k; j >= 0; j--) {
                for (int l = ranges[i][1]; l >= ranges[i][0]; l--) {
                    dp[j][l] = 0;
                    
                    int dist = Math.abs(l - pos[i]);
                    int ind = Math.min(l - 1, ranges[i - 1][1]);
                    if (ind >= ranges[i - 1][0] && j - dist >= 0) {
                        dp[j][l] = (dp[j][l] + dp[j - dist][ind]) % MOD;
                    }
                }
            }
            for (int j = 0; j <= k; j++) {
                for (int l = ranges[i][0] + 1; l <= ranges[i][1]; l++) {
                    dp[j][l] = (dp[j][l] + dp[j][l - 1]) % MOD;
                }
            }
        }
        
        int res = 0;
        for (int i = k; i >= 0; i -= 2) {
            res = (res + dp[i][ranges[m - 1][1]]) % MOD;
        }
        out.println(res);

        in.close();
        out.close();
    }
}
