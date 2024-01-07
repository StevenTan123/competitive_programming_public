import java.util.*;
import java.io.*;

public class fortmoo {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("fortmoo.in"));
        PrintWriter out = new PrintWriter("fortmoo.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int[][] grid = new int[n][m];
        int[][] psums = new int[n][m];
        for(int i = 0; i < n; i++) {
            String line2 = in.readLine();
            for(int j = 0; j < m; j++) {
                if(line2.charAt(j) == 'X') {
                    grid[i][j] = 1;
                }
                psums[i][j] = j > 0 ? psums[i][j - 1] + grid[i][j] : grid[i][j];
            }
        }
        int max = 0;
        for(int l = 0; l < m; l++) {
            for(int r = l; r < m; r++) {
                int height = max_height(grid, psums, l, r);
                max = Math.max(max, (r - l + 1) * height);
            }
        }
        out.println(max);
        in.close();
        out.close();
    }
    static int max_height(int[][] grid, int[][] psums, int l, int r) {
        int max = 0;
        int prev = -1;
        for(int i = 0; i < grid.length; i++) {
            boolean empty = (psums[i][r] - (l > 0 ? psums[i][l - 1] : 0)) == 0;
            boolean frame = grid[i][l] == 0 && grid[i][r] == 0;
            if(empty) {
                if(prev > -1) {
                    max = Math.max(max, i - prev + 1);
                }else {
                    max = Math.max(max, 1);
                    prev = i;
                }
            }else if(!frame) {
                prev = -1;
            }
        }
        return max;
    }
}
