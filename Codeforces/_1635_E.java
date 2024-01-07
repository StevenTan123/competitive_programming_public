import java.util.*;
import java.io.*;

public class _1635_E {
    static ArrayList<Integer> toposort;
    static ArrayList<Edge>[] graph;
    static int[] dirs;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int type = Integer.parseInt(line.nextToken());
            int u = Integer.parseInt(line.nextToken()) - 1;
            int v = Integer.parseInt(line.nextToken()) - 1;
            graph[u].add(new Edge(v, type));
            graph[v].add(new Edge(u, type));
        }
        dirs = new int[n];
        boolean possible = true;
        for (int i = 0; i < n; i++) {
            if (dirs[i] == 0) {
                possible = possible && bfs(i);
            }
        }
        if (possible) {
            boolean[] visited = new boolean[n];
            boolean[] stack = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    possible = possible && acyclic(i, visited, stack);
                }
            }
            if (possible) {
                boolean[] flagged = new boolean[n];
                toposort = new ArrayList<Integer>();
                for (int i = 0; i < n; i++) {
                    if (!flagged[i]) {
                        topo_dfs(i, flagged);
                    }
                }
                int[] res = new int[n];
                for (int i = toposort.size() - 1; i >= 0; i--) {
                    res[toposort.get(i)] = toposort.size() - i;
                }
                out.println("YES");
                for (int i = 0; i < n; i++) {
                    out.print(dirs[i] == 1 ? "L " : "R ");
                    out.println(res[i]);
                }
            } else {
                out.println("NO");
            }
        } else {
            out.println("NO");
        }

        in.close();
        out.close();
    }
    static void topo_dfs(int cur, boolean[] visited) {
        visited[cur] = true;
        for (Edge e : graph[cur]) {
            if (dirs[cur] == e.t && !visited[e.v]) {
                topo_dfs(e.v, visited);
            }
        }
        toposort.add(cur);
    }
    static boolean acyclic(int cur, boolean[] visited, boolean[] stack) {
        if (visited[cur]) {
            if (stack[cur]) {
                return false;
            } else {
                return true;
            }
        }
        visited[cur] = true;
        stack[cur] = true;
        
        for (Edge e : graph[cur]) {
            if (dirs[cur] == e.t) {
                boolean acyclic = acyclic(e.v, visited, stack);
                if (!acyclic) {
                    return false;
                }
            }
        }
        stack[cur] = false;
        return true;
    }
    static boolean bfs(int start) {
        ArrayDeque<Integer> BFS = new ArrayDeque<Integer>();
        dirs[start] = 1;
        BFS.add(start);
        while (BFS.size() > 0) {
            int cur = BFS.poll();
            for (Edge e : graph[cur]) {
                if (dirs[e.v] == 0) {
                    dirs[e.v] = 3 - dirs[cur];
                    BFS.add(e.v);
                } else if (dirs[cur] == dirs[e.v]) {
                    return false;
                }
            }
        }
        return true;
    }
    static class Edge {
        int v, t;
        Edge(int vv, int tt) {
            v = vv;
            t = tt;
        }
    }
}
