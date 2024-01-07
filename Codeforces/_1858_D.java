import java.util.*;
import java.io.*;

public class _1858_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            String s_str = in.readLine();
            int[] s = new int[n];
            int[] s_rev = new int[n];
            for (int i = 0; i < n; i++) {
                s[i] = s_str.charAt(i) == '1' ? 1 : 0;
                s_rev[n - i - 1] = s[i];
            }

            int[][] dp_f_0 = gen_dp(n, k, s, 0);
            int[][] dp_f_1 = gen_dp(n, k, s, 1);
            int[][] dp_b_0 = gen_dp(n, k, s_rev, 0);
            int[][] dp_b_1 = gen_dp(n, k, s_rev, 1);
            
            int[] max_l1 = new int[n + 1];
            Arrays.fill(max_l1, -1);
            for (int j = 0; j <= k; j++) {
                max_l1[0] = Math.max(max_l1[0], dp_f_1[n - 1][j]);
                if (dp_f_0[n - 1][j] == n) {
                    max_l1[n] = 0;
                }
                for (int i = 0; i < n - 1; i++) {
                    int l0 = dp_f_0[i][j];
                    int l1 = dp_b_1[n - i - 2][k - j];
                    max_l1[l0] = Math.max(max_l1[l0], l1);

                    l1 = dp_f_1[i][j];
                    l0 = dp_b_0[n - i - 2][k - j];
                    max_l1[l0] = Math.max(max_l1[l0], l1);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int a = 1; a <= n; a++) {
                int max = 0;
                for (int i = 0; i <= n; i++) {
                    if (max_l1[i] >= 0) {
                        max = Math.max(max, i * a + max_l1[i]);
                    }
                }
                sb.append(max);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static int[][] gen_dp(int n, int k, int[] s, int val) {
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            if (s[i] == val) {
                dp[i][0] = 1;
            } else if (k > 0) {
                dp[i][1] = 1;
            }
            for (int j = 0; j <= k; j++) {
                if (i > 0) {
                    if (s[i] == val) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + 1);
                    } else if (j > 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                    }
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }
        return dp;
    }
}
