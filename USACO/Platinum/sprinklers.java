import java.util.*;
import java.io.*;

public class sprinklers {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sprinklers.in"));
        PrintWriter out = new PrintWriter("sprinklers.out");
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        int[] space = new int[n];
        Arrays.fill(space, n - 1);
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(line.nextToken());
            a[x] = Integer.parseInt(line.nextToken());
            space[a[x]] = Math.min(space[a[x]], x);
        }
        for(int i = 0; i < n; i++) {
            space[i] = Math.min(space[i], (i > 0 ? space[i - 1] : n - 1));
        }
        long[] psum = new long[n];
        long[] psum_mult = new long[n];
        for(int i = 0; i < n; i++) {
            psum[i] = modadd(i > 0 ? psum[i - 1] : 0, space[i]);
            psum_mult[i] = modadd(i > 0 ? psum_mult[i - 1] : 0, modmult(space[i], n - 1 - i));
        }
        int[][] bounds = new int[n][2];
        int min = n - 1;
        for(int i = 0; i < n; i++) {
            min = Math.min(min, a[i]);
            bounds[i][0] = min;
        }
        int max = 0;
        for(int i = n - 1; i >= 0; i--) {
            max = Math.max(max, a[i]);
            bounds[i][1] = max;
        }
        long total = 0;
        long sub = 0;
        for(int i = 0; i < n; i++) {
            int l = bounds[i][0];
            int u = bounds[i][1];
            total = modadd(total, modmult(choose2(u + 1), i));
            sub = modadd(sub, modadd(modmult(modmult(l, u - l + 1), i), modmult(choose2(l), i)));
            long all = sum(psum_mult, l, u);
            long over = modmult(n - 1 - u, sum(psum, l, u));
            sub = modadd(sub, modadd(all, -over));
        }
        long res = modadd(total, -sub);
        out.println(res);
        in.close();
        out.close();
    }
    static long sum(long[] p, int l, int r) {
        return modadd(p[r], -(l > 0 ? p[l - 1] : 0));
    }
    static long choose2(int a) {
        if(a < 2) {
            return 0;
        }
        return (long)a * (a - 1) / 2 % MOD;
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
}
