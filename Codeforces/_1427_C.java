import java.util.*;
import java.io.*;

public class _1427_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int r = Integer.parseInt(line.nextToken());
        int n = Integer.parseInt(line.nextToken());
        int[][] celebs = new int[n + 1][3];
        celebs[0] = new int[]{0, 1, 1};
        for(int i = 1; i <= n; i++) {
            line = new StringTokenizer(in.readLine());
            celebs[i][0] = Integer.parseInt(line.nextToken());
            celebs[i][1] = Integer.parseInt(line.nextToken());
            celebs[i][2] = Integer.parseInt(line.nextToken());
        }
        //max photos taken given last photo was at celebrity i
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int[] max = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            int low = Math.max(0, i - 2 * r);
            for(int j = i - 1; j >= low; j--) {
                int dist = Math.abs(celebs[i][1] - celebs[j][1]) + Math.abs(celebs[i][2] - celebs[j][2]);
                if(dp[j] > -1 && celebs[j][0] + dist <= celebs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if(low > 0 && max[low - 1] > -1) {
                dp[i] = Math.max(dp[i], max[low - 1] + 1);
            }
            max[i] = Math.max(max[i - 1], dp[i]);
        }
        out.println(max[n]);
        in.close();
        out.close();
    }
}
