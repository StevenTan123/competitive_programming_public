import java.util.*;
import java.io.*;

public class _1799_E {
    static int[][] dirs = new int[][] { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] grid = new int[n][m];
            for (int i = 0; i < n; i++) {
                String line = in.readLine();
                for (int j = 0; j < m; j++) {
                    grid[i][j] = line.charAt(j) == '#' ? 1 : 0;
                }
            }

            boolean[][] visited = new boolean[n][m];
            int[] bounds1 = null;
            int[] bounds2 = null;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1 && !visited[i][j]) {
                        if (bounds1 == null) {
                            bounds1 = dfs(grid, visited, i, j);
                        } else {
                            bounds2 = dfs(grid, visited, i, j);
                        }
                    }
                }
            }

            boolean cross_r = true;
            boolean cross_c = true;
            if (bounds1[0] > bounds2[1] || bounds2[0] > bounds1[1]) {
                cross_r = false;
            }
            if (bounds1[2] > bounds2[3] || bounds2[2] > bounds1[3]) {
                cross_c = false;
            }

            if (!cross_r && !cross_c) {
                int[] corner1 = new int[2];
                int[] corner2 = new int[2];
                if (bounds1[0] > bounds2[1]) {
                    corner1[0] = bounds1[0];
                    corner2[0] = bounds2[1];
                } else {
                    corner2[0] = bounds2[0];
                    corner1[0] = bounds1[1];
                }
                if (bounds1[2] > bounds2[3]) {
                    corner1[1] = bounds1[2];
                    corner2[1] = bounds2[3];
                } else {
                    corner2[1] = bounds2[2];
                    corner1[1] = bounds1[3];
                }
                int dr = Integer.signum(corner2[0] - corner1[0]);
                int dc = Integer.signum(corner2[1] - corner1[1]);
                while (true) {
                    grid[corner1[0]][corner1[1]] = 1;
                    if (corner1[0] == corner2[0]) {
                        break;
                    }
                    corner1[0] += dr;
                }
                while (true) {
                    grid[corner1[0]][corner1[1]] = 1;
                    if (corner1[1] == corner2[1]) {
                        break;
                    }
                    corner1[1] += dc;
                }
            }

            fill(grid);
            fill(grid);
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    sb.append(grid[i][j] == 1 ? '#' : '.');
                }
                out.println(sb.toString());
            }
            out.println();
        }
        in.close();
        out.close();
    }

    // Returned list is { min_r, max_r, min_c, max_c }
    static int[] dfs(int[][] grid, boolean[][] visited, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || visited[r][c]) {
            return null;
        }
        int[] bounds = new int[] { r, r, c, c };
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int[] bounds2 = dfs(grid, visited, r + dirs[i][0], c + dirs[i][1]);
            if (bounds2 != null) {
                bounds = update_bounds(bounds, bounds2);
            }
        }
        return bounds;
    }

    static int[] update_bounds(int[] a, int[] b) {
        int[] res = new int[4];
        res[0] = Math.min(a[0], b[0]);
        res[1] = Math.max(a[1], b[1]);
        res[2] = Math.min(a[2], b[2]);
        res[3] = Math.max(a[3], b[3]);
        return res;
    }

    static void fill(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            int min = -1;
            int max = -1;
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (min == -1) {
                        min = j;
                    }
                    max = j;
                }
            }
            if (min > -1) {
                for (int j = min; j <= max; j++) {
                    grid[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < grid[0].length; i++) {
            int min = -1;
            int max = -1;
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == 1) {
                    if (min == -1) {
                        min = j;
                    }
                    max = j;
                }
            }
            if (min > -1) {
                for (int j = min; j <= max; j++) {
                    grid[j][i] = 1;
                }
            }
        }
    }
}
