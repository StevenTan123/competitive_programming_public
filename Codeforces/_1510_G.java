import java.util.*;
import java.io.*;

public class _1510_G {
    static ArrayList<Integer> path;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());

            ArrayList<Integer>[] tree = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Integer>();
            }
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n - 1; i++) {
                tree[Integer.parseInt(line.nextToken()) - 1].add(i + 1);
            }

            int[] max_depth = new int[n];
            depth_dfs(tree, max_depth, 0);

            path = new ArrayList<Integer>();
            path_dfs(tree, max_depth, 0, k);
            out.println(path.size() - 1);
            StringBuilder sb = new StringBuilder();
            for (int node : path) {
                sb.append(node + 1);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        
        in.close();
        out.close();
    }

    static void path_dfs(ArrayList<Integer>[] tree, int[] max_depth, int cur, int left) {
        if (left == 0) {
            return;
        }

        left--;
        path.add(cur);
        
        int visit_return = Math.max(left - max_depth[cur], 0);
        int visit_end = left - visit_return;

        int max_child = -1;
        for (int child : tree[cur]) {
            if (max_child == -1 || max_depth[child] > max_depth[max_child]) {
                max_child = child;
            }
        }

        for (int child : tree[cur]) {
            if (child != max_child) {
                if (visit_return == 0) {
                    break;
                }
                visit_return = return_dfs(tree, child, visit_return);
                path.add(cur);
            }
        }

        if (max_child != -1) {
            path_dfs(tree, max_depth, max_child, visit_end + visit_return);
        }        
    }

    static int return_dfs(ArrayList<Integer>[] tree, int cur, int left) {
        if (left == 0) {
            return 0;
        }
        left--;
        path.add(cur);
        
        for (int nei : tree[cur]) {
            if (left == 0) {
                break;
            }
            left = return_dfs(tree, nei, left);
            path.add(cur);
        }
        return left;
    }

    static void depth_dfs(ArrayList<Integer>[] tree, int[] max_depth, int cur) {
        for (int child : tree[cur]) {
            depth_dfs(tree, max_depth, child);
            max_depth[cur] = Math.max(max_depth[cur], max_depth[child] + 1);
        }
    }
}
