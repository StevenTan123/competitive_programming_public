import java.util.*;
import java.io.*;

public class pichu_garden {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[][] grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                String line = in.readLine();
                for (int j = 0; j < n; j++) {
                    grid[i][j] = line.charAt(j) == '*' ? 1 : 0;
                }
            }
            out.println(reachable(grid, 0, 0) ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
    static boolean reachable(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid.length || grid[r][c] == 1) {
            return false;
        }
        grid[r][c] = 1;
        if (r == grid.length - 1 && c == grid.length - 1) {
            return true;
        }
        return reachable(grid, r + 1, c) || reachable(grid, r, c + 1);
    }
}
