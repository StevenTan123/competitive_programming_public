import java.util.*;
import java.io.*;

public class _1607_F {
    static int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            in.readLine();
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[][] grid = new int[n][m];
            for(int i = 0; i < n; i++) {
                String line2 = in.readLine();
                for(int j = 0; j < m; j++) {
                    if(line2.charAt(j) == 'U') {
                        grid[i][j] = 0;
                    }else if(line2.charAt(j) == 'R') {
                        grid[i][j] = 1;
                    }else if(line2.charAt(j) == 'D') {
                        grid[i][j] = 2;
                    }else {
                        grid[i][j] = 3;
                    }
                }
            }
            int[][] marks = new int[n][m];
            int[][] dists = new int[n][m];
            int d = 0;
            int row = 0;
            int col = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    int r = i;
                    int c = j;
                    int dist = 0;
                    while(r >= 0 && r < n && c >= 0 && c < m) {
                        if(marks[r][c] > 0) {
                            dists[r][c] = dist - marks[r][c] + 1;
                            break;
                        }else if(dists[r][c] > 0) {
                            dist += dists[r][c];
                            break;
                        }
                        dist++;
                        marks[r][c] = dist;
                        int[] dir = dirs[grid[r][c]];
                        r += dir[0];
                        c += dir[1];
                    }
                    r = i;
                    c = j;
                    int cyc_len = -1;
                    while(r >= 0 && r < n && c >= 0 && c < m) {
                        if(marks[r][c] == 0) {
                            break;
                        }
                        marks[r][c] = 0;
                        if(dists[r][c] > 0) {
                            cyc_len = dists[r][c];
                        }
                        if(cyc_len == -1) {
                            dists[r][c] = dist;
                        }else {
                            dists[r][c] = cyc_len;
                        }
                        if(dists[r][c] > d) {
                            d = dists[r][c];
                            row = r + 1;
                            col = c + 1;
                        }
                        int[] dir = dirs[grid[r][c]];
                        r += dir[0];
                        c += dir[1];
                        dist--;
                    }
                }
            }
            out.println(row + " " + col + " " + d); 
        }
        in.close();
        out.close();
    }
}
