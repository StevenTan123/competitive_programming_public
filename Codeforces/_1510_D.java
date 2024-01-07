import java.util.*;
import java.io.*;

public class _1510_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int d = Integer.parseInt(line.nextToken());
        int[] a = new int[n];
        double[] logs = new double[n];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            logs[i] = Math.log(a[i]);
        }
        double[][] dp = new double[n][10];
        int[][][] prev = new int[n][10][3];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 10; j++) {
                dp[i][j] = -1;
                prev[i][j] = new int[] {-1, -1};
            }
        }
        for(int i = 0; i < n; i++) {
            int digit = a[i] % 10;
            if(logs[i] > dp[i][digit]) {
                dp[i][digit] = logs[i];
                prev[i][digit] = new int[] {-1, -1, a[i]};
            }
            for(int j = 0; j < 10; j++) {
                if(dp[i][j] > -0.5 && i < n - 1) {
                    digit = a[i + 1] % 10;
                    double next = dp[i][j] + logs[i + 1];
                    if(next > dp[i + 1][j * digit % 10]) {
                        dp[i + 1][j * digit % 10] = next;
                        prev[i + 1][j * digit % 10] = new int[] {i, j, a[i + 1]};
                    }
                    if(dp[i][j] > dp[i + 1][j]) {
                        dp[i + 1][j] = dp[i][j];
                        prev[i + 1][j] = new int[] {i, j, -1};
                    }
                }
            }
        }
        if(dp[n - 1][d] < -0.5) {
            out.println(-1);
        }else {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            int ind = n - 1;
            int digit = d;
            while(true) {
                int[] next = prev[ind][digit];
                if(next[0] == -1 || next[1] == -1) {
                    count++;
                    sb.append((int)next[2]);
                    sb.append(' ');
                    break;
                }
                if(next[2] > -0.5) {
                    count++;
                    sb.append((int)next[2]);
                    sb.append(' ');
                }
                ind = next[0];
                digit = next[1];
            }
            out.println(count);
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
}
