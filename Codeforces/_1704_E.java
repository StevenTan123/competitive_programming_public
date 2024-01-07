import java.util.*;
import java.io.*;

// First toposort, and simulate until all 0 value nodes are earlier in toposort.
// Then, one by one in order, calculate state after earliest node becomes 0 and continue.
// O(n^2)

public class _1704_E {
    static int MOD = 998244353;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            ArrayList<Integer>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                graph[u].add(v);
            }

            long res = 0;
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> nonzero = new ArrayList<Integer>();
                for (int j = 0; j < n; j++) {
                    if (a[j] > 0) {
                        nonzero.add(j);
                    }
                }
                if (nonzero.size() == 0) {
                    break;
                }
                for (int j : nonzero) {
                    a[j]--;
                    for (int nei : graph[j]) {
                        a[nei]++;
                    }
                }
                res++;
            }
            
            ArrayList<Integer> topo = toposort(graph);
            // dp[i] stores how many it adds to the sink given 1 value at node i.
            int[] dp = new int[n];
            for (int i = 0; i < n; i++) {
                int cur = topo.get(i);
                for (int nei : graph[cur]) {
                    dp[cur] = (dp[cur] + dp[nei]) % MOD;
                }
                if (i == 0) {
                    dp[cur] = 1;
                }
            }
            for (int i = 0; i < n; i++) {
                res = (res + (long) dp[i] * a[i]) % MOD;
            }
            out.println(res);
		}
		
        in.close();
		out.close();
	}
    static void dfs(ArrayList<Integer>[] graph, int node, boolean[] visited, ArrayList<Integer> topo) {
        visited[node] = true;
        for (int nei : graph[node]) {
            if (!visited[nei]) {
                dfs(graph, nei, visited, topo);
            }
        }
        topo.add(node);
    }
    static ArrayList<Integer> toposort(ArrayList<Integer>[] graph) {
        boolean[] visited = new boolean[graph.length];
        ArrayList<Integer> topo = new ArrayList<Integer>();
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, topo);
            }
        }
        return topo;
    }
}
