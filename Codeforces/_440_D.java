import java.util.*;
import java.io.*;

public class _440_D {
    static ArrayList<Integer>[] tree;
    static int[][] dp;
    static ArrayList<Integer>[][] edges;
    static int[][] mat;
    static int[] par;
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        n = Integer.parseInt(line.nextToken());
        k = Integer.parseInt(line.nextToken());
        tree = new ArrayList[n];
        mat = new int[n][n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
            Arrays.fill(mat[i], -1);
        }
        for(int i = 0; i < n - 1; i++) {
            line = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(line.nextToken()) - 1;
            int v2 = Integer.parseInt(line.nextToken()) - 1;
            tree[v1].add(v2);
            tree[v2].add(v1);
            mat[v1][v2] = i + 1;
            mat[v2][v1] = i + 1;
        }
        dp = new int[n][k + 1];
        edges = new ArrayList[n][k + 1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for(int j = 0; j <= k; j++) {
                edges[i][j] = new ArrayList<Integer>();
            }
        }
        par = new int[n];
        dfs(0, -1);
        int min = Integer.MAX_VALUE;
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i < n; i++) {
            if(dp[i][k] != Integer.MAX_VALUE) {
                int val = (i == 0 ? 0 : 1) + dp[i][k];
                if(val < min) {
                    min = val;
                    res = edges[i][k];
                    if(i != 0) {
                        res.add(mat[i][par[i]]);
                    }
                }
            }
        }
        out.println(min);
        Collections.sort(res);
        StringBuilder sb = new StringBuilder();
        for(int edge : res) {
            sb.append(edge);
            sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
    static void dfs(int node, int prev) {
        par[node] = prev;
        dp[node][1] = 0;
        for(int nei : tree[node]) {
            if(nei != prev) {
                dfs(nei, node);
                int[] new_dp = new int[k + 1];
                ArrayList<Integer>[] new_edges = new ArrayList[k + 1];
                for(int i = 0; i <= k; i++) {
                    if(dp[node][i] != Integer.MAX_VALUE) {
                        dp[node][i]++;
                        edges[node][i].add(mat[node][nei]);
                    }
                    new_dp[i] = dp[node][i];
                    new_edges[i] = edges[node][i];
                }
                for(int i = 0; i <= k; i++) {
                    if(dp[nei][i] == Integer.MAX_VALUE) {
                        continue;
                    }
                    for(int j = i; j <= k; j++) {
                        if (dp[node][j - i] == Integer.MAX_VALUE) {
                            continue;
                        }
                        int val = dp[node][j - i] + dp[nei][i] - 1;
                        if(val < new_dp[j]) {
                            new_dp[j] = val;
                            new_edges[j] = new ArrayList<Integer>(edges[node][j - i]);
                            int size = new_edges[j].size();
                            if(size > 0) {
                                new_edges[j].remove(size - 1);
                            }
                            for(int edge : edges[nei][i]) {
                                new_edges[j].add(edge);
                            }
                        }
                    }
                }
                dp[node] = new_dp;
                edges[node] = new_edges;
            }
        }
    }
}