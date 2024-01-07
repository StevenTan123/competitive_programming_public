import java.util.*;
import java.io.*;

public class redistributing_gifts {
    public static void main(String[] args) throws IOException {
        int[] pow2 = new int[20];
        pow2[0] = 1;
        for(int i = 1; i < 20; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] rank = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++) {
                rank[i][Integer.parseInt(line.nextToken()) - 1] = j;
            }
        }
        long[][] dp = new long[pow2[n]][n];
        long[] res = new long[pow2[n]];
        res[0] = 1;
        for(int i = 0; i < n; i++) {
            dp[1 << i][i] = 1;
        }
        for(int i = 1; i < pow2[n]; i++) {
            for(int j = 0; j < n; j++) {
                int hibit = -1;
                for(int k = 0; k < n; k++) {
                    if((i & (1 << k)) > 0) {
                        hibit = k;
                    }
                }
                for(int k = 0; k < hibit; k++) {
                    if((i & (1 << k)) == 0 && adj(rank, j, k)) {
                        dp[i | (1 << k)][k] += dp[i][j];
                    }
                }
                if(adj(rank, j, hibit)) {
                    res[i] += dp[i][j];
                    for(int k = hibit + 1; k < n; k++) {
                        dp[i | (1 << k)][k] += dp[i][j];
                    }
                }
            }
        }
        int q = Integer.parseInt(in.readLine());
        for(int i = 0; i < q; i++) {
            String breeds = in.readLine();
            int bmask1 = 0;
            int bmask2 = 0;
            for(int j = 0; j < n; j++) {
                if(breeds.charAt(j) == 'H') {
                    bmask1 += 1 << j;
                }else {
                    bmask2 += 1 << j;
                }
            }
            long ans = res[bmask1] * res[bmask2];
            out.println(ans);
        }
        in.close();
        out.close();
    }
    static boolean adj(int[][] rank, int from, int to) {
        return rank[to][from] <= rank[to][to];
    }
}
