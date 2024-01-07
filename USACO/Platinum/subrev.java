import java.util.*;
import java.io.*;

public class subrev {
    static int[][][][] dp;
    static int[] a;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("subrev.in"));
        PrintWriter out = new PrintWriter("subrev.out");
        int n = Integer.parseInt(in.readLine());
        a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(in.readLine());
        }
        dp = new int[n][n][51][51];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < 51; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        out.println(max(0, n - 1, 0, 50));
        in.close();
        out.close();
    }
    static int max(int i, int j, int k, int l) {
        if(dp[i][j][k][l] != -1) {
            return dp[i][j][k][l];
        }
        if(k > l) {
            return Integer.MIN_VALUE;
        }
        if(i > j) {
            return 0;
        }
        if(i == j) {
            if(a[i] >= k && a[i] <= l) {
                return 1;
            }
            return 0;
        }
        int res = 0;
        res = Math.max(res, max(i + 1, j, k, l));
        res = Math.max(res, max(i, j - 1, k, l));
        if(a[i] >= k) {
            res = Math.max(res, max(i + 1, j, a[i], l) + 1);
        }
        if(a[j] <= l) {
            res = Math.max(res, max(i, j - 1, k, a[j]) + 1);
        }
        if(a[j] >= k) {
            res = Math.max(res, max(i + 1, j - 1, a[j], l) + 1);
        }
        if(a[i] <= l) {
            res = Math.max(res, max(i + 1, j - 1, k, a[i]) + 1);
        }
        if(a[j] >= k && a[i] <= l) {
            res = Math.max(res, max(i + 1, j - 1, a[j], a[i]) + 2);
        }
        dp[i][j][k][l] = res;
        return res;
    }
}
