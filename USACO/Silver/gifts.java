import java.util.*;
import java.io.*;

public class gifts {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] pref = new int[n][n];
        int[][] rank = new int[n][n];
        ArrayList<Integer>[] graph = new ArrayList[n];
        ArrayList<Integer>[] rev = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
            rev[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            boolean seen = false;
            for(int j = 0; j < n; j++) {
                pref[i][j] = Integer.parseInt(line.nextToken()) - 1;
                rank[i][pref[i][j]] = j;
                if(!seen) {
                    graph[pref[i][j]].add(i);
                    rev[i].add(pref[i][j]);
                }
                if(pref[i][j] == i) {
                    seen = true;
                }
            }
        }
        for(int i = 0; i < n; i++) {
            boolean[] vis = new boolean[n];
            dfs(graph, vis, i);
            int best = i;
            for(int nei : rev[i]) {
                if(vis[nei] && rank[i][nei] < rank[i][best]) {
                    best = nei;
                }
            }
            out.println(best + 1);
        }
        in.close();
        out.close();
    }
    static void dfs(ArrayList<Integer>[] graph, boolean[] vis, int node) {
        if(vis[node]) {
            return;
        }
        vis[node] = true;
        for(int nei : graph[node]) {
            dfs(graph, vis, nei);
        }
    }
}
