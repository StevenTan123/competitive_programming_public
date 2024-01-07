import java.util.*;
import java.io.*;

public class tests_for_haybales {
    static ArrayList<Integer>[] tree;
    static int n, k, x, maxd;
    static long[] res;
    static int[] depth;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(in.readLine());
        tree = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            tree[Integer.parseInt(line.nextToken())].add(i);
        }
        res = new long[n + 1];
        depth = new int[n + 1];
        k = n + 1;
        x = k - 1;
        dfs(n, 0);
        out.println(k);
        for(int i = 0; i < n; i++) {
            out.println((long)(maxd - depth[i]) * k + res[i]);
        }
        in.close();
        out.close();
    }
    static void dfs(int node, int d) {
        res[node] = x;
        depth[node] = d;
        maxd = Math.max(maxd, d);
        x--;
        ArrayList<Integer> children = tree[node];
        Collections.sort(children);
        for(int i = children.size() - 1; i >= 0; i--) {
            dfs(children.get(i), d + 1);
        }
    }
}