import java.util.*;
import java.io.*;

public class _1598_E {
    static int[][][] dp;
    static int[][] state;
    static int n, m, open;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        n = Integer.parseInt(line.nextToken());
        m = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        int count = 0;
        //dp[i][j][0] is staircases going down, dp[i][j][1] is staircases going right
        dp = new int[n][m][2];
        state = new int[n][m];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = m - 1; j >= 0; j--) {
                count += trans(i, j);
            }
        }
        open = n * m;
        for(int i = 0; i < q; i++) {
            line = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(line.nextToken()) - 1;
            int c = Integer.parseInt(line.nextToken()) - 1;
            int change = update(r, c);
            count += change;
            out.println(count - open);
        }
        in.close();
        out.close();
    }
    static int update(int r, int c) {
        int change = 0;
        if(state[r][c] == 0) {
            open--;
        }else {
            open++;
        }
        state[r][c] = 1 - state[r][c];
        change += trans(r, c);
        for(int i = 1; i <= Math.min(n, m); i++) {
            if(in_bound(r - i + 1, c - i)) {
                change += trans(r - i + 1, c - i);
            }
            if(in_bound(r - i, c - i + 1)) {
                change += trans(r - i, c - i + 1);
            }
            if(in_bound(r - i, c - i)) {
                change += trans(r - i, c - i);
            }
        }
        return change;
    }
    static boolean in_bound(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
    static int trans(int r, int c) {
        if(state[r][c] == 0) {
            int orig = dp[r][c][0] + dp[r][c][1];
            int rig = c < m - 1 ? dp[r][c + 1][0] : 0;
            int bot = r < n - 1 ? dp[r + 1][c][1] : 0;
            dp[r][c][0] = bot + 1;
            dp[r][c][1] = rig + 1;
            int after = dp[r][c][0] + dp[r][c][1];
            return after - orig;
        }else {
            int val = -(dp[r][c][0] + dp[r][c][1]);
            dp[r][c][0] = 0;
            dp[r][c][1] = 0;
            return val;
        }
    }
}
