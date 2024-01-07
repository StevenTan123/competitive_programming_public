import java.util.*;
import java.io.*;

public class _1761_D {
    static final long MOD = 1000000007;
    static long[] fact, wtf, inv_fact, pow3;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());

        fact = new long[n + 5];
        wtf = new long[n + 5];
        inv_fact = new long[n + 5];
        fact[0] = fact[1] = inv_fact[0] = inv_fact[1] = wtf[1] = 1;
        for (int i = 2; i < n + 5; i++) {
            wtf[i] = modmult(wtf[(int)(i - MOD % i)], (MOD / i + 1) % MOD);
            fact[i] = modmult(fact[i - 1], i);
            inv_fact[i] = modmult(inv_fact[i - 1], wtf[i]);
        }

        pow3 = new long[n + 5];
        pow3[0] = 1;
        for (int i = 1; i < n + 5; i++) {
            pow3[i] = modmult(pow3[i - 1], 3);
        }

        long res = 0;
        int ceil = n / 2;
        if (n % 2 == 1) {
            ceil++;
        }
        for (int i = 1; i <= Math.min(ceil, k); i++) {
            if (i * 2 <= n) {
                res = modadd(res, modmult(pow3[n - 2 * i], modmult(nck(k - 1, i - 1), nck(n - k, i))));
            }
            res = modadd(res, modmult(pow3[n - 2 * i + 1], modmult(nck(k - 1, i - 1), nck(n - k, i - 1))));
        }

        if (k == 0) {
            out.println(pow3[n]);
        } else {
            out.println(res);
        }

        in.close();
        out.close();
    }

    static long nck(int n, int k) {
        if (n < 0 || k < 0 || n < k) {
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