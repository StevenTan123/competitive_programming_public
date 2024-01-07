import java.util.*;
import java.io.*;

public class _1628_D1 {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        long modinv2 = modinv(2);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            //dp[i][j] = score of a game with i turns and j additions
            long[][] dp = new long[n + 1][m + 1];
            for(int i = 1; i <= n; i++) {
                if(i <= m) {
                    dp[i][i] = modmult(k, i);
                }
                for(int j = 1; j < Math.min(m + 1, i); j++) {
                    dp[i][j] = modmult(modadd(dp[i - 1][j - 1], dp[i - 1][j]), modinv2);
                }
            }
            out.println(dp[n][m]);
        }
        in.close();
        out.close();
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
    static long modinv(long a) {
        return binpow(a, MOD - 2);
    }
    static long binpow(long a, long b) {
        if(b == 0) {
            return 1;
        }
        long small = binpow(a, b / 2);
        if(b % 2 == 0) {
            return modmult(small, small);
        }else {
            return modmult(modmult(small, small), a);
        }
    }
}
