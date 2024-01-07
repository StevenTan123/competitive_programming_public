import java.util.*;
import java.io.*;

public class _1839_D {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] c = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                c[i] = Integer.parseInt(line.nextToken());
            }

            int[][] dp = new int[n][n + 1];
            dp[0][0] = 1;
            for (int i = 0; i < n; i++) {
                dp[i][1] = 1;
                for (int j = 0; j <= n; j++) {
                    if (i > 0 && c[i] > c[i - 1] && dp[i - 1][j] > 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + 1);
                    }
                    for (int k = 0; k < i - 1; k++) {
                        if (c[i] > c[k] && j > 0 && dp[k][j - 1] > 0) {
                            dp[i][j] = Math.max(dp[i][j], dp[k][j - 1] + 1);
                        }
                    }
                }
            }

            int[] res = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                res[i] = dp[n - 1][i];
                for (int j = 0; j < n - 1; j++) {
                    res[i] = Math.max(res[i], dp[j][i - 1]);
                }
            }

            int max = 0;
            for (int i = 1; i <= n; i++) {
                max = Math.max(max, res[i]);
                out.print(n - max + " ");
            }
            out.println();
		}
		
        in.close();
		out.close();
	}
}
