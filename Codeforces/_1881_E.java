import java.util.*;
import java.io.*;

public class _1881_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            int[] dp = new int[n + 1];
            dp[n] = 0;
            for (int i = n - 1; i >= 0; i--) {
                dp[i] = dp[i + 1] + 1;
                if (i + a[i] < n) {
                    dp[i] = Math.min(dp[i], dp[i + a[i] + 1]);
                }
            }
            out.println(dp[0]);
        }
        
        in.close();
        out.close();
    }
}
