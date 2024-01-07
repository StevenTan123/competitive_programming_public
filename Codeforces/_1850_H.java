import java.util.*;
import java.io.*;

public class _1850_H {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            ArrayList<Edge>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Edge>();
            }
            for (int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(line.nextToken()) - 1;
                int b = Integer.parseInt(line.nextToken()) - 1;
                long d = Long.parseLong(line.nextToken());
                graph[a].add(new Edge(b, -d));
                graph[b].add(new Edge(a, d));
            }

            long[] dists = new long[n];
            Arrays.fill(dists, Long.MAX_VALUE);
            boolean possible = true;
            for (int i = 0; i < n; i++) {
                if (dists[i] == Long.MAX_VALUE) {
                    possible = possible && dfs(graph, dists, i, 0);
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        
        in.close();
        out.close();
    }
    static boolean dfs(ArrayList<Edge>[] graph, long[] dists, int cur, long dist) {
        if (dists[cur] < Long.MAX_VALUE) {
            if (dists[cur] == dist) {
                return true;
            } else {
                return false;
            }
        }
        dists[cur] = dist;
        boolean possible = true;
        for (Edge e : graph[cur]) {
            possible = possible && dfs(graph, dists, e.n, dist + e.w);
        }
        return possible;
    }
    static class Edge {
        int n;
        long w;
        Edge(int nn, long ww) {
            n = nn;
            w = ww;
        }
    }
}
