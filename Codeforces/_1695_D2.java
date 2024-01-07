import java.util.*;
import java.io.*;

public class _1695_D2 {
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
                int x = Integer.parseInt(line.nextToken()) - 1;
                int y = Integer.parseInt(line.nextToken()) - 1;
                tree[x].add(y);
                tree[y].add(x);
            }
            boolean line = true;
            for (int i = 0; i < n; i++) {
                if (tree[i].size() > 2) {
                    line = false;
                }
            }
            if (n == 1) {
                out.println(0);
            } else if (line) {
                out.println(1);
            } else {
                boolean[] leaf = new boolean[n];
                for (int i = 0; i < n; i++) {
                    if (tree[i].size() == 1) {
                        int prev = -1;
                        int cur = i;
                        while (tree[cur].size() <= 2) {
                            leaf[cur] = true;
                            int next = tree[cur].get(0);
                            if (next == prev) {
                                next = tree[cur].get(1);
                            }
                            prev = cur;
                            cur = next;
                        }
                    }
                }
                int res = 0;
                for (int i = 0; i < n; i++) {
                    if (!leaf[i]) {
                        int leaf_count = 0;
                        for (int nei : tree[i]) {
                            leaf_count += leaf[nei] ? 1 : 0;
                        }
                        if (leaf_count > 0) {
                            res += leaf_count - 1;
                        }
                    }
                }
                out.println(res);
            }
        }
        
        in.close();
        out.close();
    }
}
