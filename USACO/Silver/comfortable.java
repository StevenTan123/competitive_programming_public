import java.util.*;
import java.io.*;

public class comfortable {
    static int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] cows = new int[n][2];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            cows[i][0] = Integer.parseInt(line.nextToken()) + 505;
            cows[i][1] = Integer.parseInt(line.nextToken()) + 505;
        }
        boolean[][] grid = new boolean[2010][2010];
        boolean[][] block = new boolean[2010][2010];
        int add = 0;
        for(int i = 0; i < n; i++) {
            grid[cows[i][0]][cows[i][1]] = true;
            if(block[cows[i][0]][cows[i][1]]) {
                block[cows[i][0]][cows[i][1]] = false;
                add--;
            }
            add += add(grid, block, cows[i][0], cows[i][1]);
            out.println(add);
        }
        in.close();
        out.close();
    }
    static int add(boolean[][] grid, boolean[][] block, int r, int c) {
        int res = 0;
        res += check_cell(grid, block, r, c);
        for(int i = 0; i < 4; i++) {
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];
            if(grid[nr][nc] || block[nr][nc]) {
                res += check_cell(grid, block, nr, nc);
            }
        }
        return res;
    }
    static int check_cell(boolean[][] grid, boolean[][] block, int r, int c) {
        int res = 0;
        int num = 0;
        int[] empty = null;
        for(int i = 0; i < 4; i++) {
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];
            if(block[nr][nc] || grid[nr][nc]) num++;
            else empty = new int[]{nr, nc};
        }
        if(num == 3) {
            res += 1;
            block[empty[0]][empty[1]] = true;
            res += add(grid, block, empty[0], empty[1]);
        }
        return res;
    }
}
