import java.util.*;
import java.io.*;

public class spainting {

    static long mod = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("spainting.in"));
        PrintWriter out = new PrintWriter("spainting.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        long[] diags = new long[n + 1];
        long psum = 0;
        for(int i = 1; i <= n; i++) {
            if(i == 1) {
                diags[i] = m;
                psum = m;
            }else {
                diags[i] = psum * (m - 1) % mod;
                psum += diags[i];
                psum %= mod;
                if(i >= k) {
                    psum -= diags[i - k + 1];
                    if(psum < 0) psum += mod;
                }
            }
        }
        long res = 0;
        for(int i = 0; i < k - 1; i++) {
            res += diags[n - i];
            res %= mod;
        }
        res = binpow(m, n) - res;
        if(res < 0) res += mod;
        out.println(res);
        in.close();
        out.close();
    }
    static long binpow(long a, long b) {
        if(b == 0) return 1;
        if(b % 2 == 0) {
            long pow = binpow(a, b/2);
            return pow * pow % mod;
        }else {
            long pow = binpow(a, (b-1)/2);
            return pow * pow % mod * a % mod;
        }
    }
}
