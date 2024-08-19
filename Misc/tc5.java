import java.util.*;
import java.io.*;

public class tc5 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        while(tt-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            ArrayList<Edge>[] graph = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Edge>();
            }

            line = new StringTokenizer(in.readLine());
            int t = Integer.parseInt(line.nextToken()) - 1;
            int w = Integer.parseInt(line.nextToken()) - 1;

            for(int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                int l = Integer.parseInt(line.nextToken()) - 1;
                graph[u].add(new Edge(v, l));
                graph[v].add(new Edge(u, l));
            }

            int res = 0;
            boolean[] collected = new boolean[n];
            HashSet<Long> visited = new HashSet<Long>();
            ArrayDeque<BFS> bfs = new ArrayDeque<BFS>();
            bfs.add(new BFS(t, w));
            while(bfs.size() > 0) {
                BFS cur = bfs.poll();
                visited.add(hash(cur.t, cur.w));
                if(!collected[cur.t]) {
                    res++;
                    collected[cur.t] = true;
                }
                if(!collected[cur.w]) {
                    res++;
                    collected[cur.w] = true;
                }
                for(Edge e : graph[cur.t]) {
                    if(e.l == cur.w && !visited.contains(hash(cur.w, e.n))) {
                        bfs.add(new BFS(cur.w, e.n));
                    }
                }
                for(Edge e : graph[cur.w]) {
                    if(e.l == cur.t && !visited.contains(hash(cur.t, e.n))) {
                        bfs.add(new BFS(cur.t, e.n));
                    }
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static class Edge {
        int n, l;
        Edge(int nn, int ll) {
            n = nn;
            l = ll;
        }
    }
    static class BFS {
        int t, w;
        BFS(int tt, int ww) {
            t = tt;
            w = ww;
        }
    }
    static long hash(int t, int w) {
        int min = Math.min(t, w);
        int max = Math.max(t, w);
        return (long)min * 200005 + max;
    }
}
