import java.util.*;
import java.io.*;

public class _1746_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            int GCD = a[0];
            int max = a[0];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                GCD = gcd(GCD, a[i]);
                max = Math.max(max, a[i]);
            }
            out.println(max / GCD);
        }
        in.close();
        out.close();
    }
    
    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

}
