import java.util.*;
import java.io.*;

public class _1525_E {
    static long MOD = 998244353;
    static long[] fact = new long[25];
    static long[] inv_fact = new long[25];
    public static void main(String[] args) throws IOException {
        fact[0] = 1;
        inv_fact[0] = 1;
        for(int i = 1; i < 25; i++) {
            fact[i] = modmult(fact[i - 1], i);
            inv_fact[i] = modmult(inv_fact[i - 1], modinv(i));
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[][] d = new int[m][n];
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            for(int j = 0; j < m; j++) {
                d[j][i] = Integer.parseInt(line.nextToken());
            }
        }
        long res = 0;
        for(int i = 0; i < m; i++) {
            res = modadd(res, calc_prob(d[i]));
        }
        out.println(res);
        in.close();
        out.close();
    }
    static long calc_prob(int[] dists) {
        long ways = 1;
        Arrays.sort(dists);
        int filled = 0;
        for(int i = 0; i < dists.length; i++) {
            int left = dists[i] - 1 - filled;
            if(left <= 0) {
                ways = 0;
                break;
            }
            ways = modmult(ways, left);
            filled++;
        }
        return modadd(1, -modmult(ways, inv_fact[dists.length]));
    }
    static long binom(int n, int k) {
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
