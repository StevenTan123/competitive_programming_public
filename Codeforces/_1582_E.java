import java.util.*;
import java.io.*;

public class _1582_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            long[] psum = new long[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                psum[i] = a[i] + (i > 0 ? psum[i - 1] : 0);
            }
            //dp[i][j] = max last segment sum using indices i..(n - 1) and j segments
            long[][] dp = new long[n][500];
            for(int i = n - 1; i >= 0; i--) {
                Arrays.fill(dp[i], -1);
                dp[i][1] = Math.max(a[i], i < n - 1 ? dp[i + 1][1] : 0);
            }
            for(int i = n - 2; i >= 0; i--) {
                for(int j = 2; j < 500; j++) {
                    if(i + j < n && sum(i, i + j - 1, psum) < dp[i + j][j - 1]) {
                        dp[i][j] = sum(i, i + j - 1, psum);
                    }
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                }
            }
            int res = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 1; j < 500; j++) {
                    if(dp[i][j] != -1) {
                        res = Math.max(res, j);
                    }
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static long sum(int l, int r, long[] psum) {
        return psum[r] - (l > 0 ? psum[l - 1] : 0);
    }
}
