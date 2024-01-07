import java.util.*;
import java.io.*;

public class _1547_G {
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
            for(int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                graph[u].add(v);
            }
            ArrayList<Integer> cycle = new ArrayList<Integer>();
            ArrayList<Integer> multi = new ArrayList<Integer>();
            int[] marks = new int[n];
            dfs1(graph, marks, cycle, multi, 0);
            boolean[] cyc_vis = new boolean[n];
            for(int cyc_node : cycle) {
                dfs2(graph, cyc_vis, cyc_node);
            }
            boolean[] multi_vis = new boolean[n];
            for(int multi_node : multi) {
                dfs2(graph, multi_vis, multi_node);
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                if(marks[i] == 0) {
                    sb.append(0);
                }else if(cyc_vis[i]) {
                    sb.append(-1);
                }else if(multi_vis[i]) {
                    sb.append(2);
                }else {
                    sb.append(1);
                }
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static void dfs1(ArrayList<Integer>[] graph, int[] marks, ArrayList<Integer> cycle, ArrayList<Integer> multi, int node) {
        if(marks[node] == 1) {
            cycle.add(node);
            return;
        }else if(marks[node] == 2) {
            multi.add(node);
            return;
        }
        marks[node] = 1;
        for(int nei : graph[node]) {
            dfs1(graph, marks, cycle, multi, nei);
        }
        marks[node] = 2;
    }
    static void dfs2(ArrayList<Integer>[] graph, boolean[] visited, int node) {
        if(visited[node]) {
            return;
        }
        visited[node] = true;
        for(int nei : graph[node]) {
            dfs2(graph, visited, nei);
        }
    }
}
