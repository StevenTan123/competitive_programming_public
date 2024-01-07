import java.util.*;
import java.io.*;

public class _1746_D {
    static long MOD;
    static long[] fact;
    static long[] inv_fact;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        MOD = Integer.parseInt(line.nextToken());

        fact = new long[n + 1];
        inv_fact = new long[n + 1];
        fact[0] = 1;
        inv_fact[0] = 1;
        for(int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
            inv_fact[i] = modmult(inv_fact[i - 1], modinv(i));
        }
        
        long count = 0;
        long[][] seen = new long[n][n];
        int half = n / 2;
        for(int i = 0; i < half; i++) {
            for(int j = 0; j < half; j++) {
                int gap = i + j;
                if(n % 2 == 1) {
                    gap++;
                }
                if( (n % 2 == 0 && gap < half) || (n % 2 == 1 && gap <= half) ) {
                    int x = n - gap - 2;
                    int y = Math.max(gap - 1, 0);
                    long cur = 0;
                    if(seen[x + y][x] != 0) {
                        cur = seen[x + y][x];
                    }else {
                        for(int k = x; k <= x + y; k++) {
                            cur += modmult(fact[k], choose(y, k - x));
                        }
                        seen[x + y][x] = cur;
                    }
                    count += cur;
                    count %= MOD;
                }
            }
        }
        out.println(count * n % MOD);

        in.close();
        out.close();
    }

    static long choose(int n, int k) {
        return( modmult(modmult(fact[n], inv_fact[k]), inv_fact[n - k]) );
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
