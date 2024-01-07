import java.util.*;
import java.io.*;

public class _1623_D {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[] b = new int[]{Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken())};
            int[] d = new int[]{Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken())};
            long p = modmult(Integer.parseInt(line.nextToken()), modinv(100));
            long comp = modadd(1, -p);
            int[] rmeet = new int[] {d[0] - b[0], n - d[0] + n - b[0]};
            int[] cmeet = new int[] {d[1] - b[1], m - d[1] + m - b[1]};
            if(rmeet[0] < 0) {
                rmeet[0] += 2 * (n - 1);
            }
            if(cmeet[0] < 0) {
                cmeet[0] += 2 * (m - 1);
            }
            int period = 2 * (n - 1) * (m - 1);
            TreeSet<Integer> meet = new TreeSet<Integer>();
            for(int i = 0; i < m - 1; i++) {
                int val1 = rmeet[0] + i * 2 * (n - 1);
                int val2 = rmeet[1] + i * 2 * (n - 1);
                if(val1 < period) meet.add(val1);
                if(val2 < period) meet.add(val2);   
            }
            for(int i = 0; i < n - 1; i++) {
                int val1 = cmeet[0] + i * 2 * (m - 1);
                int val2 = cmeet[1] + i * 2 * (m - 1);
                if(val1 < period) meet.add(val1);
                if(val2 < period) meet.add(val2);
            }
            long res = 0;
            int size = meet.size();
            int count = 0;
            for(int time : meet) {
                long pow = binpow(comp, size);
                long num = modmult(modmult(period, p), pow);
                long den = modadd(1, -pow);
                long val = modadd(modmult(time, p), modmult(num, modinv(den)));
                val = modmult(val, modinv(den));
                val = modmult(val, binpow(comp, count));
                res = modadd(res, val);
                count++;
            }
            out.println(res);
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
