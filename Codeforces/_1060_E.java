import java.util.*;
import java.io.*;

public class _1060_E {
    static int n;
    static long[] size, evens, even_paths;
    static long total = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(in.readLine());
        size = new long[n];
        evens = new long[n];
        even_paths = new long[n];
        ArrayList<Integer>[] tree = new ArrayList[n];
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
        dfs(tree, new boolean[n], 0);
        long odd_paths = (long)n * (n - 1) / 2 - even_paths[0];
        long res = (total + odd_paths) / 2;
        out.println(res);
        in.close();
        out.close();
    }
    static void dfs(ArrayList<Integer>[] tree, boolean[] visited, int node) {
        visited[node] = true;
        ArrayList<Long> even_child = new ArrayList<Long>();
        long even_sum = 0;
        ArrayList<Long> odd_child = new ArrayList<Long>();
        long odd_sum = 0;
        for(int child : tree[node]) {
            if(!visited[child]) {
                dfs(tree, visited, child);
                size[node] += size[child];
                evens[node] += size[child] - evens[child];
                even_paths[node] += even_paths[child];
                even_child.add(evens[child]);
                odd_child.add(size[child] - evens[child]);
                even_sum += evens[child];
                odd_sum += size[child] - evens[child];
            }
        }
        size[node] += 1;
        evens[node] += 1;
        total += size[node] * (n - size[node]);
        even_paths[node] += pair_sum(even_child, even_sum) + pair_sum(odd_child, odd_sum);
        even_paths[node] += evens[node] - 1;
    }
    static long pair_sum(ArrayList<Long> l, long sum) {
        long res = 0;
        for(long val : l) {
            res += val * (sum - val);
        }
        return res / 2;
    }
}
