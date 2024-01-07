import java.util.*;
import java.io.*;

public class _1453_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
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
            int l = 1;
            int r = n;
            int res = -1;
            while(l <= r) {
                int m = (l + r) / 2;
                if(dfs(tree, 0, -1, m) >= 0) {
                    res = m;
                    r = m - 1;
                }else {
                    l = m + 1;
                }
            }   
            out.println(res);         
        }
        in.close();
        out.close();
    }
    //-1 if not possible with given k, otherwise return smallest possible returning depth
    static int dfs(ArrayList<Integer>[] tree, int node, int prev, int k) {
        int min = Integer.MAX_VALUE;
        int max = -1;
        int max2 = -1;
        int count = 0;
        for(int nei : tree[node]) {
            if(nei != prev) {
                int cur = dfs(tree, nei, node, k);
                if(cur < 0) {
                    return -1;
                }
                min = Math.min(min, cur);
                if(cur <= max) {
                    max2 = Math.max(max2, cur);
                }else {
                    max2 = max;
                    max = cur;
                }
                count++;
            }
        }
        if(count == 0) {
            return 0;
        }else if(count == 1) {
            if(max + 1 > k) {
                return -1;
            }
            return max + 1;
        }else {
            if(max + 2 <= k) {
                return min + 1;
            }else if(max2 + 2 <= k) {
                if(max + 1 > k) {
                    return -1;
                }
                return max + 1;
            }else {
                return -1;
            }
        }
    }
}
