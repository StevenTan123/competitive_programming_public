import java.util.*;
import java.io.*;

public class _1536_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            int[] a = new int[n];
            for(int i = 0; i < n; i++) {
                if(s.charAt(i) == 'K') a[i] = 1;
            }
            int sum = 0;
            HashMap<Long, Integer> seen = new HashMap<Long, Integer>();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sum += a[i];
                int num, den;
                if(sum == 0) {
                    num = 0;
                    den = 1;
                }else {
                    int gcd = gcd(sum, i + 1);
                    num = sum / gcd;
                    den = (i + 1) / gcd;
                }
                long hash = num * n + den;
                Integer prev = seen.get(hash);
                if(prev == null) prev = 0;
                seen.put(hash, prev + 1);
                sb.append(prev + 1);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
