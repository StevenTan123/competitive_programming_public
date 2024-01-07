import java.util.*;
import java.io.*;

public class _1528_B {
    static final int MOD = 998244353;
    public static void main(String[] args) throws IOException {
        int[] spf = new int[2000005];
        for (int i = 2; i < 2000005; i++) {
            if (spf[i] != 0)
                continue;
            for (int j = i; j < 2000005; j += i) {
                if (spf[j] == 0)
                    spf[j] = i;
            }
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        long[] dp = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = (dp[i - 1] * 2 % MOD + fill(spf, i * 2)) % MOD;
        }
        long res = (dp[n] - dp[n - 1]) % MOD;
        if (res < 0)
            res += MOD;
        out.println(res);
        in.close();
        out.close();
    }

    public static long fill(int[] spf, int x) {
        HashMap<Integer, Integer> factors = new HashMap<Integer, Integer>();
        while (x > 1) {
            Integer freq = factors.get(spf[x]);
            if (freq == null)
                freq = 0;
            factors.put(spf[x], freq + 1);
            x /= spf[x];
        }
        long count = 1;
        for (int factor : factors.keySet()) {
            if (factor == 2) {
                count *= factors.get(factor);
            } else {
                count *= factors.get(factor) + 1;
            }
        }
        return count;
    }
}