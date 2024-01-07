import java.util.*;
import java.io.*;

public class _1830_C {
    static long MOD = 998244353;
    static int MAXN = 300005;
    static long[] fact, inv_fact, rbs_count;
	public static void main(String[] args) throws IOException {
        Random rand = new Random();

        fact = new long[MAXN];
        inv_fact = new long[MAXN];
        fact[0] = 1;
        inv_fact[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            fact[i] = fact[i - 1] * i % MOD;
            inv_fact[i] = inv_fact[i - 1] * modinv(i) % MOD;
        }

        rbs_count = new long[MAXN];
        for (int i = 0; i < MAXN; i += 2) {
            rbs_count[i] = binom(i, i / 2) * modinv(i / 2 + 1) % MOD;
        }

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken()) + 1;
            long[] xor_hash = new long[n + 1];
            for (int i = 0; i < k; i++) {
                int l, r;
                if (i == 0) {
                    l = 0;
                    r = n;
                } else {
                    line = new StringTokenizer(in.readLine());
                    l = Integer.parseInt(line.nextToken()) - 1;
                    r = Integer.parseInt(line.nextToken());
                }
                long cur_val = Math.abs(rand.nextLong());
                xor_hash[l] ^= cur_val;
                xor_hash[r] ^= cur_val;
            }
            for (int i = 0; i <= n; i++) {
                xor_hash[i] ^= i > 0 ? xor_hash[i - 1] : 0;
            }
            HashMap<Long, Integer> counts = new HashMap<Long, Integer>();
            for (int i = 0; i < n; i++) {
                Integer count = counts.get(xor_hash[i]);
                if (count == null) {
                    count = 0;
                }
                counts.put(xor_hash[i], count + 1);
            }
            long res = 1;
            for (long hash_key : counts.keySet()) {
                int count = counts.get(hash_key);
                res = res * rbs_count[count] % MOD;
            }
            out.println(res);
		}

		in.close();
		out.close();
	}
    static long binom(int n, int k) {
        return fact[n] * inv_fact[n - k] % MOD * inv_fact[k] % MOD;
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
    static long modinv(long a) {
        return binpow(a, MOD - 2);
    }
}