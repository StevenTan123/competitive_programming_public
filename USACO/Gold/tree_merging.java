import java.util.*;
import java.io.*;

public class tree_merging {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(in.readLine());
            ArrayList<Integer>[] tree = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                tree[i] = new ArrayList<Integer>();
            }
            int[] p = new int[N];
            Arrays.fill(p, -1);
            boolean[] seen = new boolean[N];
            for (int i = 0; i < N - 1; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int cur = Integer.parseInt(line.nextToken()) - 1;
                int par = Integer.parseInt(line.nextToken()) - 1;
                p[cur] = par;
                tree[par].add(cur);
                seen[cur] = true;
            }
            int root = -1;
            for (int i = 0; i < N; i++) {
                if (!seen[i]) {
                    root = i;
                }
            }

            int M = Integer.parseInt(in.readLine());
            ArrayList<Integer>[] tree2 = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                tree2[i] = new ArrayList<Integer>();
            }
            int[] p2 = new int[N];
            Arrays.fill(p2, -1);
            for (int i = 0; i < M - 1; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int cur = Integer.parseInt(line.nextToken()) - 1;
                int par = Integer.parseInt(line.nextToken()) - 1;
                p2[cur] = par;
                tree2[par].add(cur);
            }

            int[] d = new int[N];
            int[] d2 = new int[N];
            Arrays.fill(d2, -1);
            int maxd = dfs(tree, root, 0, d);
            dfs(tree2, root, 0, d2);

            ArrayList<Integer>[] d_arr = depth_arrays(d, maxd);
            ArrayList<Integer>[] d2_arr = depth_arrays(d2, maxd);

            // share[i][j] tells if node i in orig and j in new share any subtrees
            boolean[][] share = new boolean[N][N];
            for (int i = maxd; i > 0; i--) {
                ArrayList<Integer> orig = d_arr[i];
                ArrayList<Integer> end = d2_arr[i];
                for (int n1 : orig) {
                    for (int n2 : end) {
                        if (n1 == n2 || share[n1][n2]) {
                            share[p[n1]][p2[n2]] = true;
                        }
                    }
                }
            }

            // max_depths[i][j] = max node in subtree of i at depth j
            int[][] max_depths = new int[N][maxd + 1];
            for (int i = 0; i < N; i++) {
                Arrays.fill(max_depths[i], -1);
            }
            for (int i = maxd; i >= 0; i--) {
                ArrayList<Integer> orig = d_arr[i];
                for (int node : orig) {
                    max_depths[node][i] = node;
                    for (int child : tree[node]) {
                        for (int j = i + 1; j <= maxd; j++) {
                            if (max_depths[child][j] == -1) {
                                break;
                            }
                            max_depths[node][j] = Math.max(max_depths[node][j], max_depths[child][j]);
                        }
                    }
                }
            }

            int[][] max_depths2 = new int[N][maxd + 1];
            for (int i = 0; i < N; i++) {
                Arrays.fill(max_depths2[i], -1);
            }
            for (int i = maxd; i >= 0; i--) {
                ArrayList<Integer> orig = d2_arr[i];
                for (int node : orig) {
                    max_depths2[node][i] = node;
                    for (int child : tree2[node]) {
                        for (int j = i + 1; j <= maxd; j++) {
                            if (max_depths2[child][j] == -1) {
                                break;
                            }
                            max_depths2[node][j] = Math.max(max_depths2[node][j], max_depths2[child][j]);
                        }
                    }
                }
            }

            ArrayList<Pair> ops = new ArrayList<Pair>();
            for (int i = 1; i <= maxd; i++) {
                ArrayList<Integer> orig = d_arr[i];
                ArrayList<Integer> end = d2_arr[i];
                HashSet<Integer> end_set = new HashSet<Integer>(end);

                ArrayList<Integer> unmerged = new ArrayList<Integer>();
                for (int n1 : orig) {
                    if (end_set.contains(n1)) {
                        continue;
                    }
                    boolean leaf = tree[n1].size() == 0;
                    boolean merged = false;
                    for (int n2 : end) {
                        if (n1 < n2 && p[n1] == p[n2] && share[n1][n2]) {
                            ops.add(new Pair(n1 + 1, n2 + 1));
                            for (int child : tree[n1]) {
                                p[child] = n2;
                            }
                            merged = true;
                            break;
                        }
                        if (leaf && n1 < n2 && p[n1] == p[n2]) {
                            ops.add(new Pair(n1 + 1, n2 + 1));
                            merged = true;
                            break;
                        }
                    }
                    if (!merged) {
                        unmerged.add(n1);
                    }
                }

                for (int n1 : unmerged) {
                    for (int n2 : end) {
                        boolean works = true;
                        for (int j = i; j <= maxd; j++) {
                            if (max_depths[n1][j] == -1) {
                                break;
                            }
                            if (max_depths[n1][j] > max_depths2[n2][j]) {
                                works = false;
                                break;
                            }
                        }
                        if (p[n1] == p[n2] && works) {
                            ops.add(new Pair(n1 + 1, n2 + 1));
                            for (int child : tree[n1]) {
                                p[child] = n2;
                            }
                            break;
                        }
                    }
                }
            }

            out.println(ops.size());
            for (Pair op : ops) {
                out.println(op.a + " " + op.b);
            }
        }

        in.close();
        out.close();
    }

    static ArrayList<Integer>[] depth_arrays(int[] d, int maxd) {
        ArrayList<Integer>[] d_arr = new ArrayList[maxd + 1];
        for (int i = 0; i <= maxd; i++) {
            d_arr[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < d.length; i++) {
            if (d[i] != -1) {
                d_arr[d[i]].add(i);
            }
        }
        return d_arr;
    }

    static int dfs(ArrayList<Integer>[] tree, int cur, int depth, int[] d) {
        d[cur] = depth;
        int maxd = depth;
        for (int nei : tree[cur]) {
            maxd = Math.max(maxd, dfs(tree, nei, depth + 1, d));
        }
        return maxd;
    }

    static class Pair {
        int a, b;
        Pair(int aa, int bb) {
            a = aa;
            b = bb;
        }
    }
}
