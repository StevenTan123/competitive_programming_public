import java.util.*;
import java.io.*;

public class MOD {
    static final int MAXN = 200005;
    static long MOD = 1000000007;
    static long[] fact, inv_fact;
    public static void main(String[] args) throws IOException {
        fact = new long[MAXN];
        inv_fact = new long[MAXN];
        fact[0] = 1;
        inv_fact[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            fact[i] = fact[i - 1] * i % MOD;
            inv_fact[i] = inv_fact[i - 1] * modinv(i) % MOD;
        }
    }    
    static void precomp_binom(int n, int k) {
        int[][] nck = new int[n + 1][k + 1];
        nck[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (i >= j) {
                    if (j == 0) {
                        nck[i][j] = 1;
                    } else {
                        nck[i][j] = (int) ((nck[i - 1][j - 1] + nck[i - 1][j]) % MOD);
                    }
                }
            }
        }
    }
    static long binom(int n, int k) {
        if (k > n) {
            return 0;
        }
        return fact[n] * inv_fact[k] % MOD * inv_fact[n - k] % MOD;
    }
    static long modinv(long a) {
        return binpow(a, MOD - 2);
    }
    static long binpow(long a, long b) {
        if(b == 0) {
            return 1;
        }
        long c = binpow(a, b / 2);
        if(b % 2 == 0) {
            return c * c % MOD;
        }else {
            return c * c % MOD * a % MOD;
        }
    }
}
