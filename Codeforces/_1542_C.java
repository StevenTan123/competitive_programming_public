import java.util.*;
import java.io.*;

public class _1542_C {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            long n = Long.parseLong(in.readLine());
            long prev = 1;
            long cur = 2;
            long res = 0;
            for(int i = 2; i < 10000; i++) {
                if(prev > n) {
                    break;
                }
                long total = n / prev;
                long sub = n / cur;
                res += (total - sub) * i;
                res %= MOD;
                prev = cur;
                cur = lcm(cur, i + 1);
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
