import java.util.*;
import java.io.*;

public class _1805_E {
    static int[] a, par, par_edge, counts, highest;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        ArrayList<Edge>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(line.nextToken()) - 1;
            int v = Integer.parseInt(line.nextToken()) - 1;
            tree[u].add(new Edge(v, i));
            tree[v].add(new Edge(u, i));
        }
        int[][] sorted = new int[n][2];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            sorted[i][0] = Integer.parseInt(line.nextToken());
            sorted[i][1] = i;
        }
        Arrays.sort(sorted, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        a = new int[n];
        Arrays.fill(a, -1);
        int[] mapping = new int[n];
        int max_3 = 0;
        int max = 0;
        int val = 0;
        int prev = 0;
        int start = -1;
        int end = -1;
        for (int i = 0; i <= n; i++) {
            if (i > 0 && (i == n || sorted[i][0] != sorted[i - 1][0])) {
                if (i - prev == 2) {
                    a[sorted[i - 1][1]] = val;
                    a[sorted[i - 2][1]] = val;
                    mapping[val] = sorted[prev][0];
                    val++;

                    max = Math.max(max, sorted[prev][0]);
                    start = sorted[i - 1][1];
                    end = sorted[i - 2][1];
                } else {
                    if (i - prev > 2) {
                        max_3 = Math.max(max_3, sorted[prev][0]);
                        max = Math.max(max, max_3);
                    }
                }
                prev = i;
            }
        }
        par = new int[n];
        par_edge = new int[n];
        int[] res = new int[n - 1];
        Arrays.fill(res, max);
        if (start != -1 && end != -1) {
            dfs_par(tree, start, -1, -1);
            int cur = end;
            highest = new int[] {val - 1, -1};
            counts = new int[n];
            prev = -1;
            while (par[cur] != -1) {
                dfs(tree, cur, par[cur], prev);
                res[par_edge[cur]] = Math.max(max_3, Math.max(highest[0] == -1 ? 0 : mapping[highest[0]], highest[1] == -1 ? 0 : mapping[highest[1]]));
                prev = cur;
                cur = par[cur];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            sb.append(res[i]);
            sb.append('\n');
        }
        out.print(sb.toString());
        in.close();
        out.close();
    }
    static void dfs_par(ArrayList<Edge>[] tree, int cur, int prev, int prev_edge) {
        par[cur] = prev;
        par_edge[cur] = prev_edge; 
        for (Edge e : tree[cur]) {
            if (e.v != prev) {
                dfs_par(tree, e.v, cur, e.i);
            }
        }
    }
    static void dfs(ArrayList<Edge>[] tree, int cur, int prev, int prev2) {
        if (a[cur] != -1) {
            counts[a[cur]]++;
            if (counts[a[cur]] == 2) {
                highest[1] = Math.max(highest[1], a[cur]);
            }
        }
        while (highest[0] >= 0 && counts[highest[0]] == 1) {
            highest[0]--;
        }
        for (Edge e : tree[cur]) {
            if (e.v != prev && e.v != prev2) {
                dfs(tree, e.v, cur, cur);
            }
        }
    }
    static class Edge {
        int v, i;
        Edge(int vv, int ii) {
            v = vv;
            i = ii;
        }
    }
}
