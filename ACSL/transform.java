import java.util.*;
import java.io.*;

public class transform {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = 5;
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            String n = line.nextToken();
            int p = Integer.parseInt(line.nextToken());
            int digit = Character.getNumericValue(n.charAt(n.length() - p));
            String res = "";
            for(int i = 0; i < n.length(); i++) {
                int cur = Character.getNumericValue(n.charAt(i));
                if(i < n.length() - p) {
                    res += cur + digit;
                }else if(i == n.length() - p) {
                    res += numPrimeFactors(Long.parseLong(n));
                }else {
                    res += Math.abs(cur - digit);
                }
            }
            out.println((5 - t) + ". " + res);
        }
        in.close();
        out.close();
    }
    static int numPrimeFactors(long n) {
        HashSet<Long> factors = new HashSet<Long>();
        for(long i = 2; i * i <= n; i++) {
            while(n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if(n >= 2) {
            factors.add(n);
        }
        return factors.size();
    }
}
