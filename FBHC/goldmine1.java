import java.util.*;
import java.io.*;

public class goldmine1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("gold_mine_chapter_1_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            int[] c = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            ArrayList<Integer>[] tree = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                c[i] = Integer.parseInt(line.nextToken());
                tree[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i < n - 1; i++) {
                line = new StringTokenizer(in.readLine());
                int v1 = Integer.parseInt(line.nextToken()) - 1;
                int v2 = Integer.parseInt(line.nextToken()) - 1;
                tree[v1].add(v2);
                tree[v2].add(v1);
            }
            int[] subtrees = new int[tree[0].size()];
            for(int i = 0; i < tree[0].size(); i++) {
                subtrees[i] = dfs(tree, new HashSet<Integer>(), c, tree[0].get(i), 0);
            }
            Arrays.sort(subtrees);
            int sum = c[0];
            if(subtrees.length > 0) {
                sum += subtrees[subtrees.length - 1];
            }
            if(subtrees.length > 1) {
                sum += subtrees[subtrees.length - 2];
            }
            String res = "Case #" + t + ": ";
            out.println(res + sum);
        }
        in.close();
        out.close();
    }
    static int dfs(ArrayList<Integer>[] tree, HashSet<Integer> visited, int[] c, int node, int sum) {
        if(visited.contains(node) || node == 0) {
            return 0;
        }
        visited.add(node);
        sum += c[node];
        int max = 0;
        for(int nei : tree[node]) {
            max = Math.max(max, dfs(tree, visited, c, nei, sum));
        }
        if(tree[node].size() == 1) {
            return sum;
        }else {
            return max;
        }
    }
}