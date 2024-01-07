import java.util.*;
import java.io.*;

public class _1657_E {
    static final long MOD = 998244353;
    static long[][] pow = new long[255][255];
    static long[] inv = new long[255];
    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 255; i++) {
            long prod = 1;
            for(int j = 0; j < 255; j++) {
                pow[i][j] = prod;
                prod = modmult(prod, i);
            }
            if(i > 0) {
                inv[i] = modinv(i);
            }
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        long[][][] dp = new long[n][k + 1][n];
        long[][] psum = new long[n][k + 1];
        long res = 0;
        for(int i = 1; i <= k; i++) {
            dp[1][i][1] = 1;
            psum[1][i] = modadd(psum[1][i - 1], 1);
            if(1 == n - 1) {
                res = modadd(res, 1);
            } 
        }
        for(int i = 2; i < n; i++) {
            for(int j = 1; j <= k; j++) {
                psum[i][j] = psum[i][j - 1];
                for(int a = 1; a <= i; a++) {
                    if(a > 1) {
                        long mult = modmult(modmult(i, inv[a]), pow[k - j + 1][i - 1]);
                        dp[i][j][a] = modmult(dp[i - 1][j][a - 1], mult);
                    }else {
                        long mult = modmult(i, pow[k - j + 1][i - 1]);
                        dp[i][j][a] = modmult(psum[i - 1][j - 1], mult);
                    }
                    psum[i][j] = modadd(psum[i][j], dp[i][j][a]);
                    if(i == n - 1) {
                        res = modadd(res, dp[i][j][a]);
                    }
                }
            }
        }
        out.println(res);
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
