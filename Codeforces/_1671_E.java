import java.util.*;
import java.io.*;

public class _1671_E { 

    static final int MOD = 998244353;
    static final int MAXN = 300000;
    static int[] pow2 = new int[MAXN];
    static long[] subtree_hash;
    static long[] count;
    static int[] height;
    
    public static void main(String[] args) throws IOException {
        pow2[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            pow2[i] = pow2[i - 1] * 2 % MOD;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int n = Integer.parseInt(in.readLine());
        String s = in.readLine();
        subtree_hash = new long[pow2[n]];
        count = new long[pow2[n]];
        height = new int[pow2[n]];
        dfs(1, s);
        out.println(count[1]);

        in.close();
        out.close();
    }
    static void dfs(int node, String s) {
        if (node * 2 > s.length()) {
            subtree_hash[node] = s.charAt(node - 1) == 'A' ? 0 : 1;
            count[node] = 1;
            height[node] = 1;
        } else {
            dfs(node * 2, s);
            dfs(node * 2 + 1, s);
            if (subtree_hash[node * 2] == subtree_hash[node * 2 + 1]) {
                count[node] = count[node * 2] * count[node * 2 + 1] % MOD;
            } else {
                count[node] = count[node * 2] * count[node * 2 + 1] * 2 % MOD;
            }
            long min_hash = Math.min(subtree_hash[node * 2], subtree_hash[node * 2 + 1]);
            long max_hash = Math.max(subtree_hash[node * 2], subtree_hash[node * 2 + 1]);
            height[node] = height[node * 2] + 1;
            subtree_hash[node] = ((s.charAt(node - 1) == 'A' ? 0 : 1) * pow2[pow2[height[node]] - 1] + min_hash) % MOD;
            subtree_hash[node] = (subtree_hash[node] * pow2[pow2[height[node]] - 1] + max_hash) % MOD;
        }
    }
}
