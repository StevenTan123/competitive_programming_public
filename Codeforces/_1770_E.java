import java.util.*;
import java.io.*;

public class _1770_E {
    static final long MOD = 998244353;
    static final long half = 499122177;
    static long[] p;
    static int[] sizes, depth;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        line = new StringTokenizer(in.readLine());
        p = new long[n];
        for (int i = 0; i < k; i++) {
            p[Integer.parseInt(line.nextToken()) - 1] = 1;
        }

        ArrayList<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        int[][] edges = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            line = new StringTokenizer(in.readLine());
            edges[i][0] = Integer.parseInt(line.nextToken()) - 1;
            edges[i][1] = Integer.parseInt(line.nextToken()) - 1;
            tree[edges[i][0]].add(edges[i][1]);
            tree[edges[i][1]].add(edges[i][0]);
        }
        sizes = new int[n];
        depth = new int[n];
        dfs(tree, 0, -1);

        long res = 0;
        for (int i = 0; i < n - 1; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (depth[u] > depth[v]) {
                int temp = u;
                u = v;
                v = temp;
            }

            long p_move1 = modmult(modmult(modadd(1, -p[v]), p[u]), half);
            long p_move2 = modmult(modmult(modadd(1, -p[u]), p[v]), half);

            // Case 1: u -> v
            p[v] = modadd(p[v], p_move1);
            p[u] = modadd(p[u], -p_move1);

            // Case 2: v -> u
            p[u] = modadd(p[u], p_move2);
            p[v] = modadd(p[v], -p_move2);

            // Case 1: No movement across edge
            res = modadd(res, modmult(modadd(modadd(1, -p_move1), -p_move2), modmult(sizes[v], k - sizes[v])));
            // Case 2: u -> v
            res = modadd(res, modmult(p_move1, modmult(sizes[v] + 1, k - sizes[v] - 1)));
            // Case 3: v -> u
            res = modadd(res, modmult(p_move2, modmult(sizes[v] - 1, k - sizes[v] + 1)));            
        }
        long kc2 = (long)k * (long)(k - 1) / 2;
        res = modmult(res, modinv(kc2));
        out.println(res);

        in.close();
        out.close();
    }

    static void dfs(ArrayList<Integer>[] tree, int cur, int prev) {
        if (prev != -1) {
            depth[cur] = depth[prev] + 1;
        }
        if (p[cur] > 0) {
            sizes[cur] = 1;
        }
        for (int nei : tree[cur]) {
            if (nei != prev) {
                dfs(tree, nei, cur);
                sizes[cur] += sizes[nei];
            }
        }
    }

    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }

    static long modmult(long a, long b) {
        return a * b % MOD;
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
            return modmult(small, small);
        } else {
            return modmult(modmult(small, small), a);
        }
    }
}
