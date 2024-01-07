import java.util.*;
import java.io.*;

public class _262144 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("262144.in"));
        PrintWriter out = new PrintWriter("262144.out");
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(in.readLine());
        }
        int[][] dp = new int[n][60];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        int max = 0;
        for(int i = n - 1; i >= 0; i--) {
            for(int j = 1; j < 60; j++) {
                if(j == a[i]) {
                    dp[i][j] = i;
                }else {
                    if(dp[i][j - 1] != -1 && dp[i][j - 1] < n - 1) {
                        dp[i][j] = dp[dp[i][j - 1] + 1][j - 1];
                    }
                }
                if(dp[i][j] != -1) {
                    max = Math.max(max, j);
                }
            }
        }
        out.println(max);
        in.close();
        out.close();
    }
}
