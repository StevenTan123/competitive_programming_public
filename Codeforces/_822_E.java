import java.util.*;
import java.io.*;

public class _822_E {
    static final long MOD = 1000000007;
    static final long P = 31;
    static long[] pow = new long[100005];
    static long[] pow_inv = new long[100005];
    public static void main(String[] args) throws IOException {
        pow[0] = 1;
        pow_inv[0] = 1;
        for(int i = 1; i < 100005; i++) {
            pow[i] = modmult(pow[i - 1], P);
            pow_inv[i] = modmult(pow_inv[i - 1], modinv(P));
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        String s = in.readLine();
        int m = Integer.parseInt(in.readLine());
        String t = in.readLine();
        int x = Integer.parseInt(in.readLine());
        long[] hashs = new long[n];
        long[] hasht = new long[m];
        for(int i = 0; i < n; i++) {
            int val = (int)s.charAt(i) - 97;
            hashs[i] = modadd(i > 0 ? hashs[i - 1] : 0, modmult(pow[i], val));
        }
        for(int i = 0; i < m; i++) {
            int val = (int)t.charAt(i) - 97;
            hasht[i] = modadd(i > 0 ? hasht[i - 1] : 0, modmult(pow[i], val));
        }
        int[][] dp = new int[n + 1][x + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < x; j++) {
                if(dp[i][j] == m) {
                    continue;
                }
                dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
                int lcp = lcp(hashs, hasht, i, dp[i][j]);
                if(lcp > -1) {
                    dp[i + lcp + 1][j + 1] = Math.max(dp[i + lcp + 1][j + 1], dp[i][j] + lcp + 1);
                }
            }
        }
        boolean possible = false;
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= x; j++) {
                if(dp[i][j] == m) {
                    possible = true;
                }
            }
        }
        out.println(possible ? "YES" : "NO");
        in.close();
        out.close();
    }
    static int lcp(long[] hashs, long[] hasht, int i, int j) {
        int l = 0;
        int r = Math.min(hashs.length - i - 1, hasht.length - j - 1);
        int res = -1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(equal(hashs, hasht, i, i + m, j, j + m)) {
                res = m;
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        return res;
    }
    static boolean equal(long[] hashs, long[] hasht, int l1, int r1, int l2, int r2) {
        if(hash(hashs, l1, r1) == hash(hasht, l2, r2)) {
            return true;
        }
        return false;
    }
    static long hash(long[] hash, int l, int r) {
        return modmult(modadd(hash[r], -(l > 0 ? hash[l - 1] : 0)), pow_inv[l]);
    }
    static long modadd(long a, long b) {
        long val = a + b;
        if(val >= MOD) {
            val -= MOD;
        }else if(val < 0) {
            val += MOD;
        }
        return val;
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
