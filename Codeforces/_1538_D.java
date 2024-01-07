import java.util.*;
import java.io.*;

public class _1538_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            if(k == 1) {
                if((a % b == 0 || b % a == 0) && a != b) {
                    out.println("YES");
                }else {
                    out.println("NO");
                }
            }else {
                int af = numPrimeFactors(a);
                int bf = numPrimeFactors(b);
                if(k <= af + bf) {
                    out.println("YES");
                }else {
                    out.println("NO");
                }
            }
        }
        in.close();
        out.close();
    }
    static int numPrimeFactors(int x) {
        int ret = 0;
        for(int i = 2; i * i <= x; i++) {
            while(x % i == 0) {
                ret++;
                x /= i;
            }
        }
        if(x > 1) ret++;
        return ret;
    }
}
