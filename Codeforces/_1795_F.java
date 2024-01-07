import java.util.*;
import java.io.*;

public class _1795_F {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            ArrayList<Integer>[] tree = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                tree[u].add(v);
                tree[v].add(u);
            }

            int k = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] chips = new int[k];
            for (int i = 0; i < k; i++) {
                chips[i] = Integer.parseInt(line.nextToken()) - 1;
            }

            int res = -1;
            int l = 0;
            int r = n;
            while (l <= r) {
                int m = (l + r) / 2;
                if (possible(tree, chips, m)) {
                    res = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static boolean possible(ArrayList<Integer>[] tree, int[] chips, int moves) {
        int[] lengths = new int[tree.length];
        for (int i = 0; i < chips.length; i++) {
            int floor = moves / chips.length;
            if (i < moves % chips.length) {
                lengths[chips[i]] = floor + 2;
            } else {
                lengths[chips[i]] = floor + 1;
            }
        }
        int ret = dfs(tree, lengths, 0, -1);
        if (ret == -1) {
            return false;
        } else {
            return true;
        }
    }
    // If non-negative, returns longest open path from root into subtree.
    // If -1, not possible.
    static int dfs(ArrayList<Integer>[] tree, int[] lengths, int cur, int prev) {
        boolean possible = true;
        int max_open = 0;
        for (int nei : tree[cur]) {
            if (nei != prev) {
                int next = dfs(tree, lengths, nei, cur);
                if (next == -1) {
                    possible = false;
                    break;
                }
                max_open = Math.max(max_open, next);
            }
        }
        if (!possible) {
            return -1;
        }
        if (lengths[cur] == 0) {
            return max_open + 1;
        } else if(lengths[cur] <= max_open + 1) {
            return 0;
        } else {
            if (prev == -1) {
                return -1;
            }
            if (lengths[prev] != 0) {
                return -1;
            }
            lengths[prev] = lengths[cur] - 1;
            return 0;
        }
    }
}
