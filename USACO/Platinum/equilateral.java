import java.util.*;
import java.io.*;

public class equilateral {
    static long total = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter out = new PrintWriter("triangles.out");
        int n = Integer.parseInt(in.readLine());
        int[][] grid = new int[n][n];
        for(int i = 0; i < n; i++) {
            String line = in.readLine();
            for(int j = 0; j < n; j++) {
                if(line.charAt(j) == '*') {
                    grid[i][j] = 1;
                }
            }
        }
        for(int i = 0; i < 4; i++) {
            count(grid, n);
            grid = rotate(grid, n);
        }
        out.println(total);
        in.close();
        out.close();
    }
    static int[][] rotate(int[][] grid, int n) {
        int[][] ret = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                ret[n - j - 1][i] = grid[i][j];
            }
        }
        return ret;
    }
    static void count(int[][] grid, int n) {
        for(int i = 0; i < 2 * n - 1; i++) {
            for(int j = i + 2; j < 2 * n - 1; j += 2) {
                int dist = (j - i) / 2;
                int start = Math.min(n - 1, i);
                int end = i - start;
                int count = 0;
                for(int k = start; k >= end; k--) {
                    if(k == start) {
                        int r = Math.min(n - 1, j);
                        int c = j - r;
                        while(r - dist > start && c + dist < n) {
                            if(grid[r][c] == 1 && grid[r - dist][c + dist] == 1) {
                                count++;
                            }
                            r--;
                            c++;
                        }
                    }
                    int r = k;
                    int c = i - r;
                    if(grid[r][c] == 1) {
                        total += count;
                    }
                    if(c + dist < n && r + 2 * dist < n) {
                        if(grid[r + 2 * dist][c] == 1 && grid[r + dist][c + dist] == 1) {
                            count--;
                        }
                    }
                    if(r + dist < n && c + 2 * dist < n) {
                        if(grid[r][c + 2 * dist] == 1 && grid[r + dist][c + dist] == 1) {
                            count++;
                        }
                    }
                }
            }
        }
    }
}
