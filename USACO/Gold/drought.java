import java.util.*;
import java.io.*;

public class drought {
    static long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        StringTokenizer line = new StringTokenizer(in.readLine());
        int[] h = new int[n];
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(line.nextToken());
            min = Math.min(min, h[i]);
        }
        if(n == 1) {
            out.println(h[0] + 1);
        }else {
            long[] res = new long[min + 1];
            long ans = 0;
            for(int start = min; start >= 0; start--) {
                long[][] dp = new long[n][1001];
                long[][] sum = new long[n][1001];
                for(int i = 0; i <= 1000; i++) {
                    if(i <= Math.min(h[0] - start, h[1] - start)) {
                        dp[1][i] = 1;
                    }
                    sum[1][i] = (i > 0 ? sum[1][i - 1] : 0) + dp[1][i];
                }
                for(int i = 2; i < n; i++) {
                    for(int j = 0; j <= 1000; j++) {
                        if(j <= h[i] - start) {
                            int ind = h[i - 1] - start - j;
                            if(ind >= 0) {
                                dp[i][j] = modadd(dp[i][j], sum[i - 1][ind]);
                            }
                        }
                        sum[i][j] = modadd(j > 0 ? sum[i][j - 1] : 0, dp[i][j]);
                    }
                }
                res[start] = sum[n - 1][h[n - 1] - start];
                if(n % 2 == 0 && start < min) {
                    res[start] = modadd(res[start], -ans);
                }
                ans = modadd(ans, res[start]);
            }
            out.println(ans);
        }
        in.close();
        out.close();
    }
    static long modadd(long a, long b) {
        long sum = a + b;
        if(sum >= MOD) {
            sum -= MOD;
        }else if(sum < 0) {
            sum += MOD;
        }
        return sum;
    }
}
