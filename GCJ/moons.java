import java.util.*;
import java.io.*;

public class moons {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(line.nextToken());
            int y = Integer.parseInt(line.nextToken());
            String s = line.nextToken();
            int[] a = new int[s.length()];
            for(int i = 0; i < a.length; i++) {
                char c = s.charAt(i);
                if(c == 'C') a[i] = 1;
                else if(c == 'J') a[i] = 2;
                else a[i] = 0;
            }
            //dp[i][j] = min cost filling up to index i ending on type j
            int[][] dp = new int[a.length][2];
            for(int i = 0; i < a.length; i++) Arrays.fill(dp[i], 1000000000);
            for(int i = 0; i < a.length; i++) {
                if(i == 0) {
                    if(a[i] == 0 || a[i] == 1) dp[i][0] = 0;
                    if(a[i] == 0 || a[i] == 2) dp[i][1] = 0;
                }else {
                    if(a[i] == 0 || a[i] == 1) dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1] + y);
                    if(a[i] == 0 || a[i] == 2) dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0] + x);
                }
            }
            String res = "Case #" + t + ": " + Math.min(dp[a.length - 1][0], dp[a.length - 1][1]);
            out.println(res);
        }
        in.close();
        out.close();
    }
}