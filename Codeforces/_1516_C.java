import java.util.*;
import java.io.*;

public class _1516_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        int sum = 0;
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            sum += a[i];
        }
        int x = 1;
        while(true) {
            boolean alldivides = true;
            for(int i = 0; i < n; i++) {
                if(a[i] % x != 0) {
                    alldivides = false;
                    break;
                }
            }
            if(!alldivides) break;
            x *= 2;
        }
        int[][] dp = new int[n][200005];
        dp[0][a[0]] = 1;
        for(int i = 1; i < n; i++) {
            dp[i][a[i]] = 1;
            for(int j = 0; j < 200005; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j > a[i] && dp[i - 1][j - a[i]] == 1) {
                    dp[i][j] = 1;
                }
            }
        }
        boolean good = true;
        if(sum % 2 == 1) good = false;
        else {
            good = false;
            for(int i = 0; i < n; i++) {
                if(dp[i][sum / 2] == 1) {
                    good = true;
                    break;
                }
            }
        }
        if(good) {
            out.println(1);
            int max = 0;
            for(int i = 1; i < n; i++) {
                if(a[i] % x > a[max] % x) {
                    max = i;
                }
            }
            out.println(max + 1);
        }else {
            out.println(0);
        }
        in.close();
        out.close();
    }
}
