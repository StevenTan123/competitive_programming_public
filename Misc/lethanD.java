import java.util.*;
import java.io.*;

public class lethanD {
    static ArrayList<Integer>[] tree;
    static int[] size;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(in.readLine());
        tree = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < n - 1; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(line.nextToken()) - 1;
            int v2 = Integer.parseInt(line.nextToken()) - 1;
            tree[v1].add(v2);
            tree[v2].add(v1);
        }
        int max = 0;
        for(int i = 0; i < n; i++) {
            size = new int[n];
            dfs(i, new boolean[n]);
            int[] sorted = new int[tree[i].size()];
            for(int j = 0; j < tree[i].size(); j++) {
                sorted[j] = size[tree[i].get(j)];
            }
            Arrays.sort(sorted);
            int sum = 1;
            for(int j = sorted.length - 2; j >= 0; j--) {
                sum += sorted[j];
            }
            max = Math.max(max, sum);
        }
        out.println(max);
        in.close();
        out.close();
    }
    static void dfs(int node, boolean[] visited) {
        visited[node] = true;
        for(int nei : tree[node]) {
            if(!visited[nei]) {
                dfs(nei, visited);
                size[node] += size[nei];
            }
        }
        size[node]++;
    }
}
