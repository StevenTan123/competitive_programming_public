import java.util.*;
import java.io.*;

public class moo_route {
    static final long MOD = 1000000007;
    static long[] fact = new long[2000001];
    static long[] inv_fact = new long[2000001];
    public static void main(String[] args) throws IOException {
        fact[0] = 1;
        inv_fact[0] = 1;
        for (int i = 1; i < fact.length; i++) {
            fact[i] = fact[i - 1] * i % MOD;
            inv_fact[i] = inv_fact[i - 1] * modinv(i) % MOD;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int N = Integer.parseInt(in.readLine());
        int[] A = new int[N];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(line.nextToken()) / 2;
        }
        long res = 1;
        for (int i = 1; i < N; i++) {
            if (A[i] <= A[i - 1]) {
                res = res * nck(A[i - 1], A[i]) % MOD;
            } else {
                int x = A[i] - A[i - 1];
                int y = A[i - 1];
                long combos = nck(x + y - 1, y - 1);
                res = res * combos % MOD;
            }
        }
        out.println(res);
        in.close();
        out.close();
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
            return small * small % MOD;
        } else {
            return small * small % MOD * a % MOD;
        }
    }
    static long nck(int n, int k) {
        return fact[n] * inv_fact[k] % MOD * inv_fact[n - k] % MOD;
    }
}
