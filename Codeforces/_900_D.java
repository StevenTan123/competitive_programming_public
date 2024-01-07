import java.util.*;
import java.io.*;

public class _900_D {
    static long MOD = 1000000007;
    static HashMap<Integer, Long> dp = new HashMap<Integer, Long>();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(line.nextToken());
        int y = Integer.parseInt(line.nextToken());
        if(y % x != 0) {
            out.println(0);
        }else {
            y /= x;
            long res = calc_num(y);
            if(y == 1) {
                res = 1;
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static long calc_num(int y) {
        if(dp.containsKey(y)) {
            return dp.get(y);
        }
        long res = binpow(2, y - 1);
        for(int i = 2; (long)i * i <= y; i++) {
            if(y % i == 0) {
                res = modadd(res, -calc_num(i));
                if(i != y / i) {
                    res = modadd(res, -calc_num(y / i));
                }
            }
        }
        dp.put(y, res - 1);
        return res - 1;
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
