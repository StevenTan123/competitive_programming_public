import java.util.*;
import java.io.*;

public class _1811_G2 {
    static final int MOD = 1000000007;
    static final int MAXN = 5005;
    public static void main(String[] args) throws IOException {
        int[][] nck = new int[MAXN][MAXN];
        nck[0][0] = 1;
        for (int i = 1; i < MAXN; i++) {
            for (int j = 0; j < MAXN; j++) {
                if (i >= j) {
                    if (j == 0) {
                        nck[i][j] = 1;
                    } else {
                        nck[i][j] = (nck[i - 1][j - 1] + nck[i - 1][j]) % MOD;
                    }
                }
            }
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] c = new int[n];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                c[i] = Integer.parseInt(line.nextToken()) - 1;
            }
            long[] dp = new long[n];
            int[] max_blocks = new int[n];
            int max_block = 0;
            for (int i = k - 1; i < n; i++) {
                int count = 0;
                int cur_max = 0;
                for (int j = i; j >= 0; j--) {
                    if (c[j] == c[i]) {
                        count++;
                    }
                    int cur_blocks = 1 + (j > 0 ? max_blocks[j - 1] : 0);
                    if (count / k == 1 && cur_blocks >= cur_max) {
                        if (cur_blocks > cur_max) {
                            dp[i] = 0;
                        }
                        cur_max = cur_blocks;
                        dp[i] = (dp[i] + (j > 0 ? dp[j - 1] : 1) * nck[count - 1][k - 1]) % MOD;
                    }
                }
                max_blocks[i] = cur_max;
                max_block = Math.max(max_block, max_blocks[i]);
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                if (max_blocks[i] == max_block) {
                    res = (res + dp[i]) % MOD;
                }
            }
            if (max_block == 0) {
                res = 1;
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
}
