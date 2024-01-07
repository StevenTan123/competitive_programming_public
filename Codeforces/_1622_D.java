import java.util.*;
import java.io.*;

public class _1622_D {
    static long MOD = 998244353;
    static long[] fact = new long[5005];
    static long[] inv_fact = new long[5005];
    public static void main(String[] args) throws IOException {
        long cur1 = 1;
        long cur2 = 1;
        for(int i = 0; i < 5005; i++) {
            fact[i] = cur1;
            inv_fact[i] = cur2;
            cur1 = modmult(cur1, i + 1);
            cur2 = modmult(cur2, modinv(i + 1));
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        String s = in.readLine();
        int total = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '1') {
                total++;
            }
        }
        long res = 1;
        if(k > 0) res = modadd(res, calc(n, k, s, k, total));
        if(k > 1) res = modadd(res, -calc(n, k, s, k - 1, total));
        out.println(res);
        in.close();
        out.close();
    }  
    static long calc(int n, int k, String s, int w, int total) {
        long res = 0;
        int r = 0;
        int count = 0;
        int ones = 0;
        for(int i = 0; i < n; i++) {
            if(i == 0 || s.charAt(i - 1) == '1') {
                if(i > 0) {
                    count--;
                    ones++;
                }
                while(r < n) {
                    if(s.charAt(r) == '1') {
                        count++;
                        if(count > w) {
                            count--;
                            break;
                        }
                    }
                    r++;
                }
                if(ones >= k - w && count == w && total - (ones + w) >= k - w) {
                    res = modadd(res, binom(r - i, w) - 1);
                }
            }
        }
        return res;
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
