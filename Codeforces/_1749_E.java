import java.util.*;
import java.io.*;

public class _1749_E {
    static int[][] dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] grid = new int[n][m];
            for(int i = 0; i < n; i++) {
                String line = in.readLine();
                for(int j = 0; j < m; j++) {
                    if(line.charAt(j) == '#') {
                        grid[i][j] = 1;
                    }
                }
            }
            int[][] dp = new int[n][m];
            Pair[][] prev = new Pair[n][m];
            for(int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
            }
            for(int j = 0; j < m; j++) {
                for(int i = 0; i < n; i++) {
                    if(j == 0) {
                        if(grid[i][j] == 1) {
                            dp[i][j] = 0;
                        }else if(open(grid, i, j)) {
                            dp[i][j] = 1;
                        }
                    }else {
                        if(i > 0) {
                            if(dp[i - 1][j - 1] != -1) {
                                if(grid[i][j] == 1) {
                                    dp[i][j] = dp[i - 1][j - 1];
                                    prev[i][j] = new Pair(i - 1, j - 1);
                                }else if(open(grid, i, j)) {
                                    dp[i][j] = dp[i - 1][j - 1] + 1;
                                    prev[i][j] = new Pair(i - 1, j - 1);
                                }
                            }
                        }
                        if(i < n - 1) {
                            if(dp[i + 1][j - 1] != -1) {
                                if(grid[i][j] == 1) {
                                    if(dp[i][j] == -1 || dp[i + 1][j - 1] < dp[i][j]) {
                                        dp[i][j] = dp[i + 1][j - 1];
                                        prev[i][j] = new Pair(i + 1, j - 1);
                                    }
                                }else if (open(grid, i, j)) {
                                    if(dp[i][j] == -1 || dp[i + 1][j - 1] + 1 < dp[i][j]) {
                                        dp[i][j] = dp[i + 1][j - 1] + 1;
                                        prev[i][j] = new Pair(i + 1, j - 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int min = Integer.MAX_VALUE;
            int ind = -1;
            for (int i = 0; i < n; i++) {
                if (dp[i][m - 1] != -1 && dp[i][0] < min) {
                    min = dp[i][m - 1];
                    ind = i;
                }
            }
            if(min == Integer.MAX_VALUE) {
                out.println("NO");
            }else {
                out.println("YES");
                int r = ind;
                int c = m - 1;
                while(true) {
                    grid[r][c] = 1;
                    Pair prev_pair = prev[r][c];
                    if(prev_pair == null) {
                        break;
                    }
                    r = prev_pair.r;
                    c = prev_pair.c;
                }
                for(int i = 0; i < n; i++) {
                    StringBuilder sb = new StringBuilder();
                    for(int j = 0; j < m; j++) {
                        if(grid[i][j] == 1) {
                            sb.append('#');
                        }else {
                            sb.append('.');
                        }
                    }
                    out.println(sb.toString());
                }
            }
        }
        in.close();
        out.close();
    }
    static boolean open(int[][] grid, int r, int c) {
        for(int i = 0; i < 4; i++) {
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];
            if(nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length) {
                if(grid[nr][nc] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
    static class Pair {
        int r, c;
        Pair(int rr, int cc) {
            r = rr;
            c = cc;
        }
    }
}
