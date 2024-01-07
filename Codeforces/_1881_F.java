import java.util.*;
import java.io.*;

public class _1881_F {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            
            boolean[] marked = new boolean[n];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < k; i++) {
                marked[Integer.parseInt(line.nextToken()) - 1] = true;
            }

            ArrayList<Integer>[] tree = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < n - 1; i++) {
                line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                tree[u].add(v);
                tree[v].add(u);
            }

            int[] deepest_mark = new int[n];
            Arrays.fill(deepest_mark, -1);
            int[] farthest = new int[n];
            dfs(tree, marked, deepest_mark, farthest, 0, -1);

            int res = farthest[0] / 2;
            if (farthest[0] % 2 == 1) {
                res++;
            } 
            out.println(res);
        }
        
        in.close();
        out.close();
    }

    static void dfs(ArrayList<Integer>[] tree, boolean[] marked, int[] deepest_mark, int[] farthest, int cur, int prev) {
        if (marked[cur]) {
            deepest_mark[cur] = 0;
        }
        ArrayList<Integer> depths = new ArrayList<Integer>();
        for (int nei : tree[cur]) {
            if (nei != prev) {
                dfs(tree, marked, deepest_mark, farthest, nei, cur);
                farthest[cur] = Math.max(farthest[cur], farthest[nei]);
                if (deepest_mark[nei] != -1) {
                    depths.add(deepest_mark[nei]);
                }
            }
        }
        Collections.sort(depths);
        if (depths.size() > 0) {
            int deepest = depths.get(depths.size() - 1);
            deepest_mark[cur] = Math.max(deepest_mark[cur], deepest + 1);
            if (marked[cur]) {
                farthest[cur] = Math.max(farthest[cur], deepest_mark[cur]);
            }
            if (depths.size() > 1) {
                int second = depths.get(depths.size() - 2);
                farthest[cur] = Math.max(farthest[cur], deepest + second + 2);
            }
        }
    }
}
