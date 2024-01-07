import java.util.*;
import java.io.*;

public class _1566_E {
    static int num_hanging = 0;
    static int leaves = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            ArrayList<Integer>[] tree = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i < n - 1; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                tree[u].add(v);
                tree[v].add(u);
            }
            dfs(0, tree, new boolean[n]);
            out.println(leaves - (num_hanging - 1));
            num_hanging = 0;
            leaves = 0;
        }
        in.close();
        out.close();
    }
    static int dfs(int node, ArrayList<Integer>[] tree, boolean[] visited) {
        visited[node] = true;
        int sum = 0;
        for(int nei : tree[node]) {
            if(!visited[nei]) {
                sum += dfs(nei, tree, visited);
            }
        }
        if(sum > 0) {
            leaves += sum;
            num_hanging++;
            return 0;
        }else {
            return 1;
        }
    }
}
