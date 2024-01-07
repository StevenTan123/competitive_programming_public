import java.util.*;
import java.io.*;

public class _1615_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[][] edges = new int[n - 1][3];
            ArrayList<Edge>[] graph = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Edge>();
            }
            for(int i = 0; i < n - 1; i++) {
                line = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(line.nextToken()) - 1;
                int y = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken());
                if(v != -1) {
                    int cur = v;
                    int p = 0;
                    while(cur > 0) {
                        p ^= cur % 2;
                        cur /= 2;
                    }
                    graph[x].add(new Edge(x, y, p));
                    graph[y].add(new Edge(y, x, p));
                }
                edges[i] = new int[]{x, y, v};
            }
            for(int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(line.nextToken()) - 1;
                int b = Integer.parseInt(line.nextToken()) - 1;
                int p = Integer.parseInt(line.nextToken());
                graph[a].add(new Edge(a, b, p));
                graph[b].add(new Edge(b, a, p));
            }
            int[] colors = new int[n];
            Arrays.fill(colors, -1);
            boolean possible = true;
            for(int i = 0; i < n; i++) {
                if(colors[i] == -1) {
                    possible = possible && dfs(graph, colors, new Edge(-1, i, -1), 0);
                    if(!possible) {
                        break;
                    }
                }
            }
            if(!possible) {
                out.println("NO");
            }else {
                out.println("YES");
                for(int i = 0; i < n; i++) {
                    if(colors[i] == -1) {
                        colors[i] = 0;
                    }
                }
                for(int i = 0; i < n - 1; i++) {
                    int color = edges[i][2];
                    if(color == -1) {
                        color = colors[edges[i][0]] ^ colors[edges[i][1]];
                    }
                    out.println((edges[i][0] + 1) + " " + (edges[i][1] + 1) + " " + color);
                }
            }
        }
        in.close();
        out.close();
    }
    static boolean dfs(ArrayList<Edge>[] graph, int[] colors, Edge cur, int color) {
        if(colors[cur.to] != -1) {
            if(color == colors[cur.to]) {
                return true;
            }
            return false;
        }
        colors[cur.to] = color;
        for(Edge e : graph[cur.to]) {
            if(!dfs(graph, colors, e, color ^ e.p)) {
                return false;
            }
        }
        return true;
    }
    static class Edge {
        int from, to, p;
        Edge(int f, int t, int pp) {
            from = f;
            to = t;
            p = pp;
        }
    }
}
