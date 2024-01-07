import java.util.*;
import java.io.*;

public class _1824_B {
    static final int MAXN = 200005;
    static long MOD = 1000000007;
    static long[] fact, inv_fact;
    static int[] sizes;
    public static void main(String[] args) throws IOException {
        fact = new long[MAXN];
        inv_fact = new long[MAXN];
        fact[0] = 1;
        inv_fact[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            fact[i] = fact[i - 1] * i % MOD;
            inv_fact[i] = inv_fact[i - 1] * modinv(i) % MOD;
        }

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        ArrayList<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; i++) {
            line = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(line.nextToken()) - 1;
            int v = Integer.parseInt(line.nextToken()) - 1;
            tree[u].add(v);
            tree[v].add(u);
        }

        if (k % 2 == 0) {
            sizes = new int[n];
            dfs(tree, 0, -1);
            long count = calc(tree, 0, -1, n, k);
            long nck = binom(n, k);
            out.println((count + nck) % MOD * modinv(nck) % MOD);
        } else {
            out.println(1);
        }

		in.close();
		out.close();
	}
    static void dfs(ArrayList<Integer>[] tree, int cur, int prev) {
        sizes[cur] = 1;
        for (int nei : tree[cur]) {
            if (nei != prev) {
                dfs(tree, nei, cur);
                sizes[cur] += sizes[nei];
            }
        }
    }
    static long calc(ArrayList<Integer>[] tree, int cur, int prev, int n, int k) {
        long ret = 0;
        if (prev != -1) {
            ret = binom(sizes[cur], k / 2) * binom(n - sizes[cur], k / 2) % MOD;
        }
        for (int nei : tree[cur]) {
            if (nei != prev) {
                ret = (ret + calc(tree, nei, cur, n, k)) % MOD;
            }
        }
        return ret;
    }
    static long binom(int n, int k) {
        if (k > n) {
            return 0;
        }
        return fact[n] * inv_fact[k] % MOD * inv_fact[n - k] % MOD;
    }
    static long modinv(long a) {
        return binpow(a, MOD - 2);
    }
    static long binpow(long a, long b) {
        if(b == 0) {
            return 1;
        }
        long small = binpow(a, b / 2);
        if(b % 2 == 0) {
            return small * small % MOD;
        }else {
            return small * small % MOD * a % MOD;
        }
    }
}
