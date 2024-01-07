import java.util.*;
import java.io.*;

public class radio {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("radio.in"));
        PrintWriter out = new PrintWriter("radio.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[][] fjpos = new int[n + 1][2];
        int[][] bepos = new int[m + 1][2];
        line = new StringTokenizer(in.readLine());
        fjpos[0][0] = Integer.parseInt(line.nextToken());
        fjpos[0][1] = Integer.parseInt(line.nextToken());
        line = new StringTokenizer(in.readLine());
        bepos[0][0] = Integer.parseInt(line.nextToken());
        bepos[0][1] = Integer.parseInt(line.nextToken());
        read_paths(in.readLine(), fjpos);
        read_paths(in.readLine(), bepos);
        long[][] dp = new long[n + 1][m + 1];
        for(int i = 1; i <= n; i++) dp[i][0] = dp[i - 1][0] + dist(fjpos, bepos, i, 0);
        for(int i = 1; i <= m; i++) dp[0][i] = dp[0][i - 1] + dist(fjpos, bepos, 0, i);
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                long dist = dist(fjpos, bepos, i, j);
                dp[i][j] = dp[i - 1][j - 1] + dist;
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + dist);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dist);
            }
        }
        out.println(dp[n][m]);
        in.close();
        out.close();
    }
    static void read_paths(String path, int[][] pos) {
        char[] cdirs = new char[] {'N', 'E', 'S', 'W'};
        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for(int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            int dirind = 0;
            while(c != cdirs[dirind]) dirind++;
            pos[i + 1][0] = pos[i][0] + dirs[dirind][0];
            pos[i + 1][1] = pos[i][1] + dirs[dirind][1];
        }
    }
    static long dist(int[][] fjpos, int[][] bepos, int i, int j) {
        long xdif = fjpos[i][0] - bepos[j][0];
        long ydif = fjpos[i][1] - bepos[j][1];
        return xdif * xdif + ydif * ydif;
    }
}
