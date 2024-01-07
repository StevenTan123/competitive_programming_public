import java.util.*;
import java.io.*;

public class matrygons {
    static final int MAXN = 1000005;
    public static void main(String[] args) throws IOException {
        int[] dp = new int[MAXN];
        for(int i = 2; i < MAXN; i++) {
            dp[i] = 1;
            HashSet<Integer> divisors = gen_divisors(i);
            for(int divisor : divisors) {
                dp[i] = Math.max(dp[i], dp[divisor - 1] + 1);
            }
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            int ans = 1;
            HashSet<Integer> divisors = gen_divisors(n);
            for(int divisor : divisors) {
                if(divisor > 2) {
                    ans = Math.max(ans, dp[n / divisor - 1] + 1);
                }
            }
            String res = "Case #" + t + ": ";
            out.println(res + ans);
        }
        in.close();
        out.close();
    }
    static HashSet<Integer> gen_divisors(int num) {
        HashSet<Integer> divisors = new HashSet<Integer>();
        for(int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                divisors.add(i);
                divisors.add(num / i);
            }
        }
        return divisors;
    }
}
