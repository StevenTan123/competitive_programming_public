import java.util.*;
import java.io.*;

public class _1831_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
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
            out.println(dfs(tree, 0, -1, n, 0));
        }
        in.close();
        out.close();
    }
    static int dfs(ArrayList<Edge>[] tree, int cur, int prev, int prev_ind, int count) {
        int res = count;
        for (Edge e : tree[cur]) {
            if (e.n != prev) {
                if (prev_ind > e.i) {
                    res = Math.max(res, dfs(tree, e.n, cur, e.i, count + 1));
                } else {
                    res = Math.max(res, dfs(tree, e.n, cur, e.i, count));
                }
            }
        }
        return res;
    }
    static class Edge {
        int n, i;
        Edge(int nn, int ii) {
            n = nn;
            i = ii;
        }
    }
}
