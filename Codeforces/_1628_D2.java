import java.util.*;
import java.io.*;

public class _1628_D2 {
    static final long MOD = 1000000007;
    static long[] inv_pow = new long[1000005];
    static long[] fact = new long[1000005];
    static long[] inv_fact = new long[1000005];
    public static void main(String[] args) throws IOException {
        inv_pow[0] = 1;
        fact[0] = 1;
        inv_fact[0] = 1;
        for(int i = 1; i < 1000005; i++) {
            inv_pow[i] = modmult(inv_pow[i - 1], modinv(2));
            fact[i] = modmult(fact[i - 1], i);
            inv_fact[i] = modmult(inv_fact[i - 1], modinv(i));
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            long res = 0;
            for(int i = 1; i <= m; i++) {
                long val = modmult(binom(n - i - 1, m - i), inv_pow[n - i]);
                long prod = modmult(i, k);
                res = modadd(res, modmult(val, prod));
            }
            if(n == m) {
                res = modmult(n, k);
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static long binom(int n, int k) {
        if(n < 0 || k < 0 || n < k) {
            return 0;
        }
        return modmult(modmult(fact[n], inv_fact[n - k]), inv_fact[k]);
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
