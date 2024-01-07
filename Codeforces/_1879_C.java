import java.util.*;
import java.io.*;

public class _1879_C {
    static final int MAXN = 200005;
    static long MOD = 998244353;
    static long[] fact;
    public static void main(String[] args) throws IOException {
        fact = new long[MAXN];
        fact[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            String s = in.readLine();
            int prev = 0;
            int end_len = 0;
            long prod = 1;
            for (int i = 1; i <= s.length(); i++) {
                if(i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
                    prod *= i - prev;
                    prod %= MOD;
                    prev = i;
                    end_len++;
                }
            }
            int min = s.length() - end_len;
            prod *= fact[min];
            prod %= MOD;
            out.println(min + " " + prod);
        }
        in.close();
        out.close();
    }
}
