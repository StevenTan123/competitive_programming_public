import java.util.*;
import java.io.*;

public class _1542_D {
    static long MOD = 998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            if(line.nextToken().equals("-")) {
                a[i] = -1;
            }else {
                a[i] = Integer.parseInt(line.nextToken());
            }
        }
        long res = 0;
        for(int i = 0; i < n; i++) {
            if(a[i] != -1) {
                res = modadd(res, modmult(count(a, n, i), a[i]));
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static long count(int[] a, int n, int ind) {
        //dp[i][j] = # subsequences using values 0...i with j values in the multiset below a[ind]
        long[][] dp = new long[n][n];
        if(ind == 0) {
            dp[0][0] = 1;
        }else {
            if(a[0] == -1) {
                dp[0][0]++;
            }else if(a[0] <= a[ind]) {
                dp[0][1]++;
            }else {
                dp[0][0]++;
            }
            dp[0][0]++;
        }
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == ind) {
                    dp[i][j] = modadd(dp[i][j], dp[i - 1][j]);
                }else {
                    if(a[i] == -1) {
                        if(j < n - 1) dp[i][j] = modadd(dp[i][j], dp[i - 1][j + 1]);
                        if(j == 0 && i < ind) dp[i][j] = modadd(dp[i][j], dp[i - 1][0]);
                    }else if((i < ind && a[i] <= a[ind]) || (i > ind && a[i] < a[ind])) {
                        if(j > 0) dp[i][j] = modadd(dp[i][j], dp[i - 1][j - 1]);
                    }else {
                        dp[i][j] = modadd(dp[i][j], dp[i - 1][j]);
                    }
                    dp[i][j] = modadd(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        long res = 0;
        for(int i = 0; i < n; i++) {
            res = modadd(res, dp[n - 1][i]);
        }
        return res;
    }
    static long modadd(long a, long b) {
        if(a + b >= MOD) {
            return a + b - MOD;
        }
        return a + b;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
}
