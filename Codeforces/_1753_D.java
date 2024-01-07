import java.util.*;
import java.io.*;

public class _1753_D {
    static final long MOD = 998244353;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            int t1 = 0;
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                t1 += a[i];
            }
            int b = 0;
            for (int i = n - t1; i < n; i++) {
                if (a[i] == 0) {
                    b++;
                }
            }
            long NC2 = modmult(modmult(n, n - 1), modinv(2));
            // e[i] stores the expected number of swaps with i bad pairs
            long[] e = new long[n + 1];
            e[1] = NC2;
            for (int i = 2; i <= n; i++) {
                e[i] = modadd(e[i - 1], modmult(NC2, modinv(modmult(i, i))));
            }
            out.println(e[b]);
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
