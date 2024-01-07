import java.util.*;
import java.io.*;

public class _466_D {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int h = Integer.parseInt(line.nextToken());
        int[] a = new int[n + 2];
        line = new StringTokenizer(in.readLine());
        for(int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        a[0] = h;
        a[n + 1] = h;
        long[] dp = new long[n + 2];
        dp[0] = 1;
        for(int i = 1; i <= n + 1; i++) {
            if(a[i] > h) {
                break;
            }
            if(a[i] == a[i - 1]) {
                dp[i] = modadd(dp[i - 1], modmult(dp[i - 1], h - a[i]));
            }else if (a[i] == a[i - 1] + 1) {
                dp[i] = modmult(dp[i - 1], h - a[i - 1]);
            }else if (a[i] == a[i - 1] - 1) {
                dp[i] = dp[i - 1];
            }else {
                break;
            }
        }
        out.println(dp[n + 1]);
        in.close();
        out.close();
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
}
