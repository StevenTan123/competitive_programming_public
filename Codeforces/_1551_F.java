import java.util.*;
import java.io.*;

public class _1551_F {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            in.readLine();
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            ArrayList<Integer>[] tree = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i < n - 1; i++) {
                line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                tree[u].add(v);
                tree[v].add(u);
            }
            if(k == 2) {
                out.println(n * (n - 1) / 2);
                continue;
            }
            long res = 0;
            for(int i = 0; i < n; i++) {
                if(tree[i].size() < k) {
                    continue;
                }
                int[][] counts = new int[n][tree[i].size()];
                for(int j = 0; j < tree[i].size(); j++) {
                    dfs(tree, counts, j, tree[i].get(j), i, 0);
                }
                for(int j = 0; j < n; j++) {
                    res = modadd(res, prod_k_subsets(counts[j], k));
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static long prod_k_subsets(int[] a, int k) {
        long[][] dp = new long[a.length][k + 1];
        for(int i = 0; i < a.length; i++) {
            dp[i][1] = a[i] + (i > 0 ? dp[i - 1][1] : 0);
            for(int j = 2; j <= Math.min(k, i + 1); j++) {
                if(i > 0) {
                    dp[i][j] = modadd(dp[i - 1][j], modmult(dp[i - 1][j - 1], a[i]));
                }
            }
        }
        return dp[a.length - 1][k];
    }
    static void dfs(ArrayList<Integer>[] tree, int[][] counts, int child, int node, int prev, int depth) {
        counts[depth][child]++;
        for(int nei : tree[node]) {
            if(nei != prev) {
                dfs(tree, counts, child, nei, node, depth + 1);
            }
        }
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
}
