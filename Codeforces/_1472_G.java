import java.util.*;
import java.io.*;

public class _1472_G {
    static int[] closest;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            in.readLine();
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            ArrayList<Integer>[] graph = new ArrayList[n];
            ArrayList<Integer>[] dag = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Integer>();
                dag[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                graph[u].add(v);
            }
            int[] d = new int[n];
            Arrays.fill(d, -1);
            ArrayDeque<BFS> bfs = new ArrayDeque<BFS>();
            bfs.add(new BFS(0, -1, 0));
            while(bfs.size() > 0) {
                BFS cur = bfs.poll();
                if(d[cur.cur] != -1) {
                    if(d[cur.cur] == cur.depth) {
                        dag[cur.prev].add(cur.cur);
                    }
                    continue;
                }
                d[cur.cur] = cur.depth;
                if(cur.prev != -1) {
                    dag[cur.prev].add(cur.cur);
                }
                for(int nei : graph[cur.cur]) {
                    bfs.add(new BFS(nei, cur.cur, cur.depth + 1));
                }
            }
            closest = new int[n];
            Arrays.fill(closest, Integer.MAX_VALUE);
            dfs(dag, graph, d, 0);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(closest[i]);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static int dfs(ArrayList<Integer>[] sp_tree, ArrayList<Integer>[] graph, int[] d, int cur) {
        if(closest[cur] != Integer.MAX_VALUE) {
            return closest[cur];
        }
        int best = d[cur];
        for(int nei : graph[cur]) {
            best = Math.min(best, d[nei]);
        }
        for(int nei : sp_tree[cur]) {
            best = Math.min(best, dfs(sp_tree, graph, d, nei));
        }
        closest[cur] = best;
        return best;
    }
    static class BFS {
        int cur, prev, depth;
        BFS(int c, int p, int d) {
            cur = c;
            prev = p;
            depth = d;
        }
    }
}
