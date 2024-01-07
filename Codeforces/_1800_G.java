import java.util.*;
import java.io.*;

public class _1800_G {
    static Comparator<long[]> comp = new Comparator<long[]>() {
            @Override
            public int compare(long[] a, long[] b) {
                return Long.compare(a[0], b[0]);
            }
        };
    static long MOD = 1000000007;
    static long[] vals, hashes;
    static int[] depths;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            vals = new long[n];
            hashes = new long[n];
            depths = new int[n];
            ArrayList<Integer>[] tree = new ArrayList[n];
            long a = 69420;
            for (int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Integer>();
                vals[i] = a;
                a = (2 * a + 1) % MOD;
            }
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                tree[u].add(v);
                tree[v].add(u);
            }
            hash_dfs(tree, 0, -1);
            out.println(sym_dfs(tree, 0, -1) ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
    static boolean sym_dfs(ArrayList<Integer>[] tree, int cur, int prev) {
        int children = 0;
        for (int nei : tree[cur]) {
            if (nei != prev) {
                children++;
            }
        }
        long[][] child_hashes = new long[children][2];
        int ind = 0;
        for (int nei : tree[cur]) {
            if (nei != prev) {
                child_hashes[ind][0] = hashes[nei];
                child_hashes[ind][1] = nei;
                ind++;
            }
        }
        Arrays.sort(child_hashes, comp);
        int prev_ind = 0;
        int odd_count = 0;
        int odd_node = -1;
        for (int i = 0; i <= children; i++) {
            if (i == children || child_hashes[i][0] != child_hashes[prev_ind][0]) {
                if ((i - prev_ind) % 2 == 1) {
                    odd_node = (int) child_hashes[prev_ind][1];
                    odd_count++;
                }
                prev_ind = i;
            }
        }
        return odd_count == 0 || (odd_count == 1 && children % 2 == 1 && sym_dfs(tree, odd_node, cur));
    }
    static void hash_dfs(ArrayList<Integer>[] tree, int cur, int prev) {
        int children = 0;
        for (int nei : tree[cur]) {
            if (nei != prev) {
                hash_dfs(tree, nei, cur);
                depths[cur] = Math.max(depths[cur], depths[nei]);
                children++;
            }
        }
        hashes[cur] = 1;
        if (children > 0) {
            depths[cur]++;
            for (int nei : tree[cur]) {
                if (nei != prev) {
                    hashes[cur] *= (vals[depths[cur]] + hashes[nei]) % MOD;
                    hashes[cur] %= MOD;
                }
            }
        }
    }
}
