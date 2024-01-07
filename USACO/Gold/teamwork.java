import java.util.*;
import java.io.*;

public class teamwork {
    public static void main(String[] args) throws IOException {
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new FileReader("teamwork.in"));
        PrintWriter out = new PrintWriter("teamwork.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[] cows = new int[n];
        int[] pmax = new int[n];
        for(int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(in.readLine());
            pmax[i] = Math.max(i > 0 ? pmax[i - 1] : 0, cows[i]);
        }
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            int counter = 0;
            int max = 0;
            while(i - counter >= 0 && counter < k) {
                max = Math.max(max, cows[i - counter]);
                int curskill = max * (counter + 1);
                dp[i] = Math.max(dp[i], curskill + (i - counter > 0 ? dp[i - counter - 1] : 0));
                counter++;
            }
        }
        out.println(dp[n - 1]);
        in.close();
        out.close();
    }
}
