import java.util.*;
import java.io.*;

public class _1498_A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            long n = Long.parseLong(in.readLine());
            while(true) {
                if(gcd(n, digitsum(n)) > 1) break;
                n++;
            }
            out.println(n);
        }
        in.close();
        out.close();
    }
    static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
    static int digitsum(long a) {
        int sum = 0;
        while(a > 0) {
            sum += a % 10;
            a /= 10;
        }
        return sum;
    }
}
