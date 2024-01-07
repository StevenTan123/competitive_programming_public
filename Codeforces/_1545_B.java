import java.util.*;
import java.io.*;

public class _1545_B {
    static final long MOD = 998244353;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String board = in.readLine();
            int pairs = 0;
            int zeros = 0;
            if(board.charAt(0) == '0') {
                zeros++;
            }
            for(int i = 1; i < n; i++) {
                char prev = board.charAt(i - 1);
                char cur = board.charAt(i);
                if(cur == '1' && prev == '1') {
                    if(i < n - 1 && board.charAt(i + 1) == '0') {
                        zeros++;
                    } 
                    pairs++;
                    i++;
                }else if(cur == '0') {
                    zeros++;
                }
            }
            out.println(binom(pairs + zeros, zeros));
        }
        in.close();
        out.close();
    }
    static long binom(int n, int k) {
        long res = 1;
        for(int i = n; i >= n - k + 1; i--) {
            res = modmult(res, i);
        }
        for(int i = 2; i <= k; i++) {
            res = modmult(res, modinv(i));
        }
        return res;
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
