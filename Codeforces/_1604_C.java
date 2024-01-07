import java.util.*;
import java.io.*;

public class _1604_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            long lcm = 1;
            boolean possible = true;
            for(int i = 0; i < n; i++) {
                if(lcm <= 1000000000) {
                    lcm = lcm(lcm, i + 2);
                }
                a[i] = Integer.parseInt(line.nextToken());
                if(a[i] % lcm == 0) {
                    possible = false;
                    break;
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
    static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
