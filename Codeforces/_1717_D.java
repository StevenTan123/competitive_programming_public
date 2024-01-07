import java.util.*;
import java.io.*;

public class _1717_D {
    static final long MOD = 1000000007;
    static long[] fact = new long[100005];
    static long[] inv = new long[100005];
    public static void main(String[] args) throws IOException {
        fact[0] = 1;
        inv[0] = 1;
        for(int i = 1; i < 100005; i++) {
            fact[i] = modmult(fact[i - 1], i);
            inv[i] = modmult(inv[i - 1], modinv(i));
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        long res = 1;
        for(int i = 1; i <= Math.min(n, k); i++) {
            res = modadd(res, choose(n, i));
        }
        out.println(res);
        in.close();
        out.close();
    }

    static long choose(int n, int k) {
        return modmult(modmult(fact[n], inv[n - k]), inv[k]);
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
        if (b == 0) {
            return 1;
        }
        long small = binpow(a, b / 2);
        if (b % 2 == 0) {
            return modmult(small, small);
        } else {
            return modmult(modmult(small, small), a);
        }
    }
}
