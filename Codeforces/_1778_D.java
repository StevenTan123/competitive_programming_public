import java.util.*;
import java.io.*;

public class _1778_D {
    static long MOD = 998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int count = 0;
            String a = in.readLine();
            String b = in.readLine();
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    count++;
                }
            }

            // e[i] stores the expected number of moves to match the string given there are currently i mismatches
            long[] e = new long[n + 1];
            long[] coeffs = new long[n + 1];
            long[] adds = new long[n + 1];
            coeffs[n] = 1;
            adds[n] = 1;
            for (int i = n - 1; i > 0; i--) {
                long den = modinv(modadd(n, -modmult(n - i, coeffs[i + 1])));
                coeffs[i] = modmult(i, den);
                adds[i] = modmult(modadd(modmult(n - i, adds[i + 1]), n), den);
            }
            e[0] = 0;
            for (int i = 1; i <= n; i++) {
                e[i] = modadd(modmult(coeffs[i], e[i - 1]), adds[i]);
            }
            out.println(e[count]);
        }
        in.close();
        out.close();
    }
    
    static long modadd(long a, long b) {
        long ret = a + b;
        if (ret >= MOD) {
            ret -= MOD;
        }
        if (ret < 0) {
            ret += MOD;
        }
        return ret;
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
