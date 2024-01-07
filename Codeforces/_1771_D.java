import java.util.*;
import java.io.*;

public class _1771_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            ArrayList<Integer>[] tree = new ArrayList[n];
            ArrayList<Pair>[] states = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Integer>();
                states[i] = new ArrayList<Pair>();
            }
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                tree[u].add(v);
                tree[v].add(u);
            }
            int[][] towards = new int[n][n];
            int[][] dists = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int nei : tree[i]) {
                    dfs(tree, dists[i], towards[i], nei, i, nei);
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    states[dists[i][j]].add(new Pair(i, j));
                }
            }
            int max = 1;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                dp[i][i] = 1;
            }
            for (int i = 0; i < n; i++) {
                for (Pair p : states[i]) {
                    if (s.charAt(p.a) == s.charAt(p.b)) {
                        if (dists[p.a][p.b] == 1) {
                            dp[p.a][p.b] = 2;
                        } else {
                            dp[p.a][p.b] = dp[towards[p.a][p.b]][towards[p.b][p.a]] + 2;
                        }
                    }
                    dp[p.a][p.b] = Math.max(dp[p.a][p.b],
                            Math.max(dp[towards[p.a][p.b]][p.b], dp[p.a][towards[p.b][p.a]]));
                    dp[p.b][p.a] = dp[p.a][p.b];
                    max = Math.max(max, dp[p.a][p.b]);
                }
            }
            out.println(max);
        }
        in.close();
        out.close();
    }

    static void dfs(ArrayList<Integer>[] tree, int[] dists, int[] towards, int node, int prev, int root) {
        towards[node] = root;
        dists[node] = dists[prev] + 1;
        for (int nei : tree[node]) {
            if (nei != prev) {
                dfs(tree, dists, towards, nei, node, root);
            }
        }
    }

    static class Pair {
        int a, b;

        Pair(int aa, int bb) {
            a = aa;
            b = bb;
        }
    }
}
