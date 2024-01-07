import java.util.*;
import java.io.*;

public class _1606_E {
    static long MOD = 998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int x = Integer.parseInt(line.nextToken());
        long[][] pow = new long[n + 1][n + 1];
        long[][] binom = new long[n + 1][n + 1];
        for(int i = 0; i <= n; i++) {
            long prod = 1;
            long num = 1;
            long denom = 1;
            for(int j = 0; j <= n; j++) {
                pow[i][j] = prod;
                binom[i][j] = modmult(num, modinv(denom));
                prod = modmult(prod, i);
                num = modmult(num, i - j);
                denom = modmult(denom, j + 1);
            }
        }
        long[][] dp = new long[n + 1][x + 1];
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= x; j++) {
                dp[i][j] = pow[Math.min(j, i - 1)][i];
                for(int a = 2; a <= i; a++) {
                    long prod = modmult(binom[i][a], pow[i - 1][i - a]);
                    long prev = (j - i + 1 > 0) ? dp[a][j - i + 1] : 0;
                    dp[i][j] = modadd(dp[i][j], modmult(prev, prod));
                }
            }
        }
        out.println(dp[n][x]);
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
