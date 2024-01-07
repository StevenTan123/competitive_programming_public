import java.util.*;
import java.io.*;

public class problem_setting {
    static final long MOD = 1000000007;
    static final int MAXN = 100005;
    static long[] fact = new long[MAXN];
    static long[] inv_fact = new long[MAXN];
    static long[] order = new long[25];
    public static void main(String[] args) throws IOException {
        fact[0] = 1;
        inv_fact[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            fact[i] = fact[i - 1] * i % MOD;
            inv_fact[i] = inv_fact[i - 1] * modinv(i) % MOD;
        }
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j <= i; j++) {
                order[i] += fact[i] * inv_fact[j] % MOD;
                order[i] %= MOD;
            }
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[] cnt = new int[1 << m];
        String[] strs = new String[m];
        for (int i = 0; i < m; i++) {
            strs[i] = in.readLine();
        }
        for (int i = 0; i < n; i++) {
            int b = 0;
            for (int j = 0; j < m; j++) {
                b <<= 1;
                b += strs[j].charAt(i) == 'H' ? 1 : 0;
            }
            cnt[b]++;
        }

        long[] dp = new long[1 << m];
        long res = 0;
        for (int i = 0; i < (1 << m); i++) {
            int j = i;
            long ord = order(cnt[i]);
            while (j != 0) {
                j = (j - 1) & i;
                dp[i] += dp[j] * (ord - 1);
                dp[i] %= MOD;
            }
            dp[i] += ord - 1;
            res += dp[i];
            res %= MOD;
        }
        out.println(res);

        in.close();
        out.close();
    }
    
    static long modinv(long a) {
        return binpow(a, MOD - 2);
    }

    static long binpow(long a, long b) {
        if (b == 0) {
            return 1;
        }
        long small = binpow(a, b / 2);
        if (b % 2 == 0) {
            return small * small % MOD;
        } else {
            return small * small % MOD * a % MOD;
        }
    }

    static long order(int x) {
        if (x < 25) {
            return order[x];
        }
        long res = 0;
        for (int i = 0; i <= x; i++) {
            res += fact[x] * inv_fact[x - i] % MOD;
            res %= MOD;
        }
        return res;
    }
}
