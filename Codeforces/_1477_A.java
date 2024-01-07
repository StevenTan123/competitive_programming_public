import java.util.*;
import java.io.*;

public class _1477_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            long k = Long.parseLong(line.nextToken());
            long[] x = new long[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                x[i] = Long.parseLong(line.nextToken());
            }
            Arrays.sort(x);
            long mod = x[1] - x[0];
            for(int i = 2; i < n; i++) {
                mod = gcd(mod, x[i] - x[i - 1]);
            }
            long first = x[0] % mod;
            if(first < 0) first += mod;
            boolean alleq = true;
            for(int i = 0; i < n; i++) {
                long cur = x[i] % mod;
                if(cur < 0) cur += mod;
                if(cur != first) {
                    alleq = false;
                    break;
                }
            }
            if(alleq) {
                long kmod = k % mod;
                if(kmod < 0) kmod += mod;
                if(kmod == first) {
                    out.println("YES");
                }else {
                    out.println("NO");
                }
            }else {
                out.println("YES");
            }
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
}
