import java.util.*;
import java.io.*;

public class _1453_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[][] grid = new int[n][n];
            int[][] tgrid = new int[n][n];
            for(int i = 0; i < n; i++) {
                String s = in.readLine();
                for(int j = 0; j < n; j++) {
                    int digit = Character.getNumericValue(s.charAt(j));
                    grid[i][j] = digit;
                    tgrid[j][i] = digit;
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 10; i++) {
                int res = maxArea(grid, n, i);
                res = Math.max(res, maxArea(tgrid, n, i));
                sb.append(res);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static int maxArea(int[][] grid, int n, int digit) {
        int min = Integer.MAX_VALUE;
        int max = -1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == digit) {
                    min = Math.min(min, i);
                    max = Math.max(max, i);
                }
            }
        }
        int ret = 0;
        for(int i = 0; i < n; i++) {
            int first = -1;
            int last = -1;
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == digit) {
                    if(first == -1) first = j;
                    last = j;
                    int base = Math.max(j, n - j - 1);
                    int height = Math.max(i - min,  max - i);
                    ret = Math.max(base * height, ret);
                }
            }
            int height = Math.max(i, n - i - 1);
            ret = Math.max(ret, height * (last - first));
        }
        return ret;
    }
}
