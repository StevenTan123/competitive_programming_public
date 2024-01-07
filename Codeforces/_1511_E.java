import java.util.*;
import java.io.*;

public class _1511_E {
    static int count;
    static int MAX = 300005;
    static long MOD = 998244353;
    static long[] pow = new long[MAX];
    static long[] pre = new long[MAX];
    public static void main(String[] args) throws IOException {
        pow[0] = 1;
        pre[0] = 1;
        for(int i = 1; i < MAX; i++) {
            pow[i] = modmult(pow[i - 1], 2);
            pre[i] = modadd(i > 1 ? pre[i - 2] : 0, pow[i]);
            if(i == 1) {
                pre[i]++;
            }
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[][] a = new int[n][m];
        int[][] ta = new int[m][n];
        count = n * m;
        for(int i = 0; i < n; i++) {
            String line2 = in.readLine();
            for(int j = 0; j < m; j++) {
                if(line2.charAt(j) == '*') {
                    a[i][j] = 1;
                    ta[j][i] = 1;
                    count -= 1;
                }
            }
        }
        long res = modadd(count(a), count(ta));
        out.println(res);
        in.close();
        out.close();
    }
    static long count(int[][] a) {
        long res = 0;
        for(int i = 0; i < a.length; i++) {
            int prev = 0;
            for(int j = 0; j < a[i].length; j++) {
                if(a[i][j] == 1) {
                    prev = j + 1;
                }else if(j < a[i].length - 1 && a[i][j + 1] == 0) {
                    int gap = j - prev - 1;
                    long ways = 1;
                    if(gap >= 0) {
                        ways = pre[gap];
                    }
                    res = modadd(res, modmult(ways, pow[count - (gap + 3)]));
                }
            }
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
