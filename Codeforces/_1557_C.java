import java.util.*;
import java.io.*;

public class _1557_C {
    static final int MOD = 1000000007;
    static final long[] fact = new long[200005];
    static final long[] inv = new long[200005];
    static final long[] pow = new long[200005];
    public static void main(String[] args) throws IOException {
        fact[0] = 1;
        pow[0] = 1;
        for(int i = 1; i < 200005; i++) {
            fact[i] = modmult(fact[i - 1], i);
            inv[i] = modinv(fact[i]);
            pow[i] = modmult(pow[i - 1], 2);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            //binom[i] stores n choose i
            long[] binom = new long[n + 1];
            binom[0] = 1;
            long num = 1;
            long sum = 0;
            for(int i = 0; i < n; i++) {
                num = modmult(num, n - i);
                binom[i + 1] = modmult(num, inv[i + 1]);
                if(i % 2 == 0) {
                    sum = modadd(sum, binom[i]);
                }
            }
            //dp[i] stores number of winning arrays with k = i
            long[] dp = new long[k + 1];
            dp[0] = 1;
            for(int i = 1; i <= k; i++) {
                if(n % 2 == 0) {
                    dp[i] = modadd(dp[i], binpow(pow[i - 1], n));
                }else {
                    dp[i] = modadd(dp[i], dp[i - 1]);
                }
                dp[i] = modadd(dp[i], modmult(sum, dp[i - 1]));
            }
            out.println(dp[k]);
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
