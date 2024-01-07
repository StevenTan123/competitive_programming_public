import java.util.*;
import java.io.*;

public class _360_B {
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        n = Integer.parseInt(line.nextToken());
        k = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        long l = 0;
        long r = 2000000000;
        long res = -1;
        while(l <= r) {
            long m = (l + r) / 2;
            if(ok(a, m)) {
                res = m;
                r = m - 1;
            }else {
                l = m + 1;
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static boolean ok(int[] a, long gap) {
        int[] dp = new int[n];
        for(int i = 1; i < n; i++) {
            dp[i] = i;
            for(int j = 0; j < i; j++) {
                int ceil = Math.abs(a[i] - a[j]) / (i - j);
                if(Math.abs(a[i] - a[j]) % (i - j) != 0) {
                    ceil++;
                }
                if(ceil <= gap) {
                    dp[i] = Math.min(dp[i], dp[j] + i - j - 1);
                }
            }
        }
        int res = n - 1;
        for(int i = 0; i < n; i++) {
            res = Math.min(res, dp[i] + n - i - 1);
        }
        return res <= k;
    }
}
