import java.util.*;
import java.io.*;

public class _1540_B {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        ArrayList<Integer>[] tree = new ArrayList[n];
        for(int i = 0; i < n; i++) tree[i] = new ArrayList<Integer>();
        for(int i = 0; i < n - 1; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(line.nextToken()) - 1;
            int v2 = Integer.parseInt(line.nextToken()) - 1;
            tree[v1].add(v2);
            tree[v2].add(v1);
        }
        long[][] f = new long[n][n];
        for(int i = 1; i < n; i++) {
            f[i][0] = 0;
            f[0][i] = 1;
        }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < n; j++) {
                f[i][j] = (f[i - 1][j] + f[i][j - 1]) % MOD;
                f[i][j] = moddiv(f[i][j], 2);
            }
        }
        long res = 0;
        for(int i = 0; i < n; i++) {
            ArrayList<Integer>[] rtree = new ArrayList[n];
            for(int j = 0; j < n; j++) rtree[j] = new ArrayList<Integer>();
            int[] par = new int[n];
            int[] d = new int[n];
            reroot(tree, i, 0, new boolean[n], rtree, par, d);
            par[i] = -1;
            int[][] lca = new int[n][n];
            for(int j = 0; j < n; j++) {
                lca(rtree, par, j, lca);
            }
            for(int j = 0; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    int d1 = d[j] - d[lca[j][k]];
                    int d2 = d[k] - d[lca[j][k]];
                    res += f[d2][d1];
                    res %= MOD;
                }
            }
        }
        res = moddiv(res, n);
        out.println(res);
		in.close();
		out.close();
	}
    static void reroot(ArrayList<Integer>[] tree, int cur, int depth, boolean[] visited, ArrayList<Integer>[] rtree, int[] par, int[] d) {
        visited[cur] = true;
        d[cur] = depth;
        for(int nei : tree[cur]) {
            if(!visited[nei]) {
                par[nei] = cur;
                rtree[cur].add(nei);
                reroot(tree, nei, depth + 1, visited, rtree, par, d);
            }
        }
    } 
    static void lca(ArrayList<Integer>[] tree, int[] par, int node, int[][] lca) {
        int stop = -1;
        int cur = node;
        while(cur > -1) {
            dfs(tree, cur, stop, node, cur, lca);
            stop = cur;
            cur = par[cur];
        }
    }
    static void dfs(ArrayList<Integer>[] tree, int cur, int stop, int node, int lcan, int[][] lca) {
        lca[node][cur] = lcan;
        for(int nei : tree[cur]) {
            if(nei != stop) dfs(tree, nei, stop, node, lcan, lca);
        }
    }
    static long modsub(long a, long b) {
        long res = (a - b) % MOD;
        if(res < 0) {
            res += MOD;
        }
        return res;
    }
    static long moddiv(long a, long b) {
        b %= MOD;
        if(b == 0) {
            return -1;
        }
        return modinv(b) * a % MOD;
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
