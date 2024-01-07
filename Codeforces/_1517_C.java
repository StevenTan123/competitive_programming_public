import java.util.*;
import java.io.*;

public class _1517_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] p = new int[n];
        int[] count = new int[n + 1];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(line.nextToken());
            count[i + 1] = 1;
        }
        int[][] grid = new int[n][n];
        for(int i = 0; i < n; i++) {
            grid[i][i] = p[i];
        }
        for(int i = 1; i < n; i++) {
            boolean prevright = false;
            for(int j = 0; j + i < n; j++) {
                int r = i + j;
                int c = j;
                int top = grid[r - 1][c];
                int right = grid[r][c + 1];
                if(count[top] >= top || prevright) {
                    grid[r][c] = right;
                    count[right]++;
                    prevright = true;
                }else {
                    grid[r][c] = top;
                    count[top]++;
                    prevright = false;
                }
            }
        }
        boolean possible = true;
        for(int i = 1; i <= n; i++) {
            if(count[i] != i) {
                possible = false;
                break;
            }
        }
        if(!possible) {
            out.println(-1);
        }else {
            for(int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < n; j++) {
                    if(j > i) break;
                    sb.append(grid[i][j]);
                    if(j < i) sb.append(' ');
                }
                out.println(sb.toString());
            }
        }
        in.close();
        out.close();
    }
}
