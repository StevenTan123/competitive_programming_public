import java.util.*;
import java.io.*;

public class _1616_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            int x = Integer.parseInt(in.readLine());
            int[][] dp = new int[n][2];
            int[] max = new int[n];
            for(int i = 0; i < n; i++) {
                dp[i][0] = Math.max(dp[i][0], 1);
                if(i > 1) {
                    dp[i][0] = Math.max(dp[i][0], max[i - 2] + 1);
                    if(a[i] + a[i - 1] >= x * 2 && a[i] + a[i - 1] + a[i - 2] >= x * 3) {
                        dp[i][1] = Math.max(dp[i][1], dp[i - 1][1] + 1);
                    }
                }
                if(i > 0) {
                    if(a[i] + a[i - 1] >= x * 2) {
                        dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] + 1);
                    }
                }
                int curmax = Math.max(dp[i][0], dp[i][1]);
                max[i] = Math.max(i > 0 ? max[i - 1] : 0, curmax);
            }
            out.println(max[n - 1]);
        }
        in.close();
        out.close();
    }
}
