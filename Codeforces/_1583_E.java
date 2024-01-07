import java.util.*;
import java.io.*;

public class _1583_E {
    static Stack<Integer> res;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        ArrayList<Integer>[] tree = new ArrayList[n];
        int[] par = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
            par[i] = i;
        }
        for (int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(line.nextToken()) - 1;
            int v2 = Integer.parseInt(line.nextToken()) - 1;
            if (find(par, v1) != find(par, v2)) {
                union(par, rank, v1, v2);
                tree[v1].add(v2);
                tree[v2].add(v1);
            }
        }
        int[] count = new int[n];
        int q = Integer.parseInt(in.readLine());
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(line.nextToken()) - 1;
            int v2 = Integer.parseInt(line.nextToken()) - 1;
            queries[i] = new int[] { v1, v2 };
            count[v1]++;
            count[v2]++;
        }
        int odds = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] % 2 == 1) {
                odds++;
            }
        }
        if (odds > 0) {
            out.println("NO");
            out.println(odds / 2);
        } else {
            out.println("YES");
            for (int i = 0; i < q; i++) {
                dfs(tree, new boolean[n], queries[i][0], queries[i][1], new Stack<Integer>());
                StringBuilder sb = new StringBuilder();
                sb.append(queries[i][0] + 1);
                for (int j = 0; j < res.size(); j++) {
                    sb.append(' ');
                    sb.append(res.get(j) + 1);
                }
                out.println(res.size() + 1);
                out.println(sb.toString());
            }
        }
        in.close();
        out.close();
    }

    static boolean dfs(ArrayList<Integer>[] tree, boolean[] visited, int node, int dest, Stack<Integer> path) {
        visited[node] = true;
        if (node == dest) {
            res = path;
            return true;
        }
        for (int nei : tree[node]) {
            if (!visited[nei]) {
                path.push(nei);
                if (dfs(tree, visited, nei, dest, path)) {
                    return true;
                }
                path.pop();
            }
        }
        return false;
    }

    static int find(int[] parents, int node) {
        if (parents[node] == node) {
            return node;
        }
        parents[node] = find(parents, parents[node]);
        return parents[node];
    }

    static void union(int[] parents, int[] dists, int a, int b) {
        int roota = find(parents, a);
        int rootb = find(parents, b);
        if (dists[a] > dists[b]) {
            parents[rootb] = roota;
        } else if (dists[b] > dists[a]) {
            parents[roota] = rootb;
        } else {
            parents[rootb] = roota;
            dists[roota] += 1;
        }
    }
}