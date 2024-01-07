import java.util.*;
import java.io.*;

public class _1354_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());        
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        line = new StringTokenizer(in.readLine());
        int n1 = Integer.parseInt(line.nextToken());
        int n2 = Integer.parseInt(line.nextToken());
        int n3 = Integer.parseInt(line.nextToken());

        for (int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(line.nextToken()) - 1;
            int v = Integer.parseInt(line.nextToken()) - 1;
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] comps = new int[n];
        int[] colors = new int[n];
        Arrays.fill(comps, -1);
        int comp = 0;
        boolean possible = true;
        for (int i = 0; i < n; i++) {
            if (comps[i] == -1) {
                possible = possible && dfs(graph, comps, colors, i, 1, comp);
                if (!possible) {
                    break;
                }
                comp++;
            }
        }

        if (possible) {
            int[][] bipartite_sizes = new int[comp][2];
            for (int i = 0; i < n; i++) {
                bipartite_sizes[comps[i]][colors[i] - 1]++;
            }

            // dp[i][j] = possible to sum sizes of one bipartite group of each component
            // from 0...i-1 to equal j.
            boolean[][] dp = new boolean[comp][n2 + 1];
            int[][] backtrack = new int[comp][n2 + 1];
            if (bipartite_sizes[0][0] <= n2) {
                dp[0][bipartite_sizes[0][0]] = true;
                backtrack[0][bipartite_sizes[0][0]] = 1;
            }
            if (bipartite_sizes[0][1] <= n2) {
                dp[0][bipartite_sizes[0][1]] = true;
                backtrack[0][bipartite_sizes[0][1]] = 2;
            }
            for (int i = 0; i < comp - 1; i++) {
                for (int j = 0; j <= n2; j++) {
                    if (dp[i][j]) {
                        if (j + bipartite_sizes[i + 1][0] <= n2) {
                            dp[i + 1][j + bipartite_sizes[i + 1][0]] = true;
                            backtrack[i + 1][j + bipartite_sizes[i + 1][0]] = 1;
                        }
                        if (j + bipartite_sizes[i + 1][1] <= n2) {
                            dp[i + 1][j + bipartite_sizes[i + 1][1]] = true;
                            backtrack[i + 1][j + bipartite_sizes[i + 1][1]] = 2;
                        }
                    }
                }
            }

            if (dp[comp - 1][n2]) {
                out.println("YES");

                int[] coloring = new int[n];
                int sum = n2;
                for (int i = comp - 1; i >= 0; i--) {
                    for (int j = 0; j < n; j++) {
                        if (comps[j] == i && colors[j] == backtrack[i][sum]) {
                            coloring[j] = 2;
                        }
                    }
                    sum -= bipartite_sizes[i][backtrack[i][sum] - 1];       
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (n1 > 0 && coloring[i] != 2) {
                        coloring[i] = 1;
                        n1--;
                    } else if (coloring[i] != 2) {
                        coloring[i] = 3;
                    }
                    sb.append(coloring[i]);
                }
                out.println(sb.toString());
            } else {
                out.println("NO");
            }
        } else {
            out.println("NO");
        }

        in.close();
        out.close();
    }

    static boolean dfs(ArrayList<Integer>[] graph, int[] comps, int[] colors, int cur, int color, int comp) {
        if (comps[cur] != -1) {
            if (colors[cur] != color) {
                return false;
            }
            return true;
        }
        comps[cur] = comp;
        colors[cur] = color;

        for (int nei : graph[cur]) {
            if (!dfs(graph, comps, colors, nei, 3 - color, comp)) {
                return false;
            }
        }
        return true;
    }
}
