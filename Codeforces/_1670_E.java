import java.util.*;
import java.io.*;

public class _1670_E {
    public static void main(String[] args) throws IOException {
        int[] pow2 = new int[20];
        pow2[0] = 1;
        for (int i = 1; i < pow2.length; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int p = Integer.parseInt(in.readLine());
            int n = pow2[p];
            ArrayList<Edge>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Edge>();
            }
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                graph[u].add(new Edge(v, i));
                graph[v].add(new Edge(u, i));
            }
            int[] par = new int[n];
            int[] depths = new int[n];
            dfs(graph, 0, -1, -1, 0, par, depths);
            
            int[] nodes = new int[n];
            int[] edges = new int[n - 1];
            nodes[0] = n;
            for (int i = 1; i < n; i++) {
                if (depths[i] % 2 == 1) {
                    nodes[i] = i;
                    edges[par[i]] = i + n;
                } else {
                    nodes[i] = i + n;
                    edges[par[i]] = i;  
                }
            }
            StringBuilder sb1 = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb1.append(nodes[i]);
                sb1.append(' ');
            }
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < n - 1; i++) {
                sb2.append(edges[i]);
                sb2.append(' ');
            }
            out.println(1);
            out.println(sb1.toString());
            out.println(sb2.toString());
        }
        in.close();
        out.close();
    }
    static void dfs(ArrayList<Edge>[] graph, int cur, int prev, int edge, int depth, int[] par, int[] depths) {
        depths[cur] = depth;
        par[cur] = edge;
        for (Edge e : graph[cur]) {
            if (e.n != prev) {
                dfs(graph, e.n, cur, e.i, depth + 1, par, depths);
            }
        }
    }
    static class Edge {
        int n, i;
        Edge(int nn, int ii) {
            n = nn;
            i = ii;
        }
    }
}
