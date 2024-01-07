import java.util.*;
import java.io.*;

public class chain_reactions {
    static ArrayList<Integer>[] tree, order;
    static ArrayList<Integer> ans;
    static int[] f, p, max;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            n = Integer.parseInt(in.readLine());
            f = new int[n + 1];
            p = new int[n + 1];
            tree = new ArrayList[n + 1];
            order = new ArrayList[n + 1];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i <= n; i++) {
                if(i > 0) {
                    f[i] = Integer.parseInt(line.nextToken());
                }
                tree[i] = new ArrayList<Integer>();
                order[i] = new ArrayList<Integer>();
            }
            line = new StringTokenizer(in.readLine());
            for(int i = 1; i <= n; i++) {
                p[i] = Integer.parseInt(line.nextToken());
                tree[p[i]].add(i);
            }
            max = new int[n + 1];
            Arrays.fill(max, Integer.MAX_VALUE);
            dfs(0);
            toposort();
            boolean[] visited = new boolean[n + 1];
            long sum = 0;
            for(int i = ans.size() - 1; i >= 0; i--) {
                int cur = ans.get(i);
                int max = 0;
                while(cur > 0 && !visited[cur]) {
                    max = Math.max(max, f[cur]);
                    visited[cur] = true;
                    cur = p[cur];
                }
                sum += max;
            }
            String res = "Case #" + t + ": ";
            out.println(res + sum);
        }
        in.close();
        out.close();
    }
    static int dfs(int node) {
        int min_val = Integer.MAX_VALUE;
        int min_leaf = node;
        ArrayList<Integer> leaves = new ArrayList<Integer>();
        for(int nei : tree[node]) {
            int leaf = dfs(nei);
            leaves.add(leaf);
            if(max[nei] < min_val) {
                min_val = max[nei];
                min_leaf = leaf;
            }
        }
        for(int leaf : leaves) {
            if(leaf != min_leaf) {
                order[min_leaf].add(leaf);
            }
        }
        if(min_val == Integer.MAX_VALUE) {
            min_val = 0;
        }
        max[node] = Math.max(f[node], min_val);
        return min_leaf;
    } 
    static void toposort() {
        ans = new ArrayList<Integer>();
        boolean[] visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++) {
            if(!visited[i]) {
                dfs(i, visited);
            }
        }
    }
    static void dfs(int node, boolean[] visited) {
        visited[node] = true;
        for(int nei : order[node]) {
            if(!visited[nei]) {
                dfs(nei, visited);
            }
        }
        if(tree[node].size() == 0) {
            ans.add(node);
        }
    }
}
