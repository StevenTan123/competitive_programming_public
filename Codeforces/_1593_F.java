import java.util.*;
import java.io.*;

public class _1593_F {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            String x_str = in.readLine();
            int[] x = new int[n];
            for(int i = 0; i < n; i++) {
                x[i] = Character.getNumericValue(x_str.charAt(i));
            }
            //dp[i][j][k][l] stores if its possible to create a coloring using digits to i given 
            //there are j reds, red coloring is k mod a and black coloring is l mod b.
            int[][][][] dp = new int[n][n + 1][a][b];
            int[][][][][] prev = new int[n][n + 1][a][b][4];
            dp[0][1][x[0] % a][0] = 1;
            dp[0][0][0][x[0] % b] = 1;
            for(int i = 0; i < n - 1; i++) {
                for(int j = 0; j < n; j++) {
                    for(int k = 0; k < a; k++) {
                        for(int l = 0; l < b; l++) {
                            if(dp[i][j][k][l] == 0) {
                                continue;
                            }
                            int nextk = (k * 10 + x[i + 1]) % a;
                            int nextl = (l * 10 + x[i + 1]) % b;
                            dp[i + 1][j + 1][nextk][l] = 1;
                            prev[i + 1][j + 1][nextk][l] = new int[]{i, j, k, l};
                            dp[i + 1][j][k][nextl] = 1;
                            prev[i + 1][j][k][nextl] = new int[]{i, j, k, l};
                        }
                    }
                }
            }
            int res = Integer.MAX_VALUE;
            int[] state = new int[4];
            for(int j = 1; j < n; j++) {
                int dif = Math.abs(n - 2 * j);
                if(dp[n - 1][j][0][0] == 1 && dif < res) {
                    res = dif;
                    state = new int[]{n - 1, j, 0, 0};
                }
            }
            if(res == Integer.MAX_VALUE) {
                out.println(-1);
            }else {
                StringBuilder sb = new StringBuilder();
                while(state[0] != 0) {
                    int[] next = prev[state[0]][state[1]][state[2]][state[3]];
                    if(state[1] > next[1]) {
                        sb.append('R');
                    }else {
                        sb.append('B');
                    }
                    state = next;
                }
                if(state[1] == 0) {
                    sb.append('B');
                }else {
                    sb.append('R');
                }
                out.println(sb.reverse().toString());
            }
        }
        in.close();
        out.close();
    }
}
