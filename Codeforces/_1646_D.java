import java.util.*;
import java.io.*;

public class _1646_D {
    static int n;
    static ArrayList<Integer>[] tree;
    static int[][][] dp;
    static int[] res;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        tree = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < n - 1; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(line.nextToken()) - 1;
            int v2 = Integer.parseInt(line.nextToken()) - 1;
            tree[v1].add(v2);
            tree[v2].add(v1);
        }
        if(n == 2) {
            out.println("2 2");
            out.println("1 1");
        }else {
            dp = new int[n][2][2];
            dfs(0, -1);
            int best = 0;
            if(dp[0][1][0] > dp[0][0][0] || (dp[0][1][0] == dp[0][0][0] && dp[0][1][1] < dp[0][0][1])) {
                best = 1;
            }
            res = new int[n];
            dfs2(0, -1, best);
            out.println(dp[0][best][0] + " " + dp[0][best][1]);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(res[i]);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static void dfs(int node, int prev) {
        for(int nei : tree[node]) {
            if(nei != prev) {
                dfs(nei, node);
                int count = dp[nei][0][0];
                int sum = dp[nei][0][1];
                if(dp[nei][1][0] > count || (dp[nei][1][0] == count && dp[nei][1][1] < sum)) {
                    count = dp[nei][1][0];
                    sum = dp[nei][1][1];
                }
                dp[node][0][0] += count;
                dp[node][0][1] += sum;
                dp[node][1][0] += dp[nei][0][0];
                dp[node][1][1] += dp[nei][0][1];
            }
        }
        dp[node][0][1]++;
        dp[node][1][0]++;
        dp[node][1][1] += tree[node].size();
    }
    static void dfs2(int node, int prev, int use) {
        if(use == 0) {
            res[node] = 1;
        }else {
            res[node] = tree[node].size();
        }
        for(int nei : tree[node]) {
            if(nei != prev) {
                if(use == 1) {
                    dfs2(nei, node, 0);
                }else {
                    int best = 0;
                    if(dp[nei][1][0] > dp[nei][0][0] || (dp[nei][1][0] == dp[nei][0][0] && dp[nei][1][1] < dp[nei][0][1])) {
                        best = 1;
                    }
                    dfs2(nei, node, best);
                }
            }
        }
    }
}
