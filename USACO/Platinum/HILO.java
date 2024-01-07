import java.util.*;
import java.io.*;

public class HILO {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int x = Integer.parseInt(line.nextToken());
        long[][][] dp = new long[x + 1][n - x + 1][2];
        for(int i = 0; i <= x; i++) {
            for(int j = 0; j <= n - x; j++) {
                for(int k = 0; k < i; k++) {
                    dp[i][j][0] = modadd(dp[i][j][0], dp[k][j][0]);
                    dp[i][j][1] = modadd(dp[i][j][1], dp[k][j][0] + 1);
                }
                for(int k = 0; k < j; k++) {
                    dp[i][j][0] = modadd(dp[i][j][0], dp[i][k][1]);
                    dp[i][j][1] = modadd(dp[i][j][1], dp[i][k][1]);
                }
                dp[i][j][0] = modmult(dp[i][j][0], modinv(i + j));
                dp[i][j][1] = modmult(dp[i][j][1], modinv(i + j));
            }
        }
        long nfact = 1;
        for(int i = 2; i <= n; i++) {
            nfact = modmult(nfact, i);
        }
        out.println(modmult(dp[x][n - x][0], nfact));
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
