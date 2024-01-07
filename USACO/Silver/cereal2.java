import java.util.*;
import java.io.*;

public class cereal2 {
    static int n, m, extra;
    static boolean[] visited;
    static ArrayList<Integer> edges;
    static ArrayList<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        n = Integer.parseInt(line.nextToken());
        m = Integer.parseInt(line.nextToken());
        graph = new ArrayList[m];
        for(int i = 0; i < m; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        int[][] cows = new int[n][2];
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            int f = Integer.parseInt(line.nextToken()) - 1;
            int s = Integer.parseInt(line.nextToken()) - 1;
            cows[i][0] = f;
            cows[i][1] = s;
            graph[f].add(new Edge(s, i));
            graph[s].add(new Edge(f, i));
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        HashSet<Integer> used = new HashSet<Integer>();
        visited = new boolean[m];
        edges = new ArrayList<Integer>();
        for(int i = 0; i < m; i++) {
            if(!visited[i]) {
                edges.clear();
                extra = -1;
                dfs(i, -1);
                if(extra != -1) {
                    res.add(extra);
                    used.add(extra);
                    edges.clear();
                    dfs2(cows[extra][0], extra, new boolean[m]);
                }
                for(int edge : edges) {
                    res.add(edge);
                    used.add(edge);
                }
            }
        }
        out.println(n - res.size());
        for(int i = 0; i < n; i++) {
            if(!used.contains(i)) {
                res.add(i);
            }
        }
        for(int cow : res) {
            out.println(cow + 1);
        }
        in.close();
        out.close();
    }
    static void dfs(int node, int id) {
        visited[node] = true;
        for(Edge e : graph[node]) {
            if(visited[e.to]) {
                if(e.id != id) {
                    extra = e.id;
                }
            }else {
                edges.add(e.id);
                dfs(e.to, e.id);
            }
        }
    }
    static void dfs2(int node, int id, boolean[] vis) {
        vis[node] = true;
        for(Edge e : graph[node]) {
            if(!vis[e.to] && e.id != id) {
                edges.add(e.id);
                dfs2(e.to, id, vis);
            }
        }
    }
    static class Edge {
        int to, id;
        Edge(int t, int i) {
            to = t;
            id = i;
        }
    }
}
