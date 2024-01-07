import java.util.*;
import java.io.*;

public class guard {
    public static void main(String[] args) throws IOException {
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new FileReader("guard.in"));
        PrintWriter out = new PrintWriter("guard.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int h = Integer.parseInt(line.nextToken());
        int[] pow2 = new int[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }
        int[][] cows = new int[n][3];
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            cows[i] = new int[] {Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()), 
                                 Integer.parseInt(line.nextToken())};
        }
        long[] dp = new long[pow2[n]];
        Arrays.fill(dp, -1);
        for(int i = 0; i < n; i++) {
            dp[pow2[i]] = cows[i][2];
        }
        long res = -1;
        for(int i = 0; i < pow2[n]; i++) {
            if(dp[i] >= 0) {
                long height = 0;
                int val = i;
                for(int j = 0; j < n; j++) {
                    if(val % 2 == 0) {
                        dp[i + pow2[j]] = Math.max(dp[i + pow2[j]], Math.min(dp[i] - cows[j][1], cows[j][2]));
                    }else {
                        height += cows[j][0];
                    }
                    val /= 2;
                }
                if(height >= h) {
                    res = Math.max(res, dp[i]);
                }
            }
        }
        if(res == -1) {
            out.println("Mark is too tall");
        }else {
            out.println(res);
        }
        in.close();
        out.close();
    }
}
