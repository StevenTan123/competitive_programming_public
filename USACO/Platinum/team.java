import java.util.*;
import java.io.*;

public class team {
    static final long MOD = 1000000009;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("team.in"));
        PrintWriter out = new PrintWriter("team.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        int[] b = new int[m];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(line.nextToken());
        }
        Arrays.sort(a);
        Arrays.sort(b);
        long[][][] dp = new long[n + 1][m + 1][k + 1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                dp[i][j][0] = 1;
                for(int x = 1; x <= k; x++) {
                    if(i == 0 || j == 0) {
                        continue;
                    }
                    dp[i][j][x] = modadd(dp[i][j][x], dp[i - 1][j][x]);
                    dp[i][j][x] = modadd(dp[i][j][x], dp[i][j - 1][x]);
                    dp[i][j][x] = modadd(dp[i][j][x], -dp[i - 1][j - 1][x]);
                    if(a[i - 1] > b[j - 1]) {
                        dp[i][j][x] = modadd(dp[i][j][x], dp[i - 1][j - 1][x - 1]);
                    }
                }
            }
        }
        out.println(dp[n][m][k]);
        in.close();
        out.close();
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
}
