import java.util.*;
import java.io.*;

public class _1799_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            int[] next = new int[n];
            Arrays.fill(next, n);
            int[] inds = new int[k];
            Arrays.fill(inds, -1);
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken()) - 1;
                if (inds[a[i]] > -1) {
                    next[inds[a[i]]] = i;
                }
                inds[a[i]] = i;
            }
            int[] cold = new int[k];
            int[] hot = new int[k];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < k; i++) {
                cold[i] = Integer.parseInt(line.nextToken());
            }
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < k; i++) {
                hot[i] = Integer.parseInt(line.nextToken());
            }

            long[] one_cpu = new long[n];
            for (int i = 0; i < n; i++) {
                if (i == 0 || a[i] != a[i - 1]) {
                    one_cpu[i] = (i > 0 ? one_cpu[i - 1] : 0) + cold[a[i]];
                } else {
                    one_cpu[i] = one_cpu[i - 1] + hot[a[i]];
                }
            }

            // dp[i][0/1] = min time to run programs 0...i such that program i was run cold/hot
            long[][] dp = new long[n][2];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], Long.MAX_VALUE);
            }
            dp[0][0] = cold[a[0]];
            for (int i = 0; i < n - 1; i++) {
                long min = Math.min(dp[i][0], dp[i][1]);
                if (min < Long.MAX_VALUE) {
                    if (a[i + 1] == a[i]) {
                        dp[i + 1][1] = Math.min(dp[i + 1][1], min + hot[a[i + 1]]);
                    } else {
                        dp[i + 1][0] = Math.min(dp[i + 1][0], min + cold[a[i + 1]]);
                    }
                }
                if (next[i] < n && a[i + 1] != a[i]) {
                    long time_one = one_cpu[next[i] - 1] - one_cpu[i + 1] + cold[a[i + 1]];
                    dp[next[i]][1] = Math.min(dp[next[i]][1], min + time_one + hot[a[next[i]]]);

                    if (dp[i + 1][1] != Long.MAX_VALUE) {
                        time_one = one_cpu[next[i] - 1] - one_cpu[i + 1];
                        dp[next[i]][1] = Math.min(dp[next[i]][1], dp[i + 1][1] + time_one + hot[a[next[i]]]);
                    }
                }
            }
            out.println(Math.min(dp[n - 1][0], dp[n - 1][1]));
        }
        in.close();
        out.close();
    }
}
