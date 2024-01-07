import java.util.*;
import java.io.*;

public class _1773_B {
    static final long MOD = 1000000007;
    static final int MAXN = 1000;
    static long[] pow2 = new long[MAXN];
    static int[][] recs, pos;
    static long[][] hash;
    static int n, k;
    public static void main(String[] args) throws IOException {
        pow2[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            pow2[i] = pow2[i - 1] * 2 % MOD;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        n = Integer.parseInt(line.nextToken());
        k = Integer.parseInt(line.nextToken());

        recs = new int[k][n];
        // pos[i][j] tells the position of employee i in record j 
        pos = new int[n][k];
        hash = new long[k][n];
        for (int i = 0; i < k; i++) {
            line = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                int val = Integer.parseInt(line.nextToken()) - 1;
                recs[i][j] = val;
                pos[val][i] = j;
                hash[i][j] = ((j > 0 ? hash[i][j - 1] : 0) + pow2[val]) % MOD;
            }
        }

        int[][] ranges = new int[k][2];
        for (int i = 0; i < k; i++) {
            ranges[i] = new int[] {0, n - 1};
        }
        int[] par = new int[n];
        recurse(ranges, par, -1);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(par[i] >= 0 ? par[i] + 1 : par[i]);
            sb.append(' ');
        }
        out.println(sb.toString());

        in.close();
        out.close();
    }
    static void recurse(int[][] ranges, int[] par, int prev) {
        if (ranges[0][0] == ranges[0][1]) {
            par[recs[0][ranges[0][0]]] = prev;
            return;
        }
        for (int i = 0; i < n; i++) {
            long[] unique = new long[] {-1, -1};
            int[][] one = new int[k][2];
            int[][] two = new int[k][2];
            boolean is_root = true;
            for (int j = 0; j < k; j++) {
                if (pos[i][j] <= ranges[j][0] || pos[i][j] >= ranges[j][1]) {
                    is_root = false;
                    break;
                }
                long pre = hash[j][pos[i][j] - 1] - (ranges[j][0] > 0 ? hash[j][ranges[j][0] - 1] : 0);
                pre = (pre + MOD) % MOD;
                if (unique[0] == -1 || unique[0] == pre) {
                    unique[0] = pre;
                    one[j][0] = ranges[j][0];
                    one[j][1] = pos[i][j] - 1;
                    two[j][0] = pos[i][j] + 1;
                    two[j][1] = ranges[j][1];
                } else if (unique[1] == -1 || unique[1] == pre) {
                    unique[1] = pre;
                    two[j][0] = ranges[j][0];
                    two[j][1] = pos[i][j] - 1;
                    one[j][0] = pos[i][j] + 1;
                    one[j][1] = ranges[j][1];
                } else {
                    is_root = false;
                }
            }
            if (is_root) {
                par[i] = prev;
                recurse(one, par, i);
                recurse(two, par, i);
                return;
            }
        }
    }
}
