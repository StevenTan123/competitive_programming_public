import java.util.*;
import java.io.*;

public class weightlifting {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int e = Integer.parseInt(line.nextToken());
            int w = Integer.parseInt(line.nextToken());
            int[][] counts = new int[e][w];
            for(int i = 0; i < e; i++) {
                line = new StringTokenizer(in.readLine());
                for(int j = 0; j < w; j++) {
                    counts[i][j] = Integer.parseInt(line.nextToken());
                }
            }
            int[][] shared = new int[e][e];
            for(int i = 0; i < e; i++) {
                int[] min = counts[i];
                int sum = 0;
                for(int k = 0; k < w; k++) {
                    sum += min[k];
                }
                shared[i][i] = sum;
                for(int j = i + 1; j < e; j++) {
                    for(int k = 0; k < w; k++) {
                        if(counts[j][k] < min[k]) {
                            sum -= min[k] - counts[j][k];
                            min[k] = counts[j][k];
                        }
                    }
                    shared[i][j] = sum;
                }
            }
            int[][] dp = new int[e][e];
            for(int i = e - 1; i >= 0; i--) {
                dp[i][i] = 2 * shared[i][i];
                for(int j = i + 1; j < e; j++) {
                    for(int k = i; k < j; k++) {
                        int new_val = dp[i][k] + dp[k + 1][j] - 2 * shared[i][j];
                        if(dp[i][j] == 0 || new_val < dp[i][j]) {
                            dp[i][j] = new_val;
                        }
                    }
                }
            }
            String res = "Case #" + t + ": ";
            out.println(res + dp[0][e - 1]);
        }
        in.close();
        out.close();
    }
}
