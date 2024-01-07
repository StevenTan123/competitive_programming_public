import java.util.*;
import java.io.*;

public class balance_scale {
    static final long MOD = 1000000007;
    static long[] fact = new long[9000005];
    static long[] inv_fact = new long[9000005];
    public static void main(String[] args) throws IOException {
        fact[0] = 1;
        inv_fact[0] = 1;
        for(int i = 1; i < 9000005; i++) {
            fact[i] = modmult(fact[i - 1], i);
            inv_fact[i] = modmult(inv_fact[i - 1], modinv(i));
        }
        BufferedReader in = new BufferedReader(new FileReader("balance_scale_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());

            //x = # cookies in batch 1, a = (# cookies weight < batch 1), b = (# cookies weight = batch1), total = total # cookies
            int x = 0;
            int a = 0;
            int b = 0;
            int total = 0;

            int weight1 = 0;
            for(int i = 0; i < n; i++) {
                line = new StringTokenizer(in.readLine());
                int c = Integer.parseInt(line.nextToken());
                int w = Integer.parseInt(line.nextToken());
                if(i == 0) {
                    x += c;
                    weight1 = w;
                }else {
                    if(w < weight1) {
                        a += c;
                    }else if(w == weight1) {
                        b += c;
                    }
                }
                total += c;
            }
            long ans = modmult(modadd(nck(x + a + b, k + 1), -nck(a, k + 1)), modmult(x, modinv(x + b)));
            ans = modmult(ans, modinv(nck(total, k + 1)));
            String res = "Case #" + t + ": ";
            out.println(res + ans);
        }
        in.close();
        out.close();
    }
    static long nck(int n, int k) {
        if(n < k) {
            return 0;
        }
        return modmult(fact[n], modmult(inv_fact[n - k], inv_fact[k]));
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
